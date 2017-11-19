package com.sealteam6.domainmodel;

import com.sealteam6.repository.BookingRepository;
import com.sealteam6.repository.RinkRepository;
import com.sealteam6.service.ArenaScheduleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CalendarFactoryTest {

    @MockBean
    private BookingRepository bookingRepository;

    @MockBean
    private RinkRepository rinkRepository;

    @MockBean
    private ArenaScheduleService arenaScheduleService;

    private final String USERNAME = "fake_user";
    private final String GROUPNAME = "fake_group";

    @Before
    public void setup() {
        List<Rink> allRinks = new ArrayList<>();
        allRinks.add(new Rink("1"));
        allRinks.add(new Rink("2"));
        when(rinkRepository.findAll()).thenReturn(allRinks);
        when(arenaScheduleService.getOpeningTime(any(LocalDate.class))).thenReturn(LocalTime.of(5, 0));
        when(arenaScheduleService.getClosingTime(any(LocalDate.class))).thenReturn(LocalTime.of(20, 0));
    }

    @Test
    public void fullAvailabilityTest() throws Exception {
        when(bookingRepository.findByDateBetween(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Collections.emptyList());
        List<Rink> rinks = rinkRepository.findAll();

        CalendarFactory factory = new CalendarFactory(bookingRepository, rinkRepository, arenaScheduleService);
        YearMonth yearMonth = YearMonth.of(2000, 1);
        Calendar calendar = factory.createCalendar(yearMonth.getMonthValue(), yearMonth.getYear());

        assertThat(calendar.getDays().size(), is(yearMonth.lengthOfMonth()));
        CalendarDay firstDay = calendar.getDays().get(0);
        // There should be one time slot per rink that starts at opening and ends at closing.
        assertThat(firstDay.getAvailableTimeSlots().size(), is(rinks.size()));
        TimeSlot timeSlot = firstDay.getAvailableTimeSlots().get(0);
        assertThat(timeSlot.getStartTime(), is(arenaScheduleService.getOpeningTime(yearMonth.atDay(1))));
        assertThat(timeSlot.getEndTime(), is(arenaScheduleService.getClosingTime(yearMonth.atDay(1))));

    }

    @Test
    public void noAvailabilityTest() throws Exception {
        List<Rink> rinks = rinkRepository.findAll();
        YearMonth yearMonth = YearMonth.of(2000, 1);
        LocalDate date = yearMonth.atDay(1);
        List<Booking> bookings = new ArrayList<>();
        LocalDateTime startDate = LocalDateTime.of(date, arenaScheduleService.getOpeningTime(date));
        LocalDateTime endDate= LocalDateTime.of(date, arenaScheduleService.getClosingTime(date));
        bookings.add(new Booking(startDate, endDate, rinks.get(0), USERNAME, GROUPNAME));
        when(bookingRepository.findByDateBetween(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(bookings);

        CalendarFactory factory = new CalendarFactory(bookingRepository, rinkRepository, arenaScheduleService);
        Calendar calendar = factory.createCalendar(yearMonth.getMonthValue(), yearMonth.getYear());

        assertThat(calendar.getDays().size(), is(yearMonth.lengthOfMonth()));
        CalendarDay firstDay = calendar.getDays().get(date.getDayOfMonth()-1);

        // One rink is available for the entire day and the other is unavailable.
        assertThat(firstDay.getAvailableTimeSlots().size(), is(1));
        TimeSlot timeSlot = firstDay.getAvailableTimeSlots().get(0);
        assertThat(timeSlot.getStartTime(), is(arenaScheduleService.getOpeningTime(yearMonth.atDay(1))));
        assertThat(timeSlot.getEndTime(), is(arenaScheduleService.getClosingTime(yearMonth.atDay(1))));
        assertThat(timeSlot.getRink(), is(rinks.get(1)));
    }

    @Test
    public void partialAvailabilityTest() throws Exception {
        List<Rink> rinks = rinkRepository.findAll();
        YearMonth yearMonth = YearMonth.of(2000, 1);
        LocalDate date = yearMonth.atDay(1);
        LocalTime openingTime = arenaScheduleService.getOpeningTime(date);
        LocalTime closingTime = arenaScheduleService.getClosingTime(date);
        List<Booking> bookings = new ArrayList<>();
        LocalDateTime startDate1 = LocalDateTime.of(date, openingTime.plusHours(1));
        LocalDateTime endDate1 = LocalDateTime.of(date, closingTime.minusHours(1));
        LocalDateTime startDate2 = LocalDateTime.of(date, openingTime);
        LocalDateTime endDate2 = LocalDateTime.of(date, closingTime);
        bookings.add(new Booking(startDate1, endDate1, rinks.get(0), USERNAME, GROUPNAME));
        bookings.add(new Booking(startDate2, endDate2, rinks.get(1), USERNAME, GROUPNAME));
        when(bookingRepository.findByDateBetween(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(bookings);

        CalendarFactory factory = new CalendarFactory(bookingRepository, rinkRepository, arenaScheduleService);
        Calendar calendar = factory.createCalendar(yearMonth.getMonthValue(), yearMonth.getYear());

        assertThat(calendar.getDays().size(), is(yearMonth.lengthOfMonth()));
        CalendarDay firstDay = calendar.getDays().get(date.getDayOfMonth()-1);

        assertThat(firstDay.getAvailableTimeSlots().size(), is(2));

        TimeSlot timeSlot = firstDay.getAvailableTimeSlots().get(0);
        assertThat(timeSlot.getStartTime(), is(openingTime));
        assertThat(timeSlot.getEndTime(), is(openingTime.plusHours(1)));

        timeSlot = firstDay.getAvailableTimeSlots().get(1);
        assertThat(timeSlot.getStartTime(), is(closingTime.minusHours(1)));
        assertThat(timeSlot.getEndTime(), is(closingTime));

    }
}
