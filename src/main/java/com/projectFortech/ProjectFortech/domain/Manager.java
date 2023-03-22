package com.projectFortech.ProjectFortech.domain;

import javax.persistence.*;

import static com.projectFortech.ProjectFortech.enums.Role.MANAGER;

@Entity
@DiscriminatorValue(MANAGER)
public class Manager extends User {

}
