package com.github.gw.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/24
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

    /**
     * 左侧菜单
     */
    LEFT_MENU("0", "left"),

    /**
     * 按钮
     */
    BUTTON("1", "button");

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;

}
