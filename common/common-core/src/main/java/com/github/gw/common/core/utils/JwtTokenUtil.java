package com.github.gw.common.core.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.github.gw.common.core.constant.TokenConstants;
import lombok.experimental.UtilityClass;

import java.util.Map;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/24
 */
@UtilityClass
public class JwtTokenUtil {

    private static final byte[] keyBytes = StrUtil.bytes(TokenConstants.SECRET);

    public String createToken(Map<String, Object> map) {
        return JWTUtil.createToken(map, keyBytes);
    }

    public JWTPayload getPayLoad(String token) {
        return JWTUtil.parseToken(token).getPayload();
    }

    public boolean verify(String token) {
        return JWTUtil.verify(token, keyBytes);
    }
}
