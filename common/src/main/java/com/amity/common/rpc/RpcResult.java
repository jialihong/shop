package com.amity.common.rpc;

import com.alibaba.fastjson.JSON;
import com.amity.common.dto.BaseErrorDTO;

import java.io.Serializable;

/**
 * Created by Amity on 2020/12/23 15:33
 */
public class RpcResult<T> implements Serializable {
    public static final String CODE_SUCCESS = "0";

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回值，返回结果对象。
     */
    private T data;

    /**
     * 操作消息
     */
    private String message;
    /**
     * 详细错误消息，比如说异常
     */
    private String error;

    /**
     * 错误信息的附带信息，一般用于承载错误的占位符对应的值。使用此参数时，最好在 API 文档中加以说明，以便于调用方利用
     */
    private BaseErrorDTO errorDTO;

    public RpcResult() {
    }

    public RpcResult(T data) {
        this.code = CODE_SUCCESS;
        this.data = data;
    }

    public RpcResult(T data, String message) {
        this.code = CODE_SUCCESS;
        this.data = data;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public BaseErrorDTO getErrorDTO() {
        return errorDTO;
    }

    public void setErrorDTO(BaseErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
