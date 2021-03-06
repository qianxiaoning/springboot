import axios from 'axios';
//兼容localstorage的包
import storejs from 'storejs';
// 以服务的方式调用。引入element Loading 服务
import { Loading } from 'element-ui';
let loadingInstance;
// 引入消息提示
import { Message } from 'element-ui';
let messageInstance;

//配置axios实例
let instanceAxios = axios.create({
    headers:{
        'Content-Type':'application/json;charset=UTF-8',
        // token:storejs("token")
    },
    timeout:'20000',    
    // 跨域请求是否需要凭证 是否带上cookie
    withCredentials:true,
    // baseURL:url    
});
// 请求拦截器
instanceAxios.interceptors.request.use(
  (config)=>{
    // 发送请求之前    
    // loading
    loadingInstance = Loading.service({
        text:'加载中...',
        background:'rgba(0,0,0,.3)'
    });        
    return config
  },
  //请求错误
  (error)=>{    
    //loading 停止
    loadingInstance.close();
    // 弹窗 网络异常    
    messageInstance = Message({
        message:'网络异常',
        type:'warning',
    });
    return Promise.reject(error)
  },
)
// 响应拦截器
instanceAxios.interceptors.response.use(
  (response)=>{
    //响应数据  
    //loading停止
    loadingInstance.close();
    const res = response.data;
    //请求正常
    if(res.code==200){
        return res;
    }
    //登录失效
    else if(res.code==302){
        messageInstance = Message({
            message:'账号已掉线，请重新登录',
            type:'warning',
            duration:1000,
            onClose:()=>{
                //重定向回登录页
                // window.location.replace("/");
            }
        });
        return res;
    }    
    //前端请求错误
    else if(res.code==403){
        messageInstance = Message({
            message:res.message,
            type:'warning',
        });
        return Promise.reject(response);
    }    
  },
  (error)=>{    
    console.log(error);
    //loading停止
    loadingInstance.close();
    // 服务端问题
    messageInstance = Message({
        message:"系统维护中",
        // message:error,
        type:'warning',
    });
    return Promise.reject(error)
  },
)

//POST新增 DELETE删除 PUT更新 GET查询
const http = {
    post(url,params,isFile){
        if(isFile){
            return instanceAxios.post(url,params,{
                headers:{
                    'Content-Type':'multipart/form-data'
                },
            }).then((res)=>{
                return res;
            })
        }
        return instanceAxios.post(url,params).then((res)=>{
            return res;
        })
    },
    delete(url,params){
        return instanceAxios.delete(url,{params:params}).then((res)=>{
            return res;
        })
    },
    put(url,params){
        return instanceAxios.put(url,params).then((res)=>{
            return res;
        })
    },
    get(url,params){
        return instanceAxios.get(url,{params:params}).then((res)=>{
            return res;
        })
    },
};
window.instanceAxios = instanceAxios;
export default http
