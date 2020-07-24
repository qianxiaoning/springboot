const host_dev = '/api';
const host_prod = 'http://a.cloud.cn/api/';
// 根据 process.env.NODE_ENV 判断 开发或生产接口地址
const host = process.env.NODE_ENV == 'development' ? host_dev : host_prod; 

// 后台各个服务地址头
// const hostV1 = host + '/v1';
// const hostV2 = host + '/v2';
// const hostV3 = host + '/v3';
// const hostV4 = host + '/v4';  

const urls = {
    //查询products
    getProducts:host+'/products',
    //查询productDetailById
    getProductById:host+'/products',
    //jsonp测试
    jsonpScriptSrc:"http://manage.c2c-system.com"+host+'/web/testJsonp',
    //jsonpObject测试    
    jsonpObjectScriptSrc:"http://manage.c2c-system.com"+host+'/web/testJSONPObject',    
    //jsonp验证用户信息
    validateUser:"http://sso.c2c-system.com/user/check",    
    //用户注册
    doRegister:host+'/user/doRegister',
    //用户登录
    doLogin:host+'/user/doLogin',
    //jsonp查询用户信息byCookie
    queryByCookie:"http://sso.c2c-system.com/user/queryByCookie",  
    //用户登出
    logout:host+'/user/logout',  
    //根据用户id查询购物车
    queryCartByUserId:host+'/cart/queryCartByUserId',
    //根据用户id查询购物车
    queryCartByUserId2:host+'/order/create',
    //购物车商品数量更新
    updateCartNum:host+'/cart/update/num',
    //购物车商品删除
    deleteCart:host+'/cart/delete',
    //新增购物车商品
    addCart:host+'/cart/add',
    //提交订单
    submitOrder:host+'/order/submit',
    //查询订单
    queryOrder:host+'/order/success',
}

export default urls;