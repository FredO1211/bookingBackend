package com.github.fredO1211.BookingBackend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DateProvider{

    private static void validData(LocalDate startDay, LocalDate endDay){
        if(endDay.isBefore(startDay)){
            throw new IllegalArgumentException();
        }
    }

    public static List<LocalDate> getDatesBetweenOthers(LocalDate startDay, LocalDate endDay){
        validData(startDay,endDay);

        return startDay.datesUntil(endDay)
                .filter(date->!date.equals(startDay))
                .collect(Collectors.toList());
    }
    public static List<LocalDate> getOtherDates(LocalDate currentDateStartDay,LocalDate currentDateEndDay,LocalDate newDateStartDay,LocalDate newDateEndDay){
        List<LocalDate> currentDates = getDatesListIncludingTheExtremes(currentDateStartDay,currentDateEndDay);
        List<LocalDate> newDates = getDatesListIncludingTheExtremes(newDateStartDay,newDateEndDay);
        return newDates.stream()
                .filter(date->!currentDates.contains(date))
                .collect(Collectors.toList());

    }

    public static List<LocalDate> getDatesListIncludingTheExtremes(LocalDate startDay, LocalDate endDay){
        validData(startDay,endDay);
        List<LocalDate> currentDates = startDay.datesUntil(endDay).collect(Collectors.toList());
        currentDates.add(endDay);
        return currentDates;
    }
}
