import request from '@/utils/request'

/**
 * 用户登陆
 * @param username 用户名
 * @param password 密码
 */
export function list(query) {
  return request({
    url: '/cms/api/resource/list',
    method: 'post',
    params: query
  })
}
