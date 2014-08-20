package com.ajs.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 06/07/2013
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public class RpcDto implements Serializable, IsSerializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
