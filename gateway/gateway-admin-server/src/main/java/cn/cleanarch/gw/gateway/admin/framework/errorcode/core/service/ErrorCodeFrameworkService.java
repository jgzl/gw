package cn.cleanarch.gw.gateway.admin.framework.errorcode.core.service;

import cn.cleanarch.gw.common.model.errorcode.dto.ErrorCodeAutoGenerateReqDTO;
import cn.cleanarch.gw.common.model.errorcode.dto.ErrorCodeRespDTO;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 错误码 Framework Service 接口
 *
 * @author lihaifeng
 */
public interface ErrorCodeFrameworkService {

    /**
     * 自动创建错误码
     *
     * @param autoGenerateDTOs 错误码信息
     */
    void autoGenerateErrorCodes(@Valid List<ErrorCodeAutoGenerateReqDTO> autoGenerateDTOs);

    /**
     * 增量获得错误码数组
     *
     * 如果 minUpdateTime 为空时，则获取所有错误码
     *
     * @param applicationName 应用名
     * @param minUpdateTime 最小更新时间
     * @return 错误码数组
     */
    List<ErrorCodeRespDTO> getErrorCodeList(String applicationName, Date minUpdateTime);

}
