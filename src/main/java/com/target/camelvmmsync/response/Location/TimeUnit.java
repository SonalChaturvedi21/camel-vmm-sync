package com.target.camelvmmsync.response.Location;

public enum TimeUnit {

    HOUR("Hour"),
    DAYS("Day"),
    WEEK("Week"),
    YEAR("Year");

    private final String value;

    TimeUnit(String value) {
        this.value = value;
    }

    public static TimeUnit fromValue(String value) {
        for (TimeUnit timeUnit : values()) {
            if (timeUnit.value.equalsIgnoreCase(value)) {
                return timeUnit;
            }
        }
        return HOUR;
    }

    public String getValue() {
        return this.value;
    }
}

