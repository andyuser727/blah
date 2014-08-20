package com.ajs.shared;

import com.ajs.shared.commands.GoogleSuggestion;
import com.ajs.shared.dto.RpcDto;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class SimpleResponse implements Serializable, IsSerializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<GoogleSuggestion> suggestions;

    private ArrayList<String> validationMessages;

    public SimpleResponse(){
    }

    public ArrayList<GoogleSuggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(ArrayList<GoogleSuggestion> suggestions) {
        this.suggestions = suggestions;
    }

    public ArrayList<String> getValidationMessages() {
        return validationMessages;
    }

    public void setValidationMessages(ArrayList<String> validationMessages) {
        this.validationMessages = validationMessages;
    }
}