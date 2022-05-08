package com.github.jgzl.gw.gateway.admin.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.jgzl.gw.common.model.errorcode.dataobject.ErrorCodeDO;
import com.github.jgzl.gw.common.model.errorcode.vo.ErrorCodeCreateReqVO;
import com.github.jgzl.gw.common.model.errorcode.vo.ErrorCodeExportReqVO;
import com.github.jgzl.gw.common.model.errorcode.vo.ErrorCodePageReqVO;
import com.github.jgzl.gw.common.model.errorcode.vo.ErrorCodeUpdateReqVO;
import com.github.jgzl.gw.gateway.admin.framework.errorcode.core.service.ErrorCodeFrameworkService;

import javax.validation.Valid;
import java.util.List;

/**
 * 错误码 Service 接口
 *
 * @author lihaifeng
 */
public interface ErrorCodeService extends ErrorCodeFrameworkService {

    /**
     * 创建错误码
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createErrorCode(@Valid ErrorCodeCreateReqVO createReqVO);

    /**
     * 更新错误码
     *
     * @param updateReqVO 更新信息
     */
    void updateErrorCode(@Valid ErrorCodeUpdateReqVO updateReqVO);

    /**
     * 删除错误码
     *
     * @param id 编号
     */
    void deleteErrorCode(Long id);

    /**
     * 获得错误码
     *
     * @param id 编号
     * @return 错误码
     */
    ErrorCodeDO getErrorCode(Long id);

    /**
     * 获得错误码分页
     *
     * @param pageReqVO 分页查询
     * @return 错误码分页
     */
    IPage<ErrorCodeDO> getErrorCodePage(ErrorCodePageReqVO pageReqVO);

    /**
     * 获得错误码列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 错误码列表
     */
    List<ErrorCodeDO> getErrorCodeList(ErrorCodeExportReqVO exportReqVO);

}
