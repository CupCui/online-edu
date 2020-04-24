import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/edu/login',
    method: 'post',
    data: {
      username,
      password
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/edu/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/edu/logout',
    method: 'post'
  })
}
