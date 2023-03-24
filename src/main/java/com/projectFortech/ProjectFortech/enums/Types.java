package com.projectFortech.ProjectFortech.enums;

import java.util.EnumSet;

public enum Types {
    TWOD("2D"),THREED("3D"),FOURD("4D");
    private final String type;


    Types(String type) {
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public static Types fromString(String text) {
        EnumSet.allOf(Types.class).stream()
                .filter(type -> type.type.equalsIgnoreCase(text))
                .findAny()
                .orElse(TWOD);
        for (Types b : Types.values()) {
          if (b.type.equalsIgnoreCase(text)) {
            return b;
          }
        }
        return null;
      }
}
