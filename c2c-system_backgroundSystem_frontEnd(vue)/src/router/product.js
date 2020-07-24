//product模块
import addProduct from '@/pages/main/product/addProduct/addProduct'
import findProduct from '@/pages/main/product/findProduct/findProduct'
import parameter from '@/pages/main/product/parameter/parameter'

export default [
    {
        path: 'product',
        redirect: 'product/addProduct',
    },
    {
        path: 'product/addProduct',
        name: 'addProduct',
        component: addProduct,
    },
    {
        path: 'product/findProduct',
        name: 'findProduct',
        component: findProduct,
    },
    {
        path: 'product/parameter',
        name: 'parameter',
        component: parameter,
    }
]



