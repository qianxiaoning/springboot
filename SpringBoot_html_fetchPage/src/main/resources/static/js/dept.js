deptList();
function deptList(){
	const listWrap = document.querySelector('.listWrap');
	myFetch('/depts/findDepts',
			{
				'method': 'get',
				'headers': {
					'content-type': 'application/json;charset=UTF-8'
				}
			},
			(res)=>{return res.json()},
			(res)=>{
				let context = '';
				res.data.forEach((item)=>{
					context += 
					'<tr>'+
						'<td>'+item.id+'</td>'+						
						'<td>'+item.name+'</td>'+						
						'<td>'+item.note+'</td>'+						
					'</tr>';
				});
				listWrap.innerHTML = context;
			}
	);
}
