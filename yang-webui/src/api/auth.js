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


/**
 * 机构列表
 * @param title
 * @param current
 * @param size
 */
export function getOrganizationList(title, current, size, pid) {

  console.log("pid: " + pid);
  const data = {
    title,
    current,
    size
  }

  return request({
    url: '/auth/api/organization/list',
    method: 'post',
    params: data
  })
}
