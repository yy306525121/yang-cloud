import request from '@/utils/request'

/**
 * 用户登陆
 * @param username 用户名
 * @param password 密码
 */
export function loginByUsername(username, password) {
  const grant_type = 'password'
  const scope = 'openid'
  const data = {
    username,
    password,
    grant_type,
    scope
  }

  return request({
    url: '/auth/oauth/token',
    method: 'post',
    headers: {
      'Authorization': 'Basic d2ViX2FwcDpoZWxsbw=='
    },
    params: data
  })
}

export function refreshToken(refresh_token) {
  const grant_type = 'refresh_token'
  const data = {
    grant_type,
    refresh_token
  }

  return request({
    url: '/auth/oauth/token',
    method: 'post',
    headers: {
      'Authorization': 'Basic d2ViX2FwcDpoZWxsbw=='
    },
    params: data
  })
}


/**
 * 获取用户信息
 */
export function getUserInfo() {
  return request({
    url: '/auth/api/user/info',
    method: 'get'
  })
}

/**
 * 注销token
 */
export function logout(){
  return request({
    url: '/auth/api/token/revoke',
    method: 'delete'
  })
}
