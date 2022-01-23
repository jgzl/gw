package com.github.gw.common.core.utils.poi;

import com.github.gw.common.core.annotation.Excel;
import com.github.gw.common.core.domain.TestDomain;
import com.github.gw.common.core.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class ExcelUtilTest {

    @Test
    public void exportExcel() throws IOException {
        ExcelUtil<TestDomain> excelUtil = new ExcelUtil<>(TestDomain.class);
        List<TestDomain> testDomains = RandomUtils.randomPojoList(TestDomain.class);
        excelUtil.init(testDomains,"测试实体sheet","测试实体标题", Excel.Type.EXPORT);
        File tempFile = File.createTempFile("测试实体导出excel", ".xlsx");
        log.info("生成的临时文件为:{}",tempFile.getAbsolutePath());
        FileOutputStream outputStream = new FileOutputStream(tempFile);
        excelUtil.exportExcel(outputStream);
    }
}