package com.ajs.client.image;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 14/12/2013
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */
public interface AppResources extends ClientBundle {
    public static final AppResources INSTANCE =  GWT.create(AppResources.class);

//    @Source("my.css")
//    public CssResource css();
//
//    @Source("config.xml")
//    public TextResource initialConfiguration();

//    @Source("../../../../../../../gwtTest-server/src/main/webapp/deleteIcon.jpeg")
    @Source("deleteIcon16.jpg")
    public ImageResource deleteIcon();
}