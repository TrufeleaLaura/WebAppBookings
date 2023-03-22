package com.projectFortech.ProjectFortech.enums;

public enum Types {
    TWOD("2D"),THREED("3D"),FOURD("4D");
    private final String type;


    Types(String type) {
        this.type = type;
    }


    public String getType() {
        return type;
    }
}
