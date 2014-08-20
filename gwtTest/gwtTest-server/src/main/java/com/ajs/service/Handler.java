package com.ajs.service;

import com.ajs.shared.SimpleResponse;
import com.ajs.shared.dto.RpcDto;
import com.ajs.shared.AppResponse;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 19:47
 * To change this template use File | Settings | File Templates.
 */
public interface Handler<T extends RpcDto> {
    public Class getIncomingCommandClass();
    public AppResponse execute(T dto) throws Exception;

}
