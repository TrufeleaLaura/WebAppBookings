package com.projectFortech.ProjectFortech.domain;

import lombok.AllArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static com.projectFortech.ProjectFortech.enums.Role.CLIENT;
import static com.projectFortech.ProjectFortech.enums.Role.MANAGER;

@Entity
@DiscriminatorValue(CLIENT)
public class Client extends User {

        public Client(String name, String surname, String email, String password) {
            super(name, surname, email, password, CLIENT);
        }

        public Client() {
        }

}
