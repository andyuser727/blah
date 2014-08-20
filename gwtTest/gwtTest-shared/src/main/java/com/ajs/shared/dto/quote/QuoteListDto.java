package com.ajs.shared.dto.quote;

import com.ajs.shared.dto.RpcDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */
public class QuoteListDto extends RpcDto {

    List<QuoteDetailDto> quoteDetailDtoList;

    public List<QuoteDetailDto> getQuoteDetailDtoList() {
        return quoteDetailDtoList;
    }

    public void setQuoteDetailDtoList(List<QuoteDetailDto> quoteDetailDtoList) {
        this.quoteDetailDtoList = quoteDetailDtoList;
    }
}
