import store from '@/store'

function checkRole(el, binding) {
  const { value } = binding
  const roles = store.state.user.roles
  if (value && value instanceof Array && value.length > 0) {
    const roleFlag = value
    const hasRole = roles.some(role => {
      return roleFlag.includes(role)
    })

    if (!hasRole) {
      el.parentNode && el.parentNode.removeChild(el)
    }
  } else {
    throw new Error(`请设置角色权限标签值"`)
  }
}

export default {
  mounted(el, binding, vnode) {
    checkRole(el, binding)
  },
  updated(el, binding, vnode) {
    checkRole(el, binding)
  }
}
