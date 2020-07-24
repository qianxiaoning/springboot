// token所需
import http from '@/http/http'
import urls from '@/http/urls'
// 路由
import Vue from 'vue'
import Router from 'vue-router'
// login
import login from '@/pages/login/login'
// register
import register from '@/pages/register/register'
// notFound
import notFound from '@/pages/notFound/notFound'
// main
import main from '@/pages/main/main'
// productDetail
import productDetail from '@/pages/productDetail/productDetail'
// cart
import cart from '@/pages/cart/cart'
// order
import order from '@/pages/order/order'
// orderSuccess
import orderSuccess from '@/pages/orderSuccess/orderSuccess'
// testJsonp
import testJsonp from '@/pages/testJsonp/testJsonp'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',      
      redirect: '/main'
    },
    {
      path: '/login',   
      name:'login',   
      component: login
    },    
    {
      path: '/register',   
      name:'register',   
      component: register
    },    
    {
      path: '/main',      
      name:'main',
      component: main,
    },
    {
      path: '/productDetail',      
      name:'productDetail',
      component: productDetail,
    },
    {
      path: '/cart',      
      name:'cart',
      component: cart,
    },
    {
      path: '/order',      
      name:'order',
      component: order,
    },
    {
      path: '/orderSuccess',      
      name:'orderSuccess',
      component: orderSuccess,
    },
    {
      path: '/testJsonp',      
      name:'testJsonp',
      component: testJsonp,
    },
    {
      path: '*',      
      component: notFound
    },
  ]
})

// 获取token
// let name;
// function createToken(){
//   if(location.hash.lastIndexOf('?')>0){
//     const query = location.hash.split('?')[1];
//     const queryArray = query.split('&');
//     queryArray.forEach((item) => {
//         if(item.indexOf('name')>-1){
//           name = item.split('=')[1];
//         }
//     });

//     const timestamp = Date.now();
//     const md5_sign = jsmd5(`loginName=${name}&timestamp=${timestamp}&secret=123`);
//     let loginUrl = `${urls.login}?loginName=${name}&timestamp=${timestamp}&sign=${md5_sign}`;
//     return http.post(loginUrl);               
//   }
// }
// // 路由守卫
// router.beforeEach((to, from, next) => {
//   const token = instanceAxios.defaults.headers.token;
//   // token存在
//   if(token){
//     next();
//   } else {
//     // 不存在请求
//     if(location.hash.lastIndexOf('?')>0){
//       // 刷新 token 
//       createToken().then((res) => {
//         instanceAxios.defaults.headers.token = res.value;        
//         next();                
//         localStorage.token = res.value;
//       })
//     }else{
//       instanceAxios.defaults.headers.token = localStorage.token;
//       next();
//     }    
//   }
// })

export default router;
