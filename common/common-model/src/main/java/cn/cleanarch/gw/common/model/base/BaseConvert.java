package cn.cleanarch.gw.common.model.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface BaseConvert<Vo, Do> {
    Vo convertDo2Vo(Do model);

    Do convertVo2Do(Vo model);

    Page<Vo> convertDo2Vo(Page<Do> model);

    Page<Do> convertVo2Do(Page<Vo> model);
}
