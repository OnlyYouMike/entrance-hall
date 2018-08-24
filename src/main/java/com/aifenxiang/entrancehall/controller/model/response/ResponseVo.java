package com.aifenxiang.entrancehall.controller.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseVo implements Serializable {

    private static final long serialVersionUID = -2188603082451033872L;
    private int code = ResultCode.RETURN_CODE_NULL;
    private String message;
    private Object data;

}
