package de.riagade.warframe.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateHelper {

    public static LocalDateTime parseTimestamp(String timestamp, ZoneId zoneId) {
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(Long.parseLong(timestamp)),
                zoneId);
    }
}
