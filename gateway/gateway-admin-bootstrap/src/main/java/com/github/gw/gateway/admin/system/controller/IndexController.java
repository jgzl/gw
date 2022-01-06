package com.github.gw.gateway.admin.system.controller;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.github.gw.common.core.domain.R;
import com.github.gw.common.core.utils.RandomUtils;
import com.github.gw.gateway.admin.system.vo.SysMenuVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 主页
 * @author li7hai26@gmail.com
 */
@Slf4j
@AllArgsConstructor
@RestController
public class IndexController {

    /**
     * 根节点跳转
     *
     * @return
     */
    @GetMapping("/")
    public R<String> index() {
        return R.ok("success");
    }

    /**
     * 测试-获取菜单列表
     * @return
     */
    @GetMapping(value = "getMenuList")
    public R<List<SysMenuVo>> menuList() {
        String result = "[\n" +
                "    {\n" +
                "        \"menuUrl\": \"/system\",\n" +
                "        \"menuName\": \"系统管理\",\n" +
                "        \"icon\": \"SettingIcon\",\n" +
                "        \"tip\": \"new\",\n" +
                "        \"parentPath\": \"\",\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"parentPath\": \"/system\",\n" +
                "                \"menuUrl\": \"/system/department\",\n" +
                "                \"menuName\": \"部门管理\",\n" +
                "                \"tip\": \"new\",\n" +
                "                \"cacheable\": true\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/system\",\n" +
                "                \"menuUrl\": \"/system/user\",\n" +
                "                \"menuName\": \"用户管理\",\n" +
                "                \"tip\": \"dot\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/system\",\n" +
                "                \"menuUrl\": \"/system/role\",\n" +
                "                \"menuName\": \"角色管理\",\n" +
                "                \"tip\": \"12\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/system\",\n" +
                "                \"menuUrl\": \"/system/menu\",\n" +
                "                \"menuName\": \"菜单管理\",\n" +
                "                \"cacheable\": true\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"menuUrl\": \"/list\",\n" +
                "        \"menuName\": \"列表页面\",\n" +
                "        \"icon\": \"OperationIcon\",\n" +
                "        \"parentPath\": \"\",\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"parentPath\": \"/list\",\n" +
                "                \"menuUrl\": \"/list/table-with-search\",\n" +
                "                \"menuName\": \"表格搜索\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/list\",\n" +
                "                \"menuUrl\": \"/list/table-custom\",\n" +
                "                \"menuName\": \"自定义表格\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/list\",\n" +
                "                \"menuUrl\": \"/list/list\",\n" +
                "                \"menuName\": \"普通列表\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/list\",\n" +
                "                \"menuUrl\": \"/list/card-list\",\n" +
                "                \"menuName\": \"卡片列表\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"menuUrl\": \"/form\",\n" +
                "        \"menuName\": \"表单页面\",\n" +
                "        \"tip\": \"dot\",\n" +
                "        \"icon\": \"PostcardIcon\",\n" +
                "        \"parentPath\": \"\",\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"parentPath\": \"/form\",\n" +
                "                \"menuUrl\": \"/form/base-form-view\",\n" +
                "                \"menuName\": \"基本表单\",\n" +
                "                \"cacheable\": true\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/form\",\n" +
                "                \"menuUrl\": \"/form/advance-form\",\n" +
                "                \"menuName\": \"高级表单\",\n" +
                "                \"cacheable\": true\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/form\",\n" +
                "                \"menuUrl\": \"/form/step-form\",\n" +
                "                \"menuName\": \"分步表单\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/form\",\n" +
                "                \"menuUrl\": \"/form/form-component\",\n" +
                "                \"menuName\": \"表单组件\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"menuUrl\": \"/other\",\n" +
                "        \"menuName\": \"功能/组件\",\n" +
                "        \"icon\": \"GridIcon\",\n" +
                "        \"parentPath\": \"\",\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"parentPath\": \"/other\",\n" +
                "                \"menuUrl\": \"/other/chart\",\n" +
                "                \"menuName\": \"图表\",\n" +
                "                \"children\": [\n" +
                "                    {\n" +
                "                        \"parentPath\": \"/other/chart\",\n" +
                "                        \"menuUrl\": \"/other/chart/icon\",\n" +
                "                        \"menuName\": \"图标\",\n" +
                "                        \"children\": [\n" +
                "                            {\n" +
                "                                \"parentPath\": \"/other/chart\",\n" +
                "                                \"menuUrl\": \"/other/chart/icon/icon-font\",\n" +
                "                                \"menuName\": \"IconFont\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"parentPath\": \"/other/chart\",\n" +
                "                                \"menuUrl\": \"/other/chart/icon/element-plus\",\n" +
                "                                \"menuName\": \"ElementPlus\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"parentPath\": \"/other/chart\",\n" +
                "                        \"menuUrl\": \"/other/chart/echarts\",\n" +
                "                        \"menuName\": \"echarts\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/other\",\n" +
                "                \"menuUrl\": \"/other/print\",\n" +
                "                \"menuName\": \"打印\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/other\",\n" +
                "                \"menuUrl\": \"/other/tip\",\n" +
                "                \"menuName\": \"消息提示\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/other\",\n" +
                "                \"menuUrl\": \"/other/clipboard\",\n" +
                "                \"menuName\": \"剪切板\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/other\",\n" +
                "                \"menuUrl\": \"http://www.baidu.com\",\n" +
                "                \"menuName\": \"外链\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/other\",\n" +
                "                \"menuUrl\": \"/other/cropper\",\n" +
                "                \"menuName\": \"图片裁剪\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/other\",\n" +
                "                \"menuUrl\": \"/other/qrcode\",\n" +
                "                \"menuName\": \"二维码\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/other\",\n" +
                "                \"menuUrl\": \"/other/css-animation\",\n" +
                "                \"menuName\": \"Css动画\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/other\",\n" +
                "                \"menuUrl\": \"/other/descriptions\",\n" +
                "                \"menuName\": \"详情页面\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"menuUrl\": \"/result\",\n" +
                "        \"menuName\": \"结果页面\",\n" +
                "        \"icon\": \"TakeawayBoxIcon\",\n" +
                "        \"parentPath\": \"\",\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"parentPath\": \"/result\",\n" +
                "                \"menuUrl\": \"/result/success\",\n" +
                "                \"menuName\": \"成功页面\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/result\",\n" +
                "                \"menuUrl\": \"/result/fail\",\n" +
                "                \"menuName\": \"失败页面\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"menuUrl\": \"/exception\",\n" +
                "        \"menuName\": \"异常页面\",\n" +
                "        \"icon\": \"WarningIcon\",\n" +
                "        \"parentPath\": \"\",\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"parentPath\": \"/exception\",\n" +
                "                \"menuUrl\": \"/exception/404\",\n" +
                "                \"menuName\": \"404页面\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/exception\",\n" +
                "                \"menuUrl\": \"/exception/403\",\n" +
                "                \"menuName\": \"403页面\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/exception\",\n" +
                "                \"menuUrl\": \"/exception/500\",\n" +
                "                \"menuName\": \"500页面\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"menuUrl\": \"/editor\",\n" +
                "        \"menuName\": \"编辑器\",\n" +
                "        \"tip\": \"12\",\n" +
                "        \"icon\": \"EditIcon\",\n" +
                "        \"parentPath\": \"\",\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"parentPath\": \"/editor\",\n" +
                "                \"menuUrl\": \"/editor/rich-text\",\n" +
                "                \"menuName\": \"富文本\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/editor\",\n" +
                "                \"menuUrl\": \"/editor/markdown\",\n" +
                "                \"menuName\": \"markdown\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"menuUrl\": \"/excel\",\n" +
                "        \"menuName\": \"Excel\",\n" +
                "        \"icon\": \"NotebookIcon\",\n" +
                "        \"parentPath\": \"\",\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"parentPath\": \"/excel\",\n" +
                "                \"menuUrl\": \"/excel/export-excel\",\n" +
                "                \"menuName\": \"导出Excel\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/excel\",\n" +
                "                \"menuUrl\": \"/excel/export-rows-excel\",\n" +
                "                \"menuName\": \"导出选中行\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"menuUrl\": \"/draggable\",\n" +
                "        \"menuName\": \"拖拽\",\n" +
                "        \"icon\": \"PointerIcon\",\n" +
                "        \"parentPath\": \"\",\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"parentPath\": \"/draggable\",\n" +
                "                \"menuUrl\": \"/draggable/dialog-draggable\",\n" +
                "                \"menuName\": \"拖拽对话框\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/draggable\",\n" +
                "                \"menuUrl\": \"/draggable/card-draggable\",\n" +
                "                \"menuName\": \"卡片拖拽\",\n" +
                "                \"cacheable\": true\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"menuUrl\": \"/next\",\n" +
                "        \"menuName\": \"多级菜单\",\n" +
                "        \"icon\": \"ShareIcon\",\n" +
                "        \"parentPath\": \"\",\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"parentPath\": \"/next\",\n" +
                "                \"menuUrl\": \"/next/menu1\",\n" +
                "                \"menuName\": \"menu-1\",\n" +
                "                \"cacheable\": true\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/next\",\n" +
                "                \"menuUrl\": \"/next/menu2\",\n" +
                "                \"menuName\": \"menu-2\",\n" +
                "                \"children\": [\n" +
                "                    {\n" +
                "                        \"parentPath\": \"/next/menu2\",\n" +
                "                        \"menuUrl\": \"/next/menu2/menu-2-1\",\n" +
                "                        \"menuName\": \"menu-2-1\",\n" +
                "                        \"children\": [\n" +
                "                            {\n" +
                "                                \"parentPath\": \"/next/menu2/menu-2-1\",\n" +
                "                                \"menuUrl\": \"/next/menu2/menu-2-1/menu-2-1-1\",\n" +
                "                                \"menuName\": \"menu-2-1-1\",\n" +
                "                                \"cacheable\": true\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"parentPath\": \"/next/menu2/menu-2-1\",\n" +
                "                                \"menuUrl\": \"/next/menu2/menu-2-1/menu-2-1-2\",\n" +
                "                                \"menuName\": \"menu-2-1-2\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"parentPath\": \"/next/menu2\",\n" +
                "                        \"menuUrl\": \"/next/menu2/menu-2-2\",\n" +
                "                        \"menuName\": \"menu-2-2\",\n" +
                "                        \"cacheable\": true\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"menuUrl\": \"/map\",\n" +
                "        \"menuName\": \"地图\",\n" +
                "        \"icon\": \"MapLocationIcon\",\n" +
                "        \"children\": [\n" +
                "            {\n" +
                "                \"parentPath\": \"/map\",\n" +
                "                \"menuUrl\": \"/map/gaode\",\n" +
                "                \"menuName\": \"高德地图\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"parentPath\": \"/map\",\n" +
                "                \"menuUrl\": \"/map/baidu\",\n" +
                "                \"menuName\": \"百度地图\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]";
        List<SysMenuVo> SysMenuVos = JSON.parseArray(result, SysMenuVo.class);
        return R.ok(SysMenuVos);
    }

