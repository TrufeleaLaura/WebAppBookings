package com.projectFortech.ProjectFortech.domain;

import javax.persistence.*;

@Entity
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roomId;
    private int places;
    private String name;

    public Room() {

    }

    public Integer getRoomId() {
        return roomId;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room(int places, String name) {
        this.places = places;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", places=" + places +
                ", name='" + name + '\'' +
                '}';
    }
}
