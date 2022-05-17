package cn.cleanarch.gw.common.model.base;

public interface BaseConvert<Vo, Do> {
    Vo convertDo2Vo(Do model);

    Do convertVo2Do(Vo model);
}
