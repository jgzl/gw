package com.github.gw.gateway.admin.system.service;

import com.github.gw.common.core.model.R;

/**
 * @author li7hai26@gmail.com
 * @date 2018/11/14
 */
public interface MobileService {

    /**
     * 发送手机验证码
     *
     * @param mobile mobile
     * @return code
     */
    R<Boolean> sendSmsCode(String mobile);

}
