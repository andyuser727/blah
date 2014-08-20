package com.ajs.shared;

import com.ajs.shared.Test;

/**
 * Created with IntelliJ IDEA.
 * User: smithaj
 * Date: 26/02/2014
 * Time: 12:59
 * To change this template use File | Settings | File Templates.
 */
public class TestImpl implements Test {

    String message;

    public TestImpl(){

    }

    public TestImpl(String message){
        this.message = message;

    }

    @Override
    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

}
