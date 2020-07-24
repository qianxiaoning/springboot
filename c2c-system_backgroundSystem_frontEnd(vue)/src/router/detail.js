//detail模块
import contentCategory from '@/pages/main/detail/contentCategory/contentCategory'
import contentManage from '@/pages/main/detail/contentManage/contentManage'

export default [
    {
        path: 'detail',
        redirect: 'detail/contentCategory'
    },
    {
        path: 'detail/contentCategory',
        name: 'contentCategory',
        component: contentCategory,
    },
    {
        path: 'detail/contentManage',
        name: 'contentManage',
        component: contentManage,
    }
]

