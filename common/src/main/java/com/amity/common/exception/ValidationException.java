package com.amity.common.exception;

import com.amity.common.dto.BaseErrorDTO;

/**
 * Created by Amity on 2020/12/23 15:26
 */
public class ValidationException extends ServiceException{

    private String field;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, String code) {
        super(message, code);
    }

    public ValidationException(String message, String code, String field) {
        super(message, code);
        this.field = field;
    }

    public ValidationException(String message, String code, BaseErrorDTO baseErrorDTO) {
        super(message, code, baseErrorDTO);
    }

    public ValidationException(String message, String code, String field, BaseErrorDTO baseErrorDTO) {
        super(message, code, baseErrorDTO);
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
