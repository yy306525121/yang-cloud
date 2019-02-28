import {getUserInfo, loginByUsername, logout, refreshToken} from '@/api/user'
import {
  getAccessToken,
  getRefreshToken,
  removeAccessToken,
  removeRefreshToken,
  setAccessToken,
  setRefreshToken
} from '@/utils/auth'

const user = {
  state: {
    access_token: getAccessToken(),
    refresh_token: getRefreshToken(),
    name: '',
    avatar: '',
    introduction: '',
    roles: [],
  },

  mutations: {
    SET_ACCESS_TOKEN: (state, access_token) => {
      state.access_token = access_token
    },
    SET_REFRESH_TOKEN: (state, refresh_token) => {
      state.refresh_token = refresh_token
    },
    SET_INTRODUCTION: (state, introduction) => {
      state.introduction = introduction
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_EMAIL: (state, email) => {
      state.email = email
    }
  },

  actions: {
    /**
     * 登陆
     * @param commit
     * @param userInfo
     * @returns {Promise<any>}
     */
    LoginByUsername({commit}, userInfo) {
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {

        loginByUsername(username, userInfo.password).then(response => {
          commit('SET_ACCESS_TOKEN', response.access_token)
          commit('SET_REFRESH_TOKEN', response.refresh_token)
          setAccessToken(response.access_token)
          setRefreshToken(response.refresh_token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    /**
     * 刷新令牌
     * @param token
     */
    flushToken({commit}) {
      return new Promise((resolve, reject) => {
        refreshToken(getRefreshToken()).then(response => {
          commit('SET_ACCESS_TOKEN', response.access_token)
          commit('SET_REFRESH_TOKEN', response.refresh_token)
          setAccessToken(response.access_token)
          setRefreshToken(response.refresh_token)
          resolve()
        }).catch(err => {
          reject(err)
        })
      })
    },

    /**
     * 获取用户信息
     * @param commit
     * @returns {Promise<any>}
     * @constructor
     */
    GetUserInfo({commit}) {
      return new Promise((resolve, reject) => {
        getUserInfo().then(response => {
          if (!response) {
            reject("登陆失败 请重新登陆")
          }

          const data = response

          let roles = []
          if (data.roles && data.roles.length > 0) {
            data.roles.forEach(val => {
              roles.push(val.name)
            })
          } else {
            reject("该用户无权登陆")
          }

          commit('SET_ROLES', roles)
          commit('SET_NAME', data.username)
          commit('SET_EMAIL', data.email)
          commit('SET_AVATAR', data.avatar)
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    LogOut({commit, state}) {
      return new Promise((resolve, reject) => {
        logout().then(() => {
          commit('SET_ACCESS_TOKEN', '')
          commit('SET_REFRESH_TOKEN', '')
          commit('SET_ROLES', [])
          removeAccessToken()
          removeRefreshToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 前端 登出
    FedLogOut({commit}) {
      return new Promise(resolve => {
        commit('SET_ACCESS_TOKEN', '')
        commit('SET_REFRESH_TOKEN', '')
        removeAccessToken()
        removeRefreshToken()
        resolve()
      })
    },

    DeleteAccessToken({commit}) {
      return new Promise(resolve => {
        commit('SET_ACCESS_TOKEN', '')
        removeAccessToken()
        resolve()
      })
    }

  }
}

export default user
