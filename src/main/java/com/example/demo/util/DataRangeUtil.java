package com.example.demo.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateRangeUtil {
    public static List<LocalDate> daysBetween(LocalDate startInclusive, LocalDate endInclusive) {
        List<LocalDate> dates = new ArrayList<>();
        if (startInclusive == null || endInclusive == null) return dates;
        LocalDate cur = startInclusive;
        while (!cur.isAfter(endInclusive)) {
            dates.add(cur);
            cur = cur.plusDays(1);
        }
        return dates;
    }
}
