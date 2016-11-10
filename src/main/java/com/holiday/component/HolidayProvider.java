package com.holiday.component;

import de.jollyday.Holiday;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import de.jollyday.ManagerParameters;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class HolidayProvider {

    private HolidayManager getDefaultHolidayManager(String locale) {
        return HolidayManager.getInstance(ManagerParameters.create(locale));
    }

    public Boolean isHoliday(String locale, LocalDate localDate, String ... args) {
        return
                this.getDefaultHolidayManager(locale)
                        .isHoliday(
                                localDate,
                                args
                        );
    }

    public Set<String> getCountries() {

        return
                Arrays
                        .stream(HolidayCalendar.values())
                        .map(HolidayCalendar::getId)
                        .map(String::toLowerCase)
                        .collect(Collectors.toSet());

    }

    public Set<String> getStates(String locale) {

        return
                this.getDefaultHolidayManager(locale)
                        .getCalendarHierarchy()
                        .getChildren()
                        .keySet();

    }

    public Set<Holiday> findHolidays(String country,
                                     String state,
                                     Integer year,
                                     Integer month,
                                     Integer day) {

        LocalDate now = LocalDate.now();

        if (country == null) {
            country = "br";
        }

        if (state == null) {
            state = "";
        }

        if (year == null) {
            year = now.getYear();
        }

        if (month == null) {
            month = now.getMonthValue();
        }

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = LocalDate.of(year, month, Month.of(month).maxLength());

        if (day != null) {
            startDate = LocalDate.of(year, month, day);
            endDate = LocalDate.of(year, month, day);
        }

        return
                this.getDefaultHolidayManager(country)
                    .getHolidays(
                            startDate,
                            endDate,
                            country,
                            state
                    );

    }

}
