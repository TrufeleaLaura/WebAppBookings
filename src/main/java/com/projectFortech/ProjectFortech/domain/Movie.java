package com.projectFortech.ProjectFortech.domain;

import com.projectFortech.ProjectFortech.enums.Categories;
import com.projectFortech.ProjectFortech.enums.Types;

import javax.persistence.*;

@Entity
@Table(name="movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer movieId;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Categories category;

    @Column
    @Enumerated(EnumType.STRING)
    private Types type;

    @Column(name="image_url")
    private String imageUrl;

    public Movie(String name, Categories category, Types type, String imageUrl) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.imageUrl = imageUrl;
    }

    public Movie() {

    }

    public Integer getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }



    public void setType(Types type) {
        this.type = type;
    }

    public Types getType() {
        return type;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
