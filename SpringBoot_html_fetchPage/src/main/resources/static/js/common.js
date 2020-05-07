//公共js
window.addEventListener('load', () => {
	commonRunning();
});　
function commonRunning(){
	
}
//fetch
function myFetch(url,data,handle,successFun){
	fetch(url, data).then(res => handle(res)).then(res => { // 接受时
		//请求页面
		if(typeof res === "string"){
			successFun(res);
		}else if(res.code){
			//请求接口
			if(res.code === 200){			
				successFun(res);
			}else if(res.code === 403){
				console.log("error");
			}
		}
    }, res => { // 拒绝时
        console.log(res.desc);
    }).catch(err => {
        console.log(err);
    });
}