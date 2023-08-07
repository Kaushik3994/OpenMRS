package com.qa.util;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String getCurrentDateTime(ZoneId zoneId, DateTimeFormatter dateTimeFormatter){
        return ZonedDateTime.now(zoneId)
                .toOffsetDateTime().withOffsetSameLocal(ZoneOffset.UTC)
                .format(dateTimeFormatter);
    }
}
