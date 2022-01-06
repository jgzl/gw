import store from '@/store'

function checkPermission(el, binding) {
  const { value } = binding
  const all_permission = "*:*:*";
  const permissions = store.state.user.permissions
  if (value && value instanceof Array && value.length > 0) {
    const permissionFlag = value
    const hasPermissions = permissions.some(permission => {
      return all_permission === permission || permissionFlag.includes(permission)
    })
    if (!hasPermissions) {
      el.parentNode && el.parentNode.removeChild(el)
    }
  } else {
    throw new Error(`请设置操作权限标签值`)
  }
}

export default {
  mounted(el, binding, vnode) {
    checkPermission(el, binding)
  },
  updated(el, binding, vnode) {
    checkPermission(el, binding)
  }
}
