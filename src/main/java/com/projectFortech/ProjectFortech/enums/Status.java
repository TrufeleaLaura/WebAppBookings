package com.projectFortech.ProjectFortech.enums;

public enum Status {
    AVAILIBLE("available"),
    UNAVAILIBLE("occupied");;
    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
