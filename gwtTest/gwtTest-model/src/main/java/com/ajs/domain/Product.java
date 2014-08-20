package com.ajs.domain;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 08/07/2013
 * Time: 21:11
 * To change this template use File | Settings | File Templates.
 */

import javax.persistence.*;

@Entity
@Table(name="PRODUCT")
public class Product extends PersistentObject {

    public Product(){}

    private String name;
    private String description;


    @Column(name="NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
