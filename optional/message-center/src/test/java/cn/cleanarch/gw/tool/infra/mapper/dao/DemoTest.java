package cn.cleanarch.gw.tool.infra.mapper.dao;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

@Slf4j
public class DemoTest {
    @Test
    public void parseString() {
        String str = "[{\"domain\":\"weeu.m\",\"name\":\"xs\"}],[{\"domain\":\"weng.x\",\"name\":\"xm\"}]";
        str = "["+str+"]";
        List<List<Map<String,String>>> list = JSONObject.parseObject(str,new TypeReference<List<List<Map<String,String>>>>(){});
        for (List<Map<String, String>> oneList : list) {
            for (Map<String, String> map : oneList) {
                map.forEach((k,v)->log.info("key:{},value:{}",k,v));
            }
        }
    }
}
