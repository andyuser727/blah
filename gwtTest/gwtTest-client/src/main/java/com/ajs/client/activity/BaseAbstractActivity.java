package com.ajs.client.activity;

import com.ajs.client.place.ActivityPlace;
import com.ajs.client.ui.BaseAbstractView;
import com.google.gwt.activity.shared.AbstractActivity;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 12/12/2013
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseAbstractActivity<V extends BaseAbstractView> extends AbstractActivity{

    private Map<String, String> properties;
    protected final V display;

    public BaseAbstractActivity(V display){
        this.display = display;
        display.setActivity(this);
    }

    public void init(ActivityPlace place) {
        this.properties = place.getProperties();
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}
