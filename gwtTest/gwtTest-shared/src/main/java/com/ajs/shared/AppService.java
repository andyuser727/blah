package com.ajs.shared;

import com.ajs.shared.dto.RpcDto;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("app.rpc")
public interface AppService extends RemoteService {
	AppResponse callServer(RpcDto dto) throws Exception;
    SimpleResponse callServerSimple(RpcDto dto) throws Exception;
}
