package com.ajs.domain;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 12/12/2013
 * Time: 18:49
 * To change this template use File | Settings | File Templates.
 */
@MappedSuperclass
public class PersistentObject {

    private Long id;

    public PersistentObject(){
    }

    @Id
    @GeneratedValue
    @Column(name="ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
