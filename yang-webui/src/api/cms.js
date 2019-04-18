import request from '@/utils/request'


/**
 * 资源列表
 * @param query
 */
export function list(query) {
  return request({
    url: '/cms/api/resource/list',
    method: 'post',
    params: query
  })
}

/**
 * 创建资源
 * @param name
 * @param path
 * @param type
 * @param size
 */
export function createResource(resource) {

  return request({
    url: "/cms/api/resource/create",
    method: "post",
    params: resource
  })
}
