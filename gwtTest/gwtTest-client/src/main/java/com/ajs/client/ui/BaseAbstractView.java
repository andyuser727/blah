package com.ajs.client.ui;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.Composite;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 21/12/2013
 * Time: 18:19
 * To change this template use File | Settings | File Templates.
 */
public class BaseAbstractView extends Composite {
    protected Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }
}
