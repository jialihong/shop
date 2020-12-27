package com.amity.common.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 异常基本信息DTO
 * Created by Amity on 2020/12/23 15:19
 */
public class BaseErrorDTO implements Serializable {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
