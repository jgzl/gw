<template>
    <div class="main-container">
        <TableBody>
            <template #tableConfig>
                <TableConfig
                        v-model:border="tableConfig.border"
                        v-model:stripe="tableConfig.stripe"
                        @refresh="doRefresh"
                >
                    <template #actions>
                        <el-button
                                type="primary"
                                size="mini"
                                icon="PlusIcon"
                                @click="onAddItem"
                        >
                            添加
                        </el-button>
                        <el-button
                                type="danger"
                                size="mini"
                                icon="DeleteIcon"
                                :disabled="selectRows.length === 0"
                                @click="onDeleteItems"
                        >
                            删除
                        </el-button>
                    </template>
                </TableConfig>
            </template>
            <template #default>
                <el-table
                        v-loading="tableLoading"
                        :data="dataList"
                        :header-cell-style="tableConfig.headerCellStyle"
                        :size="tableConfig.size"
                        :stripe="tableConfig.stripe"
                        :border="tableConfig.border"
                        @selection-change="handleSelectionChange"
                >
                    <el-table-column type="selection" width="45"/>
                    <el-table-column
                            align="center"
                            label="id"
                            prop="id"
                    />
                    <el-table-column
                            align="center"
                            label="网关访问key"
                            prop="apiKey"
                    />
                    <el-table-column
                            align="center"
                            label="网关访问secret"
                            prop="apiSecret"
                    />
                    <el-table-column
                            align="center"
                            label="调用系统"
                            prop="system"
                    />
                    <el-table-column align="center" label="状态">
                        <template #default="scope">
                            <el-tag
                                    size="mini"
                                    :type="scope.row.status === '0' ? 'success' : 'danger'"
                            >
                                {{ scope.row.status === "0" ? "正常" : "禁用" }}
                            </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="备注"
                            prop="remark"
                    />
                    <el-table-column
                            align="center"
                            label="操作"
                            fixed="right"
                            width="220"
                    >
                        <template #default="scope">
                            <el-button
                                    type="primary"
                                    size="mini"
                                    plain
                                    @click="onUpdateItem(scope.row)"
                            >编辑
                            </el-button
                            >
                            <el-button
                                    type="danger"
                                    size="mini"
                                    plain
                                    @click="onDeleteItem(scope.row)"
                            >删除
                            </el-button
                            >
                            <el-button
                                    :type="scope.row.status === '0' ? 'warning' : 'success'"
                                    size="mini"
                                    plain
                                    @click="onEnableItem(scope.row)"
                            >{{ scope.row.status === "0" ? "禁用" : "启用" }}</el-button
                            >
                        </template>
                    </el-table-column>
                </el-table>
            </template>
        </TableBody>
        <Dialog ref="dialogRef">
            <template #content>
                <el-form
                        class="base-form-container"
                        :model="accessModel"
                        :inline="true"
                        label-width="240px"
                        label-position="right"
                >
                    <el-divider border-style="dashed" content-position="left">基本信息</el-divider>
                    <el-form-item label="网关访问key">
                        <el-input
                                v-model="accessModel.apiKey"
                                size="small"
                                placeholder="请输入网关访问key"
                                clearable
                        />
                    </el-form-item>
                    <el-form-item label="网关访问secret">
                        <el-input
                                v-model="accessModel.apiSecret"
                                size="small"
                                placeholder="请输入网关访问secret"
                                clearable
                        />
                    </el-form-item>
                    <el-form-item label="访问系统">
                        <el-input
                                v-model="accessModel.system"
                                size="small"
                                placeholder="请输入访问系统"
                                clearable
                        />
                    </el-form-item>
                    <el-form-item label="备注">
                        <el-input
                                v-model="accessModel.remark"
                                size="small"
                                placeholder="请输入备注"
                                clearable
                        />
                    </el-form-item>
                    <el-form-item label="状态">
                        <el-switch
                                v-model="accessModel.status"
                                active-value="0"
                                inactive-value="1"
                        />
                    </el-form-item>
                </el-form>
            </template>
        </Dialog>
    </div>
</template>

