package com.github.gw.gateway.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.github.gw.gateway.common.ActionEnum;
import com.github.gw.common.gateway.domain.GatewayLog;
import com.github.gw.gateway.log.listener.GatewayRequestLogApplicationEvent;
import io.netty.buffer.UnpooledByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.MediaType;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 记录日志到ES中的公用方法
 *
 * @author li7hai26@gmail.com
 * @date 2021/12/17
 */
@Slf4j
public class LogUtils {

    public static final List<MediaType> legalLogMediaTypes = Arrays.asList(
            MediaType.TEXT_XML,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_JSON_UTF8,
            MediaType.TEXT_PLAIN,
            MediaType.TEXT_XML,
            MediaType.MULTIPART_FORM_DATA);

    @SuppressWarnings("unchecked")
    public static <T extends DataBuffer> T logging(GatewayLog gatewayLog, T buffer, ActionEnum actionEnum, ActionEnum typeEnum) {
        GatewayRequestLogApplicationEvent event = new GatewayRequestLogApplicationEvent(gatewayLog.getId(), gatewayLog, actionEnum);
        InputStream dataBuffer = buffer.asInputStream();
//        byte[] bytes = toByteArray(dataBuffer);
//        使用hutool方法进行优化
        byte[] bytes = IoUtil.readBytes(dataBuffer);
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
        String body = new String(bytes);
        switch (typeEnum) {
            case REQUEST:
                event.getGatewayLog().setRequestBody(body);
                break;
            case RESPONSE:
                event.getGatewayLog().setResponseBody(body);
                break;
            default:
                log.error("非法参数类型,不记录数据体");
        }

        SpringUtil.publishEvent(event);
        DataBufferUtils.release(buffer);
        return (T) nettyDataBufferFactory.wrap(bytes);
    }

    private static byte[] toByteArray(InputStream inStream) {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100]; //buff用于存放循环读取的临时数据
        int rc = 0;
        byte[] in_b = new byte[]{};
        try {
            while ((rc = inStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            in_b = swapStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return in_b;
    }
}