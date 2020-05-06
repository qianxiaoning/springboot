//sysManage模块
import deptManage from '@/pages/main/sysManage/deptManage/deptManage'
import menuManage from '@/pages/main/sysManage/menuManage/menuManage'
import roleManage from '@/pages/main/sysManage/roleManage/roleManage'
import userManage from '@/pages/main/sysManage/userManage/userManage'
import logsManage from '@/pages/main/sysManage/logsManage/logsManage'
import editPassword from '@/pages/main/sysManage/editPassword/editPassword'
export default [
    {
        path: 'sysManage',
        redirect: 'sysManage/deptManage'
    },
    {
        path: 'sysManage/deptManage',
        name: 'deptManage',
        component: deptManage,
    },
    {
        path: 'sysManage/menuManage',
        name: 'menuManage',
        component: menuManage,
    },
    {
        path: 'sysManage/roleManage',
        name: 'roleManage',
        component: roleManage,
    },
    {
        path: 'sysManage/userManage',
        name: 'userManage',
        component: userManage,
    },
    {
        path: 'sysManage/logsManage',
        name: 'logsManage',
        component: logsManage,
    },
    {
        path: 'sysManage/editPassword',
        name: 'editPassword',
        component: editPassword,
    }
]



