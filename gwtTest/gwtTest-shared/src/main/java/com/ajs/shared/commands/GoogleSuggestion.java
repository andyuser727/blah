package com.ajs.shared.commands;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.SuggestOracle;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 16/12/2013
 * Time: 20:44
 * To change this template use File | Settings | File Templates.
 */
public class GoogleSuggestion implements SuggestOracle.Suggestion, Serializable, IsSerializable {
    private static final long serialVersionUID = 1L;

    private String itemCode;

    private String displayedText;

    @SuppressWarnings("unused")
    private GoogleSuggestion() {
    }

    public GoogleSuggestion(String itemCode, String displayedText) {
        super();
        this.itemCode = itemCode;
        this.displayedText = displayedText;
    }

    public String getItemCode() {
        return itemCode;
    }

    @Override
    public String getDisplayString() {
        return displayedText;
    }

    @Override
    public String getReplacementString() {
        return displayedText;
    }

}