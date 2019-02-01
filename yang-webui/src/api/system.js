import request from '@/utils/request'

/**
 * 查询系统列表
 * @param query
 */
export function fetchList(query) {
  return request({
    url: '/auth/api/system/list',
    method: 'post',
    params: query
  })
}

export function createSystem(data) {
  return request({
    url: '/auth/api/system/create',
    method: 'post',
    data
  })
}

export function updateSystem(data) {
  return request({
    url: '/auth/api/system/update',
    method: 'post',
    data
  })
}

export function deleteSystem(id) {
  const data = {
    id
  }
  return request({
    url: '/auth/api/system/delete',
    method: 'post',
    data
  })
}