<script lang="ts" setup>
    import { useDataTable, useDelete, useGet, usePost, usePut } from "@/hooks";
    import {onMounted, reactive, ref} from "vue";
    import {ElMessage, ElMessageBox} from "element-plus";
    import {gatewayAccess, gatewayAccessStatus} from "@/api/url";
    import {DialogType, TableFooter} from "@/components/types";

    const get = useGet();
    const post = usePost();
    const put = usePut();
    const httpDelete = useDelete();
    const dialogRef = ref<DialogType>();
    const tableFooter = ref<TableFooter>();
    const {
        dataList,
        tableLoading,
        tableConfig,
        handleSuccess,
        handleSelectionChange,
        selectRows,
        useHeight,
    } = useDataTable();

    const accessModel: AccessModel = reactive({
        id: "",
        apiKey: "",
        apiSecret: "",
        system: "",
        remark: "",
        status: "",
    });

    const routeIdDisabled = ref(true);
    function doRefresh() {
        get({
            url: gatewayAccess,
            data: tableFooter.value?.withPageInfoData(),
        })
            .then((res) => {
                return handleSuccess(res);
            })
            .then((res: any) => {
                tableFooter.value?.setTotalSize(res.totalSize);
            })
            .catch((error) => {
                console.log(error);
            });
    }

    function onDeleteItems() {
        if (selectRows.value.length > 0) {
            ElMessageBox.confirm("确定要删除这些数据吗？", "提示")
                .then(() => {
                  const idArray: string[] = [];
                  selectRows.value.map((row: AccessModel)=>idArray.push(row.id));
                  httpDelete({ url:gatewayAccess+"/"+idArray.join(",") })
                      .then((res)=>{
                        console.log(JSON.stringify(res))
                        doRefresh();
                      })
                      .catch(console.log)
                })
                .catch(console.log);
            console.log("请求结束")
        }
    }

    function onAddItem() {
        accessModel.apiKey = "";
        accessModel.id = "";
        accessModel.apiSecret = "";
        accessModel.system = "";
        accessModel.remark = "";
        accessModel.status = "0";
        dialogRef.value?.show(() => {
            dialogRef.value?.showLoading();
            post({url:gatewayAccess ,data: accessModel})
                .then((res)=>{
                    console.log(JSON.stringify(res))
                    doRefresh();
                })
                .catch(console.log)
            dialogRef.value?.close();
        });
    }

    function onUpdateItem(item: AccessModel) {
        accessModel.id = item.id;
        accessModel.apiKey = item.apiKey;
        accessModel.apiSecret = item.apiSecret;
        accessModel.system = item.system;
        accessModel.remark = item.remark;
        accessModel.status = item.status;
        dialogRef.value?.show(() => {
            dialogRef.value?.showLoading();
            put({ url:gatewayAccess ,data: accessModel })
                .then((res)=>{
                    console.log(JSON.stringify(res))
                    doRefresh();
                })
                .catch(console.log)
            dialogRef.value?.close();
        });
    }

    function onDeleteItem(item: AccessModel) {
        ElMessageBox.confirm("确定要删除此数据吗？", "提示")
            .then(() => {
                httpDelete({ url:gatewayAccess+"/"+item.id })
                    .then((res)=>{
                      console.log(JSON.stringify(res))
                      doRefresh();
                    })
                    .catch(console.log)
            })
            .catch(console.log);
    }
    function onEnableItem(item: any) {
        ElMessageBox.confirm(
            "确定要" + (item.status === "0" ? "禁用" : "启用") + "此数据吗？",
            "提示"
        )
            .then(() => {
                switch (item.status) {
                    case "0":
                        item.status = "1"
                        break;
                    case "1":
                        item.status = "0"
                        break;
                    default:
                        item.status = "0"
                }
                console.log(item.status)
                put({url:gatewayAccessStatus , data:item})
                    .then((res)=>{
                        console.log(JSON.stringify(res))
                        doRefresh();
                    })
                    .catch(console.log)
            })
            .catch(console.log);
    }
    onMounted(() => {
        doRefresh();
        useHeight();
    });
</script>

<style lang="scss" scoped>
    .gender-container {
        .gender-icon {
            width: 20px;
        }
    }
</style>