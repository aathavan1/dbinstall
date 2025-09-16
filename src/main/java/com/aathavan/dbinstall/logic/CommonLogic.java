package com.aathavan.dbinstall.logic;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CommonLogic {
    public String getFinYearByDate() {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        year %= 100;
        if (date.getMonthValue() <= 3) {
            return String.valueOf(((year - 1) * 100) + (year));
        } else {
            return String.valueOf((year * 100) + (year + 1));
        }
    }
}
