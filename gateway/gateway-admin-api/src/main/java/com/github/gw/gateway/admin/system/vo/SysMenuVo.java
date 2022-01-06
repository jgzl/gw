package com.github.gw.gateway.admin.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

/**
 * 菜单
 * @author li7hai26@gmail.com
 * @date 2021/12/23
 */
@Data
public class SysMenuVo {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long menuId;
    private String menuUrl;
    private String menuName;
    private String icon;
    private String tip;
    private Boolean cacheable;
    private String parentPath;
    private List<SysMenuVo> children;
}