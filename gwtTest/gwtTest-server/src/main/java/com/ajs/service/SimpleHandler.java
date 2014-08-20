package com.ajs.service;

import com.ajs.shared.SimpleResponse;
import com.ajs.shared.dto.RpcDto;

/**
 * Created with IntelliJ IDEA.
 * User: smithaj
 * Date: 28/02/2014
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */
public interface SimpleHandler<T extends RpcDto> {
    public Class getIncomingCommandClass();
    public SimpleResponse executeSimple(T dto) throws Exception;

}
