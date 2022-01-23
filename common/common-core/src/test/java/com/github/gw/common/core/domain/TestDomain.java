package com.github.gw.common.core.domain;

import com.github.gw.common.core.annotation.Excel;
import lombok.Data;

/**
 * @author lihaifeng
 * @date 2022/01/18
 */
@Data
public class TestDomain {
    private Long id;
    @Excel(name = "用户名")
    private String userName;
    @Excel(name = "密码")
    private String password;
    @Excel(name = "别名")
    private String nickName;
    @Excel(name = "年龄",cellType = Excel.ColumnType.NUMERIC)
    private Integer age;
    @Excel(name = "性别")
    private String sex;
}
