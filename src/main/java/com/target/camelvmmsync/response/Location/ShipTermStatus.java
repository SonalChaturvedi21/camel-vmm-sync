package com.target.camelvmmsync.response.Location;

public enum ShipTermStatus {

    APPROVED, REJECT, REQUEST, DISABLED, REMOVED;

    public static ShipTermStatus fromValue(String value) {
        for (ShipTermStatus shipTermOperation : values()) {
            if (shipTermOperation.name().equalsIgnoreCase(value)) {
                return shipTermOperation;
            }
        }
        return null;
    }
}
