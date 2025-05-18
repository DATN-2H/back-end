package com.menuplus.backend.library.util;

import static java.time.temporal.ChronoUnit.DAYS;

import com.menuplus.backend.library.common.Constant;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import org.springframework.util.StringUtils;

public class DateUtil {

    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(
        Constant.DATE_FORMAT
    );
    static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(
        Constant.TIME_FORMAT
    );
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
        Constant.DATE_TIME_FORMAT
    );

    public static String format(LocalDateTime dateTime) {
        if (dateTime == null) return "";
        return dateTimeFormatter.format(dateTime);
    }

    public static String format(LocalDateTime dateTime, String format) {
        if (dateTime == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(dateTime);
    }

    public static String format(LocalDate date) {
        return format(date, Constant.DATE_FORMAT);
    }

    public static String format(LocalDate date, String format) {
        if (date == null) return "";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    public static LocalDateTime parseDateTime(String dateTime) {
        if (!StringUtils.hasText(dateTime)) return null;
        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }

    public static LocalDate parseDate(String date) {
        if (!StringUtils.hasText(date)) return null;
        return LocalDate.parse(date, dateFormatter);
    }

    public static LocalDateTime getStartTimeUTC(
        LocalDate localDate,
        String zoneId
    ) {
        return convertToUTC(localDate.atStartOfDay(), zoneId);
    }

    public static LocalDateTime convert(Date date) {
        return date
            .toInstant()
            .atZone(ZoneId.of(Constant.UTC_TIMEZONE))
            .toLocalDateTime();
    }

    public static Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(
            localDateTime.atZone(ZoneId.of(Constant.UTC_TIMEZONE)).toInstant()
        );
    }

    public static LocalDateTime convertToUTC(
        LocalDateTime localDateTime,
        String zoneId
    ) {
        if (localDateTime == null) return null;
        ZoneId oldZone = ZoneId.of(zoneId);
        ZoneId utcZone = ZoneId.of(Constant.UTC_TIMEZONE);
        return localDateTime
            .atZone(oldZone)
            .withZoneSameInstant(utcZone)
            .toLocalDateTime();
    }

    public static String formatMinute(long numMinute) {
        long numHour = numMinute / 60;
        numMinute = numMinute - numHour * 60;
        return String.format("%d HRS %d MINS", numHour, numMinute);
    }

    public static LocalDateTime getMin(LocalDateTime... dateTimes) {
        return Arrays.stream(dateTimes)
            .filter(Objects::nonNull)
            .min(LocalDateTime::compareTo)
            .orElse(null);
    }

    public static LocalDateTime getMax(LocalDateTime... dateTimes) {
        return Arrays.stream(dateTimes)
            .filter(Objects::nonNull)
            .max(LocalDateTime::compareTo)
            .orElse(null);
    }
}
