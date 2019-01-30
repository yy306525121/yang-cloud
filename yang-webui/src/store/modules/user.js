import {getUserInfo, loginByUsername, logout} from '@/api/user'
import {getToken, removeToken, setToken} from '@/utils/auth'

const user = {
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    introduction: '',
    roles: [],
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
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
          const data = response
          commit('SET_TOKEN', data.access_token)
          setToken(response.access_token)

          resolve()
        }).catch(error => {
          reject(error)
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
          if (data.roles && data.roles.length > 0){
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
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    },

  }
}

export default user
