package com.flask.app.enums;

public enum EmploymentCapacityEnum {
    PART_TIME("part-time"), FULL_TIME("full-time");

    EmploymentCapacityEnum(String capacity) {
        this.capacity = capacity;
    }

    private String capacity;

    public String getCapacity() {
        return capacity;
    }
}
