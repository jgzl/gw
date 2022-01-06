import LayoutStore from '@/layouts'
import Cookies from 'js-cookie'
import { Module } from 'vuex'
import { UserState, RootState } from '../types'

import Avatar from '@/assets/img_avatar.gif'

const defaultAvatar = Avatar

const userInfo: UserState = JSON.parse(
  localStorage.getItem('x-user-info') || '{}'
)
LayoutStore.setUserInfo({
  nickName: userInfo.nickName || 'admin',
  avatar: userInfo.avatar || defaultAvatar,
})

export const userModule: Module<UserState, RootState> = {
  namespaced: true,
  state: {
    userId: userInfo.userId || 0,
    roles: userInfo.roles || null,
    permissions: userInfo.permissions || null,
    token: userInfo.token || '',
    userName: userInfo.userName || '',
    nickName: userInfo.nickName || '',
    avatar: userInfo.avatar || defaultAvatar,
  },
  getters: {
    userId(state) {
      return state.userId
    },
    roles(state) {
      return state.roles
    },
    permissions(state) {
      return state.permissions
    },
  },
  actions: {
    saveUser({ commit }, userInfo: UserState) {
      return new Promise<void>((res) => {
        commit('SAVE_USER', userInfo)
        res()
      })
    },
    changeNickName({ commit }, newNickName) {
      commit('CHANGE_NICKNAME', newNickName)
    },
    logout({ commit }) {
      commit('LOGOUT')
    },
  },
  mutations: {
    CHANGE_NICKNAME(state, newNickName) {
      state.nickName = newNickName
    },
    SAVE_USER(state, userInfo: UserState) {
      state.userId = userInfo.userId
      state.token = userInfo.token
      state.roles = userInfo.roles
      state.permissions = userInfo.permissions
      state.userName = userInfo.userName
      state.nickName = userInfo.nickName
      state.avatar = userInfo.avatar || defaultAvatar
      Cookies.set('x-admin-token', userInfo.token)
      localStorage.setItem('x-user-info', JSON.stringify(userInfo))
      LayoutStore.setUserInfo({
        nickName: userInfo.nickName,
        avatar: userInfo.avatar || defaultAvatar,
      })
    },
    LOGOUT(state) {
      state.userId = 0
      state.avatar = ''
      state.roles = []
      state.permissions = []
      state.userName = ''
      state.nickName = ''
      state.token = ''
      Cookies.remove('x-admin-token')
      localStorage.clear()
      LayoutStore.reset()
    },
  },
}
