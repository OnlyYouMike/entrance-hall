package com.aifenxiang.entrancehall.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zj
 * @create: 2018-08-21 23:09
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AiUserException extends RuntimeException {

    private String msg;
}
