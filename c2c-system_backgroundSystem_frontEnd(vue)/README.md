### c2c-system_backgroundSystem_frontEnd(vue)
#### c2c-system后台站点
#### 配套后台c2c-system(springboot+mybatisPlus+PowerDesigner+MultipartFile+nginx+linux+redis+aop+HttpClient+jsonp+sso+dubbo+zookeeper+Cookie+Interceptor+ThreadLocal+Quartz)
```
概要：
1.axios配合FormData上传文件
1.1.http/http.js 扩展http.post
post(url,params,isFile){
    if(isFile){
        return instanceAxios.post(url,params,{
            headers:{
                //*'Content-Type':'multipart/form-data'
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
1.2.addProduct.uploadPic()
//*传入FormData对象uploadImg
this.$http.post(this.$urls.uploadImg,picFiles,1);

FormData中的多图片上传，
for (let i = 0; i < files.length; i++) {
    picFiles.append('fileImage', files[i])
}
console.log(picFiles);
console.log(picFiles.getAll("fileImage"));
new FormData().getAll("fileImage");能获取到key为"fileImage"的多个文件对象
```
