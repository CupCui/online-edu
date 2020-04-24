import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },
  
  {
    path: '/teacher',
    component: Layout,
    redirect: '/teacher/list',
    name: '讲师管理',
    meta: { title: '讲师管理', icon: 'example' },
    children: [
      {
        path: 'list',
        name: '讲师列表',
        component: () => import('@/views/teacher/list'),
        meta: { title: '讲师列表', icon: 'table' }
      },
      {
        path: 'save',
        name: '添加讲师',
        component: () => import('@/views/teacher/form'),
        meta: { title: '添加讲师', icon: 'tree' }
      },
      {
        path: 'edit/:teacherId',
        name: '修改讲师',
        component: () => import('@/views/teacher/form'),
        meta: { title: '修改讲师', icon: 'tree' },
        hidden: true
      }
    ]
  },
  
  {
    path: '/subject',
    component: Layout,
    redirect: '/subject/list',
    name: '课程分类管理',
    meta: { title: '课程分类管理', icon: 'example' },
    children: [
      {
        path: 'list',
        name: '课程分类列表',
        component: () => import('@/views/subject/list'),
        meta: { title: '课程分类列表', icon: 'table' }
      },
      {
        path: 'upload',
        name: '导入课程分类',
        component: () => import('@/views/subject/upload'),
        meta: { title: '导入课程分类', icon: 'tree' }
      }
    ]
  },

  // 课程管理
  {
    path: '/course',
    component: Layout,
    redirect: '/course/list',
    name: '课程管理',
    meta: { title: '课程管理', icon: 'example' },
    children: [
      {
        path: 'list',
        name: '课程列表',
        component: () => import('@/views/course/list'),
        meta: { title: '课程列表', icon: 'table' }
      },
      {
        path: 'form',
        name: '添加课程',
        component: () => import('@/views/course/form'),
        meta: { title: '添加课程', icon: 'tree' }
      },
      {
        path: 'form/:courseId',
        name: '添加课程',
        component: () => import('@/views/course/form'),
        meta: { title: '添加课程', icon: 'tree' },
        hidden: true
      },
      {
        path: 'chapter/:courseId',
        name: '章节小节管理',
        component: () => import('@/views/course/chapter'),
        meta: { title: '章节小节管理', icon: 'tree' },
        hidden: true
      },
      // 课程发布确认
      {
        path: 'publish/:courseId',
        name: '课程发布确认',
        component: () => import('@/views/course/publish'),
        meta: { title: '章节小节管理', icon: 'tree' },
        hidden: true
      }
    ]
  },
  // 统计管理
  {
    path: '/statistics',
    component: Layout,
    redirect: '/statistics/list',
    name: '统计分析',
    meta: { title: '统计分析', icon: 'example' },
    children: [
      {
        path: '/generate',
        name: '生成数据',
        component: () => import('@/views/statistics/generate'),
        meta: { title: '生成数据', icon: 'table' }
      },
      {
        path: '/show',
        name: '图表显示',
        component: () => import('@/views/statistics/show'),
        meta: { title: '图表显示', icon: 'tree' }
      }
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
