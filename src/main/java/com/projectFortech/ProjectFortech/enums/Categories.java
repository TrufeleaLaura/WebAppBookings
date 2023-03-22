package com.projectFortech.ProjectFortech.enums;

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
}
