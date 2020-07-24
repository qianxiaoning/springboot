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
    //查询product
    getProduct:host+'/product/query',
    //根据id查询productDesc
    getProductDescById:host+'/product/query/product/desc/',
    //新增product
    postProduct:host+'/product/save',
    //更新product
    putProduct:host+'/product/update',
    //删除product
    deleteProduct:host+'/product/delete',
    //上架product
    reshelfProduct:host+'/product/reshelf',
    //下架product
    instockProduct:host+'/product/instock',
    //查询productCategoryList
    getProductCategoryList:host+'/product/category/list',
    //上传图片
    uploadImg:host+'/image/upload',
}

export default urls;