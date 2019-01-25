import request from '@/utils/request'

export function loginByUsername(username, password) {
  const grant_type = 'password'
  const scope = 'openid'
  return request({
    url: '/auth/oauth/token',
    method: 'post',
    headers: {
      'Authorization': 'Basic d2ViX2FwcDpoZWxsbw=='
    },
    params: { username, password, grant_type, scope }
  })
}

export function logout() {
  return request({
    url: '/login/logout',
    method: 'post'
  })
}

export function getUserInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

