package com.target.camelvmmsync.response.Location;

public enum DayOfWeek {
    SUNDAY("SUNDAY"),
    MONDAY("MONDAY"),
    TUESDAY("TUESDAY"),
    WEDNESDAY("WEDNESDAY"),
    THURSDAY("THURSDAY"),
    FRIDAY("FRIDAY"),
    SATURDAY("SATURDAY");

    private final String value;

    DayOfWeek(String value) {
        this.value = value;
    }
    public static DayOfWeek fromValue(String value){
        for(DayOfWeek dayOfWeek : values()){
            if(dayOfWeek.value.equalsIgnoreCase(value)) {
                return dayOfWeek;
            }
        }
        return null;
    }
    public String getValue() {
        return this.value;
    }
}

