package com.aifenxiang.entrancehall.logging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author zhouhao
 * @since 1.0
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class AccessLoggerResponse {
    private String id;

    private long responseTime;

    private Object response;
}
