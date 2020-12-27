package com.amity.common.exception;

import com.amity.common.dto.BaseErrorDTO;

import java.util.List;

/**
 * Created by Amity on 2020/12/23 15:17
 */
public class ServiceException extends RuntimeException{

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息，一般是 错误的占位符对应的值
     */
    private BaseErrorDTO baseErrorDTO;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, String code) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, String code, BaseErrorDTO baseErrorDTO) {
        super(message);
        this.code = code;
        this.baseErrorDTO = baseErrorDTO;
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message, String code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(String message, String code, Throwable cause, BaseErrorDTO baseErrorDTO) {
        super(message, cause);
        this.code = code;
        this.baseErrorDTO = baseErrorDTO;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BaseErrorDTO getBaseErrorDTO() {
        return baseErrorDTO;
    }

    public void setBaseErrorDTO(BaseErrorDTO baseErrorDTO) {
        this.baseErrorDTO = baseErrorDTO;
    }
}
