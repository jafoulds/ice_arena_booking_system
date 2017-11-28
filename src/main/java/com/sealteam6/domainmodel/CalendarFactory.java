package com.sealteam6.domainmodel;

import com.sealteam6.repository.BookingRepository;
import com.sealteam6.repository.RinkRepository;
import com.sealteam6.service.ArenaScheduleService;
import com.sealteam6.service.GroupService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Builder
@AllArgsConstructor
public class CalendarFactory {


    @Autowired private BookingRepository bookingRepository;

    @Autowired
    private RinkRepository rinkRepository;

    @Autowired
    private ArenaScheduleService arenaScheduleService;

    @Autowired
    private GroupService groupService;

    public Calendar createCalendarForUser(YearMonth yearMonth, User user) {
        List<Rink> rinks = rinkRepository.findAll();
        List<Booking> allBookingsForMonth =  getAllBookingsForMonthAndYear(yearMonth);
        allBookingsForMonth = allBookingsForMonth.stream()
                .filter(booking -> groupService.userIsInGroup(user.getUsername(), booking.getGroupName()))
                .collect(Collectors.toList());

        List<List<Booking>> bookingsByDay = separateByDay(allBookingsForMonth, yearMonth.lengthOfMonth());
        List<CalendarDay> calendarDays = bookingsByDay.stream()
                .map(this::bookingsToCalendarDay)
                .collect(Collectors.toList());
        Collections.sort(calendarDays);
        return new Calendar(calendarDays, yearMonth);
    }

    public Calendar createCalendarOfAvailableTimeSlots(int month, int year) {
        List<Rink> rinks = rinkRepository.findAll();
        YearMonth yearMonth = YearMonth.of(year, month);

        List<Booking> allBookingsForMonth =  getAllBookingsForMonthAndYear(yearMonth);
        List<List<Booking>> bookingsByDay = separateByDay(allBookingsForMonth, yearMonth.lengthOfMonth());

        List<CalendarDay> calendarDays = new ArrayList<>();
        LocalDate currentDate = LocalDate.of(year, month, 1);
        while (currentDate.getMonthValue() == month) {
            LocalTime openingTime = arenaScheduleService.getOpeningTime(currentDate);
            LocalTime closingTime = arenaScheduleService.getClosingTime(currentDate);

            List<TimeSlot> timeSlots = getAvailableTimeSlots(
                    bookingsByDay.get(currentDate.getDayOfMonth()-1), openingTime, closingTime, rinks);
            calendarDays.add(new CalendarDay(
                    LocalDate.of(currentDate.getYear(), currentDate.getMonth(), currentDate.getDayOfMonth()), timeSlots));

            currentDate = currentDate.plusDays(1);
        }
        return new Calendar(calendarDays, yearMonth);
    }

    private CalendarDay bookingsToCalendarDay(List<Booking> bookings) {
        if (bookings.size() == 0) {
            return new CalendarDay(LocalDate.now(), Collections.emptyList());
        }
        LocalDate date = bookings.get(0).getStartDate().toLocalDate();
        List<TimeSlot> timeSlots = bookings.stream()
                .map(booking -> {
                    return TimeSlot.builder()
                            .startTime(booking.getStartDate().toLocalTime())
                            .endTime(booking.getEndDate().toLocalTime())
                            .rink(booking.getRink())
                            .bookingId(booking.getId())
                            .build();
                }).collect(Collectors.toList());

        return new CalendarDay(date, timeSlots);
    }

    private List<TimeSlot> getAvailableTimeSlots(List<Booking> bookings, LocalTime openingTime,
                                                 LocalTime closingTime, List<Rink> rinks) {

        Map<Rink, List<Booking>> bookingsByRink = new HashMap<>();
        for (Rink rink : rinks) {
            bookingsByRink.put(rink, new ArrayList<>());
        }
        for (Booking booking : bookings) {
            bookingsByRink.get(booking.getRink()).add(booking);
        }
        List<TimeSlot> availableTimeSlots = new ArrayList<>();
        for (Rink rink : rinks) {
            availableTimeSlots.addAll(getAvailableTimeSlotsForRink(
                    bookingsByRink.get(rink), openingTime, closingTime, rink));
        }
        return availableTimeSlots;
    }

    private List<TimeSlot> getAvailableTimeSlotsForRink(List<Booking> bookings, LocalTime openingTime,
                                                        LocalTime closingTime, Rink rink) {

        List<TimeSlot> availableTimeSlots = new ArrayList<>();
        Collections.sort(bookings);

        LocalTime startOfTimeSlot = openingTime;
        for (Booking booking : bookings) {
            LocalTime bookingStartTime = booking.getStartDate().toLocalTime();
            LocalTime bookingEndTime = booking.getEndDate().toLocalTime();
            if (!startOfTimeSlot.equals(bookingStartTime)) {
                availableTimeSlots.add(TimeSlot.builder()
                        .startTime(startOfTimeSlot)
                        .endTime(bookingStartTime)
                        .rink(rink)
                        .build());
            }
            startOfTimeSlot = bookingEndTime;
        }
        if (startOfTimeSlot.isBefore(closingTime)) {
            availableTimeSlots.add(TimeSlot.builder()
                .startTime(startOfTimeSlot)
                .endTime(closingTime)
                .rink(rink)
                .build());
        }
        return availableTimeSlots;
    }

    private List<List<Booking>> separateByDay(List<Booking> bookings, int lengthOfMonth) {
        List<List<Booking>> bookingsByDay = new ArrayList<>();
        for (int i = 0; i < lengthOfMonth; i++) {
            bookingsByDay.add(new ArrayList<>());
        }
        for (Booking booking : bookings) {
            bookingsByDay.get(booking.getStartDate().getDayOfMonth()-1).add(booking);
        }
        return bookingsByDay;

    }

    private List<Booking> getAllBookingsForMonthAndYear(YearMonth month) {
        LocalDateTime startOfMonth = LocalDateTime
                .of(month.getYear(), month.getMonthValue(), 1, 0, 0);
        LocalDateTime endOfMonth = LocalDateTime
                .of(month.getYear(), month.plusMonths(1).getMonthValue(), 1, 0, 0)
                .minusMinutes(1);
        return bookingRepository.findByDateBetween(startOfMonth, endOfMonth);
    }
}
