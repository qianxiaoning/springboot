logList();
function logList(){
	const listWrap = document.querySelector('.listWrap');
	myFetch('/logs/findLogs',
			{
				'method': 'get',
				'headers': {
					'content-type': 'application/json;charset=UTF-8'
				}
			},
			(res)=>{return res.json()},
			(res)=>{
				let context = '';
				res.data.forEach((item,index)=>{
					context += 
					'<tr>'+
						'<td>'+(index+1)+'</td>'+						
						'<td>'+item.username+'</td>'+						
						'<td>'+item.operation+'</td>'+						
						'<td>'+item.createdTime+'</td>'+						
					'</tr>';
				});
				listWrap.innerHTML = context;
			}
	);
}
