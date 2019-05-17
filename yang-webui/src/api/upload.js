import request from '@/utils/request'

/**
 * 文件上传
 * @param file 文件
 */
export function uploadFile(formData) {
  console.log(formData)
  return request({
    url: '/cms/api/upload/simpleUpload',
    method: 'post',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    data: formData
  })
}

export function blockUpload(formData) {
  console.log(formData)
  return request({
    url: '/cms/api/upload/blockUpload',
    method: 'post',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    data: formData
  })
}