    /**
     * 根据用户角色获取菜单列表
     * @return
     */
    @GetMapping("/getMenuListByRoleId")
    public R<List<SysMenuVo>> getMenuListByRoleId (){
        return menuList();
    }

    /**
     * 获取表格列表
     * @return
     */
    @GetMapping("/getTableList")
    public R<List<TableInfo>> getTableList (){
        List<TableInfo> result = RandomUtils.randomPojoList(TableInfo.class,(t)-> {
            t.setGender(RandomUtil.randomInt(0,2));
            t.setVip(RandomUtil.randomInt(0,2));
            t.setStatus(RandomUtil.randomInt(0,2));
        });
        return R.ok(result);
    }

    /**
     * 获取表格列表
     * @return
     */
    @GetMapping("/getUserList")
    public R<List<TableInfo>> getUserList (){
        List<TableInfo> result = RandomUtils.randomPojoList(TableInfo.class,(t)-> {
            t.setGender(RandomUtil.randomInt(0,2));
            t.setVip(RandomUtil.randomInt(0,2));
            t.setStatus(RandomUtil.randomInt(0,2));
        });
        return R.ok(result);
    }

    @Data
    class TableInfo {
        private Long id;
        private String nickName;
        private String avatar;
        private Integer gender;
        private Integer vip;
        private String address;
        private LocalDateTime lastLoginTime;
        private String lastLoginIp;
        private Integer status;
    }
}