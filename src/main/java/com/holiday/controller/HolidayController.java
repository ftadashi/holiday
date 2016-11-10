package com.holiday.controller;

import com.holiday.component.HolidayProvider;
import de.jollyday.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;

@RestController
public class HolidayController {

    @Autowired
    private HolidayProvider holidayProvider;

    @RequestMapping(value = "/isHoliday/{country}/{state}/{year}/{month}/{day}", method = RequestMethod.GET)
    public Boolean isHoliday(
            @PathVariable(name = "country") String country,
            @PathVariable(name = "state") String state,
            @PathVariable(name = "year") Integer year,
            @PathVariable(name = "month") Integer month,
            @PathVariable(name = "day") Integer day) {

        LocalDate localDate = LocalDate.of(year, month, day);

        return
                this.holidayProvider
                        .isHoliday(
                                country,
                                localDate,
                                state
                        );

    }

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public Set<String> countries() {

        return
                this.holidayProvider
                        .getCountries();

    }

    @RequestMapping(value = "/countries/{country}/states", method = RequestMethod.GET)
    public Set<String> states(
            @PathVariable(name = "country") String country) {

        return
                this.holidayProvider
                        .getStates(country);

    }

    @RequestMapping(value = "/findHolidays", method = RequestMethod.GET)
    public Set<Holiday> findHolidays(
            @RequestParam(name = "country", required = false) String country,
            @RequestParam(name = "state", required = false) String state,
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "month", required = false) Integer month,
            @RequestParam(name = "day", required = false) Integer day) {

        return
                this.holidayProvider
                    .findHolidays(
                            country,
                            state,
                            year,
                            month,
                            day
                    );

    }

}
