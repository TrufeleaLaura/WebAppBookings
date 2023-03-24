package com.projectFortech.ProjectFortech.enums;

import java.util.EnumSet;

public enum Categories {
  COMEDY("comedy"),
    DRAMA("drama"),
    HORROR("horror"),
    ROMANCE("romance"),
    ANIMATION("animation"),
    CRIME("crime"),
    DOCUMENTARY("documentary"),
    FAMILY("family");

    private final String category;


    Categories(String category) {
        this.category = category;
    }


    public String getCategory() {
        return category;
    }

    public static Categories fromString(String text) {
        return EnumSet.allOf(Categories.class).stream()
                .filter(category -> category.category.equalsIgnoreCase(text))
                .findAny()
                .orElse(COMEDY);
      }
}
