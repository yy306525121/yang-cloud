import request from '@/utils/request'

/**
 * 文件上传
 * @param file 文件
 */
export function uploadFile(formData) {
  console.log(formData)
  return request({
    url: '/cms/api/upload',
    method: 'post',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    data: formData
  })
}
