package cn.cleanarch.gw.common.model.errorcode.convert;

import cn.cleanarch.gw.common.model.errorcode.dataobject.ErrorCodeDO;
import cn.cleanarch.gw.common.model.errorcode.dto.ErrorCodeAutoGenerateReqDTO;
import cn.cleanarch.gw.common.model.errorcode.dto.ErrorCodeRespDTO;
import cn.cleanarch.gw.common.model.errorcode.vo.ErrorCodeCreateReqVO;
import cn.cleanarch.gw.common.model.errorcode.vo.ErrorCodeRespVO;
import cn.cleanarch.gw.common.model.errorcode.vo.ErrorCodeUpdateReqVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 错误码 Convert
 *
 * @author lihaifeng
 */
@Mapper
public interface ErrorCodeConvert {

    ErrorCodeConvert INSTANCE = Mappers.getMapper(ErrorCodeConvert.class);

    ErrorCodeDO convert(ErrorCodeCreateReqVO bean);

    ErrorCodeDO convert(ErrorCodeUpdateReqVO bean);

    ErrorCodeRespVO convert(ErrorCodeDO bean);

    List<ErrorCodeRespVO> convertList(List<ErrorCodeDO> list);

    Page<ErrorCodeRespVO> convertPage(IPage<ErrorCodeDO> page);

    ErrorCodeDO convert(ErrorCodeAutoGenerateReqDTO bean);

    List<ErrorCodeRespDTO> convertList03(List<ErrorCodeDO> list);

}
