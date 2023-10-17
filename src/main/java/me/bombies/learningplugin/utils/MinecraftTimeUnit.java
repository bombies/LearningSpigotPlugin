package me.bombies.learningplugin.utils;

public class MinecraftTimeUnit {

    public static class TICKS {
        public static final long SECOND = 20;
        public static final long MINUTE = 1200;
        public static final long HOUR = 72000;
        public static final long DAY = 1728000;
        public static final long WEEK = 12096000;
        public static final long MONTH = 48384000;
        public static final long YEAR = 483840000;

        public static long toSeconds(long ticks) {
            return ticks / SECOND;
        }

        public static long toMinutes(long ticks) {
            return ticks / MINUTE;
        }

        public static long toHours(long ticks) {
            return ticks / HOUR;
        }

        public static long toDays(long ticks) {
            return ticks / DAY;
        }

        public static long toWeeks(long ticks) {
            return ticks / WEEK;
        }

        public static long toMonths(long ticks) {
            return ticks / MONTH;
        }

        public static long toYears(long ticks) {
            return ticks / YEAR;
        }

        public static long fromSeconds(long seconds) {
            return seconds * SECOND;
        }

        public static long fromMinutes(long minutes) {
            return minutes * MINUTE;
        }

        public static long fromHours(long hours) {
            return hours * HOUR;
        }

        public static long fromDays(long days) {
            return days * DAY;
        }

        public static long fromWeeks(long weeks) {
            return weeks * WEEK;
        }

        public static long fromMonths(long months) {
            return months * MONTH;
        }

        public static long fromYears(long years) {
            return years * YEAR;
        }
    }
}
