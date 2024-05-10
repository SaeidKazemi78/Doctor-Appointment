package com.corporation.doctorsappointment.utils;

import org.springframework.data.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {

    /**
     * This method breaks down the date into 30 minutes appointment times
     */
    public static List<Pair<LocalDateTime, LocalDateTime>> breakDownInto30Minutes(LocalDateTime startTime, LocalDateTime endTime) {
        List<Pair<LocalDateTime, LocalDateTime>> response = new ArrayList<>();
        while (startTime.isBefore(endTime)) {
            LocalDateTime start = startTime;
            LocalDateTime end = startTime.plusMinutes(30);
            startTime = end;
            if (end.isAfter(endTime)) {
                continue;
            }
            response.add(Pair.of(start, end));
        }
        return response;
    }

    public static void main(String[] args) {
        LocalDateTime startTime = LocalDateTime.now().minusMinutes(8);
        LocalDateTime endTime = LocalDateTime.now().plusHours(2).plusMinutes(20);
        List<Pair<LocalDateTime, LocalDateTime>> responseFinal = breakDownInto30Minutes(startTime, endTime);
        responseFinal.forEach(eachOne -> {
            System.out.println("\n " + eachOne.getFirst().toLocalTime() + " |  " + eachOne.getSecond().toLocalTime() + "\n");
        });
    }
}
