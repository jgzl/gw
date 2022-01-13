declare interface RoleModel {
  roleId: number;
  roleName: string;
  roleCode: string;
  description: string;
  [propName: string]: any
}

declare interface MenuModel {
  menuId: string;
  parentPath: string;
  parentId: string;
  path: string;
  name: string;
  outLink?: string;
  badge?: string | number;
  badgeNum?: number;
  keepAlive?: boolean;
  hidden?: boolean;
  icon?: string;
  affix?: boolean
  permission?: string
  type?: string
}

declare interface TablePropsType {
  title: string;
  prop: string;
  checked: boolean;
}

declare interface RouteModel {
  id: string;
  routeName: string;
  routeId: string;
  predicates: string;
  filters: string;
  uri: string;
  order: string;
  metadata: string;
  [propName: string]: any
}