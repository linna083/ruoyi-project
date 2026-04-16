import request from '@/utils/request'

// 查询部门信息列表
export function listDept(query) {
  return request({
    url: '/dept/dept/list',
    method: 'get',
    params: query
  })
}

// 查询部门信息详细
export function getDept(deptId) {
  return request({
    url: '/dept/dept/' + deptId,
    method: 'get'
  })
}

// 新增部门信息
export function addDept(data) {
  return request({
    url: '/dept/dept',
    method: 'post',
    data: data
  })
}

// 修改部门信息
export function updateDept(data) {
  return request({
    url: '/dept/dept',
    method: 'put',
    data: data
  })
}

// 删除部门信息
export function delDept(deptId) {
  return request({
    url: '/dept/dept/' + deptId,
    method: 'delete'
  })
}
