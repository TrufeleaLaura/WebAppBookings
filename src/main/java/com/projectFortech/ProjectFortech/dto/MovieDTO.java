package com.projectFortech.ProjectFortech.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MovieDTO {

    private Integer movieId;

    private String name;

    private String category;

    private String type;

    private String imageUrl;

    public MovieDTO(String name, String category, String type, String imageUrl) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.imageUrl = imageUrl;
    }

    public MovieDTO() {

    }
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + movieId +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", type=" + type +
                '}';
    }
}
