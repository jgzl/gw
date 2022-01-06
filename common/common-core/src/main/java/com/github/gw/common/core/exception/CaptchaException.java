package com.github.gw.common.core.exception;

/**
 * 验证码错误异常类
 *
 * @author li7hai26@gmail.com
 */
public class CaptchaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CaptchaException(String msg) {
        super(msg);
    }
}
