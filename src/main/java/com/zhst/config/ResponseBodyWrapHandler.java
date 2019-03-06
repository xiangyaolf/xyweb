package com.zhst.config;

import com.zhst.config.excptions.ExceptionConstance;
import com.zhst.config.excptions.GeneralException;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * ResponseBody包装类，返回Result
 * Created by yunbinhuang on 2015/10/13.
 */
public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler delegate;

    public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        RespResult result = null;
        if (returnValue instanceof Exception) {
            if (returnValue instanceof GeneralException) {
                GeneralException e = (GeneralException) returnValue;
                result = new RespResult(e.getErrorCode(), e.getMessage(), null);
            } else {
                Exception e = (Exception) returnValue;
                result = new RespResult(ExceptionConstance.SYS_ERROR, e.getMessage(), null);
            }
        } else {
            if (!(returnValue instanceof RespResult)) {
                result = new RespResult(returnValue);
            }
        }
        delegate.handleReturnValue(result, returnType, mavContainer, webRequest);
    }
}
