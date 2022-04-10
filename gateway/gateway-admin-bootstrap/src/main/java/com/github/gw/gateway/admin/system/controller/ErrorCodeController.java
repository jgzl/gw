package com.github.gw.gateway.admin.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.gw.common.core.model.R;
import com.github.gw.common.model.errorcode.convert.ErrorCodeConvert;
import com.github.gw.common.model.errorcode.dataobject.ErrorCodeDO;
import com.github.gw.common.model.errorcode.vo.ErrorCodeCreateReqVO;
import com.github.gw.common.model.errorcode.vo.ErrorCodePageReqVO;
import com.github.gw.common.model.errorcode.vo.ErrorCodeRespVO;
import com.github.gw.common.model.errorcode.vo.ErrorCodeUpdateReqVO;
import com.github.gw.gateway.admin.system.service.ErrorCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.github.gw.common.core.model.R.success;

@Api(tags = "管理后台 - 错误码")
@RestController
@RequestMapping("/system/error-code")
@Validated
public class ErrorCodeController {

    @Resource
    private ErrorCodeService errorCodeService;

    @PostMapping("/create")
    @ApiOperation("创建错误码")
//    @PreAuthorize("@pms.hasPermission('system:error-code:create')")
    public R<Long> createErrorCode(@Valid @RequestBody ErrorCodeCreateReqVO createReqVO) {
        return R.success(errorCodeService.createErrorCode(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新错误码")
//    @PreAuthorize("@pms.hasPermission('system:error-code:update')")
    public R<Boolean> updateErrorCode(@Valid @RequestBody ErrorCodeUpdateReqVO updateReqVO) {
        errorCodeService.updateErrorCode(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除错误码")
//    @PreAuthorize("@pms.hasPermission('system:error-code:delete')")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    public R<Boolean> deleteErrorCode(@RequestParam("id") Long id) {
        errorCodeService.deleteErrorCode(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得错误码")
//    @PreAuthorize("@pms.hasPermission('system:error-code:query')")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    public R<ErrorCodeRespVO> getErrorCode(@RequestParam("id") Long id) {
        ErrorCodeDO errorCode = errorCodeService.getErrorCode(id);
        return success(ErrorCodeConvert.INSTANCE.convert(errorCode));
    }

    @GetMapping("/page")
    @ApiOperation("获得错误码分页")
//    @PreAuthorize("@pms.hasPermission('system:error-code:query')")
    public R<IPage<ErrorCodeRespVO>> getErrorCodePage(@Valid ErrorCodePageReqVO pageVO) {
        IPage<ErrorCodeDO> pageResult = errorCodeService.getErrorCodePage(pageVO);
        return success(ErrorCodeConvert.INSTANCE.convertPage(pageResult));
    }
}
