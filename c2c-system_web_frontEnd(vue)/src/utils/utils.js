// 公共方法 按需引入 如import {putJsonpScript} from '@/utils/utils';
const putJsonpScript=(url,methodName)=>{
    const script = document.createElement("script");
    script.src = url + `?callback=${methodName}`;
    document.getElementsByTagName("body")[0].appendChild(script);
}
export{
    putJsonpScript
}