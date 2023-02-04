package com.mjdsoftware.user;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("priv")
public class PrivilegedUser extends User {

    @Column(name="priv_level")
    private Integer privilegeLevel;

}
