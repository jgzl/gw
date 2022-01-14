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
            >添加
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
        >
          <el-table-column align="center" label="序号" fixed="left" width="80">
            <template #default="scope">
              {{ scope.$index + 1 }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="角色名称" prop="roleName" />
          <el-table-column align="center" label="角色编号" prop="roleCode" />
          <el-table-column align="center" label="角色描述" prop="roleDesc" />
          <el-table-column align="center" label="创建时间" prop="createTime" />
          <el-table-column align="center" label="操作" width="300px">
            <template #default="scope">
              <el-button
                      v-permission = "['sys_role_edit']"
                      plain
                      type="primary"
                      size="mini"
                      @click="onUpdateItem(scope.row)"
              >编辑</el-button
              >
              <el-button
                      v-role = "['ROLE_admin','ROLE_editor']"
                      plain
                      type="danger"
                      size="mini"
                      @click="onDeleteItem(scope.row)"
              >删除</el-button
              >
              <el-button
                      plain
                      type="warning"
                      size="mini"
                      @click="showMenu(scope.row)"
              >菜单权限</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </template>
    </TableBody>
    <Dialog ref="dialogRef">
      <template #content>
        <BaseForm ref="baseFormRef" :form-items="formItems" />
      </template>
    </Dialog>
    <Dialog ref="menuDialogRef" title="菜单权限">
      <template #content>
        <el-tree
                ref="tree"
                :data="menuData"
                show-checkbox
                node-key="id"
                :default-expanded-keys="defaultExpandedKeys"
                :default-checked-keys="defaultCheckedKeys"
                :props="defaultProps"
        />
      </template>
    </Dialog>
  </div>
</template>

<script lang="ts" setup>
  import type { DialogType } from "@/components/types";
  import { onMounted, reactive, ref, shallowReactive } from "vue";
  import { ElMessage, ElMessageBox } from "element-plus";
  import {useGet, useDataTable, usePost, usePut, useDelete} from "@/hooks";
  import {
    systemRolePage,
    systemMenuTree,
    systemMenuTreeByRole,
    systemMenuList,
    systemUser,
    systemRole, systemRoleMenu
  } from "@/api/url";
  import useUserStore from "@/store/modules/user";
  const userStore = useUserStore();
  const ROLE_CODE_FLAG = "ROLE_";
  const MENU_LEFT = "0";
  const MENU_BUTTON = "1";
  const roleModel = reactive({
    roleId: 0,
    roleName: "",
    roleCode: "",
    description: "",
    createTime: "",
  });
  const menuData = shallowReactive<Array<any>>([]);
  const defaultProps = {
    children: "children",
    label: "menuName",
  };
  const defaultCheckedKeys = reactive<Array<any>>([]);
  const defaultExpandedKeys = reactive<Array<any>>([]);
  const formItems = reactive([
    {
      label: "角色名称",
      type: "input",
      name: "roleName",
      value: "",
      maxLength: 50,
      inputType: "text",
      placeholder: "请输入角色名称",
      validator: (item: any) => {
        if (!item.value) {
          ElMessage.error(item.placeholder);
          return false;
        }
        return true;
      },
      reset() {
        this.value = "";
      },
    },
    {
      label: "角色编号",
      type: "input",
      name: "roleCode",
      value: "",
      maxLength: 20,
      inputType: "text",
      placeholder: "请输入角色编号:ROLE_",
      validator: (item: any) => {
        if (!item.value) {
          ElMessage.error(item.placeholder);
          return false;
        }
        return true;
      },
      reset() {
        this.value = "";
      },
    },
    {
      label: "角色描述",
      type: "input",
      name: "roleDesc",
      value: "",
      maxLength: 50,
      inputType: "text",
      placeholder: "请输入角色描述",
      validator: (item: any) => {
        if (!item.value) {
          ElMessage.error(item.placeholder);
          return false;
        }
        return true;
      },
      reset() {
        this.value = "";
      },
    },
  ]);
  const menuDialogRef = ref<DialogType>();
  const dialogRef = ref<DialogType>();
  const baseFormRef = ref();
  const tree = ref()
  const get = useGet();
  const post = usePost();
  const put = usePut();
  const httpDelete = useDelete();
  const { handleSuccess, dataList, tableLoading, tableConfig } = useDataTable();
  function doRefresh() {
    get({
      url: systemRolePage,
      data: {},
    })
            .then(handleSuccess)
            .catch(console.log);
  }
  function showMenu(item: RoleModel) {
    menuData.length = 0;
    defaultCheckedKeys.length = 0;
    defaultExpandedKeys.length = 0;
    get({
      url: systemMenuTree,
      data: {
        lazy: false
      }
    })
    .then((res) => {
      menuData.push(...res.data);
    }).then(()=>{
      get({
        url: systemMenuList+"/"+item.roleId
      }).then((roleRes) => {
        const roleMenus = [];
        if (roleRes.data!=null) {
          roleMenus.push(...roleRes.data);
        }
        handleRoleMenusSelected(menuData,roleMenus);
        console.log(`defaultCheckedKeys:${JSON.stringify(defaultCheckedKeys)}`)
        console.log(`defaultExpandedKeys:${JSON.stringify(defaultExpandedKeys)}`)
        menuDialogRef.value?.show(() => {
          const menuIds = tree.value.getCheckedKeys().join(",")
          put({
            url:systemRoleMenu ,
            data: {
              roleId: item.roleId,
              menuIds: menuIds
            }
          })
          .then((res)=>{
            console.log(JSON.stringify(res))
            doRefresh();
          })
          .catch(console.log)
          menuDialogRef.value?.close();
        });
      }).catch(console.log);
    })
    .catch(console.log);
  }
  function onAddItem() {
    formItems.forEach((it: FormItem) => it.reset && it.reset());
    dialogRef.value?.show(() => {
      post({url:systemRole ,data: baseFormRef.value?.generatorParams()})
              .then((res)=>{
                console.log(JSON.stringify(res))
                doRefresh();
              })
              .catch(console.log)
      dialogRef.value?.close();
    });
  }
  function onUpdateItem(item: RoleModel) {
    formItems.forEach((it: FormItem) => {
      const typeName = it.name;
      if (typeName) {
        const typeValue = item[typeName];
        if (typeValue) {
          it.value = item[typeName];
        }
      }
    });
    const currentRoles = userStore.roles
    console.log(currentRoles)
    dialogRef.value?.show(() => {
      const updateData: any = {};
      updateData['roleId'] = item.roleId;
      formItems.forEach((it: FormItem) => {
        updateData[it.name] = it.value
      });
      put({url:systemRole ,data: updateData})
              .then((res)=>{
                console.log(JSON.stringify(res))
                doRefresh();
              })
              .catch(console.log)
      dialogRef.value?.close();
    });
  }
  function onDeleteItem(item: RoleModel) {
    ElMessageBox.confirm("是否要删除此信息，删除后不可恢复？", "提示").then(
            () => {
              httpDelete({url:`${systemRole}/${item.roleId}`})
                      .then((res)=>{
                        console.log(JSON.stringify(res))
                        doRefresh();
                      })
                      .catch(console.log)
            }
    );
  }
  function handleRoleMenusSelected(menus: Array<any>,roleMenus: Array<any>) {
    menus.forEach((it: any) => {
        if (roleMenus.includes(it.id)) {
          console.log(`it.id:${it.id}`);
          defaultCheckedKeys.push(it.id);
        }
        if (it.children) {
          defaultExpandedKeys.push(it.id);
          handleRoleMenusSelected(it.children,roleMenus);
        }
      })
  }
  onMounted(doRefresh);
</script>
