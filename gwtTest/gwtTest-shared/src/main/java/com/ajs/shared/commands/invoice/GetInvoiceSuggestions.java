package com.ajs.shared.commands.invoice;

import com.ajs.shared.dto.RpcDto;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 16/12/2013
 * Time: 20:31
 * To change this template use File | Settings | File Templates.
 */
public class GetInvoiceSuggestions extends RpcDto {

    private String partialQueryString;

    public GetInvoiceSuggestions(){}

    public GetInvoiceSuggestions(String partialQueryString){
        this.partialQueryString = partialQueryString;
    }

    public String getPartialQueryString() {
        return partialQueryString;
    }
}
