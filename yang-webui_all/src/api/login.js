import request from '@/utils/request'

export function loginByUsername(username, password) {
  const grant_type = 'password'
  const scope = 'openid'
  const data = {
    username,
    password,
    grant_type
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

export function logout() {
  return request({
    url: '/auth/api/token/revoke',
    method: 'delete',
  })
}

export function getUserInfo() {
  return request({
    url: '/auth/api/user/info',
    method: 'get'
  })
}

