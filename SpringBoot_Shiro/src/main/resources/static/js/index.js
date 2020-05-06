window.addEventListener('load', () => {
	loadPage();
});
function loadPage(){
	const container = document.querySelector('.pages');
	const btns = document.querySelectorAll('.btns span');
	//上一个页面的js文件
	let lastJsTag = null;
	btns.forEach((item) => {
		const src = item.getAttribute("src");
		item.addEventListener('click', () => {
			fetch(`${src}.html`, {
				'method': 'get',
				'headers': {
					'content-type': 'text/html;charset=UTF-8',
				},
			}).then(res=>res.text()).then(res => {
				//上一个存在则删除
				lastJsTag && document.body.removeChild(lastJsTag);
				container.innerHTML=res;
				if(src){
					//添加js文件
					const newJs = document.createElement("script");
					newJs.setAttribute("type", "text/javascript");
					newJs.setAttribute(
							"src",
							`js/${src}.js`
					);
					lastJsTag = document.body.appendChild(newJs);
				}
			});
		});
	});
}