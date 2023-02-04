package com.mjdsoftware.user;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("abuse")
public class AbusiveUser extends User {

    @Column(name="abuse_level")
    private Integer abuseLevel;
}
