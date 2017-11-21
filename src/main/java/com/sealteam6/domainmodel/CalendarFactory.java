package com.sealteam6.domainmodel;

import com.sealteam6.repository.BookingRepository;
import com.sealteam6.repository.RinkRepository;
import com.sealteam6.service.ArenaScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.*;

@Component
public class CalendarFactory {


    @Autowired private BookingRepository bookingRepository;

    @Autowired
    private RinkRepository rinkRepository;

    @Autowired
    private ArenaScheduleService arenaScheduleService;

    @Autowired
    public CalendarFactory(
            BookingRepository bookingRepository,
            RinkRepository rinkRepository,
            ArenaScheduleService arenaScheduleService) {

        this.bookingRepository = bookingRepository;
        this.rinkRepository = rinkRepository;
        this.arenaScheduleService = arenaScheduleService;
    }

    public Calendar createCalendar(int month, int year) {
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
                    bookingsByDay.get(currentDate.getDayOfMonth()), openingTime, closingTime, rinks);
            calendarDays.add(new CalendarDay(
                    LocalDate.of(currentDate.getYear(), currentDate.getMonth(), currentDate.getDayOfMonth()), timeSlots));

            currentDate = currentDate.plusDays(1);
        }
        return new Calendar(calendarDays, yearMonth);
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
                availableTimeSlots.add(new TimeSlot(startOfTimeSlot, bookingStartTime, rink));
            }
            startOfTimeSlot = bookingEndTime;
        }
        if (startOfTimeSlot.isBefore(closingTime)) {
            availableTimeSlots.add(new TimeSlot(startOfTimeSlot, closingTime, rink));
        }
        return availableTimeSlots;
    }

    private List<List<Booking>> separateByDay(List<Booking> bookings, int lengthOfMonth) {
        List<List<Booking>> bookingsByDay = new ArrayList<>();
        for (int i = 0; i <= lengthOfMonth; i++) {
            bookingsByDay.add(new ArrayList<>());
        }
        for (Booking booking : bookings) {
            bookingsByDay.get(booking.getStartDate().getDayOfMonth()).add(booking);
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
