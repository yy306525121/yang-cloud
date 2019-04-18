import request from '@/utils/request'

/**
 * 系统列表
 * @param title 系统标题
 * @param current 当前页
 * @param size 每页条数
 */
export function getSystemList(title, current, size) {
  const data = {
    title,
    current,
    size
  }

  return request({
    url: '/auth/api/system',
    method: 'post',
    params: data
  })
}


export function registerAccount(username, email, password, avatar) {

}
