package com.github.gw.gateway.admin.system.convert;

public interface BaseConvert<Vo,Do> {
    Vo convertDo2Vo(Do model);
    Do convertVo2Do(Vo model);
}
