package com.github.gw.common.core.convert;

public interface BaseConvert<Vo, Do> {
    Vo convertDo2Vo(Do model);

    Do convertVo2Do(Vo model);
}
