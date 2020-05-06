const host_dev = '';//开发以'/'本地localhost发起
const host_prod = 'http://a.cloud.cn/api';
// 根据 process.env.NODE_ENV 判断 开发或生产接口地址
const host = process.env.NODE_ENV == 'development' ? host_dev : host_prod; 

// 后台各个服务地址头
// const hostV1 = host + '/v1';
// const hostV2 = host + '/v2';
// const hostV3 = host + '/v3';
// const hostV4 = host + '/v4';  

const urls = {
    //查询logs
    getLogs:host+'/logs/doFindPageObjects',
    //删除logs
    deleteLogs:host+'/logs/deleteObjects',
    //查询menus
    getMenus:host+'/menus/doFindObjects',
    //删除menus
    deleteMenu:host+'/menus/doDeleteObject',
    //新增menus
    postMenu:host+'/menus/doInsertObject',
    //修改menus
    putMenu:host+'/menus/doUpdateObject',
    //查询depts
    getDepts:host+'/depts/doFindObjects',
    //新增dept
    postDept:host+'/depts/doInsertObject',
    //修改dept
    putDept:host+'/depts/doUpdateObject',
    //删除dept
    deleteDept:host+'/depts/doDeleteObject',
    //查询roles
    getRoles:host+'/roles/doFindPageObjects',
    //查找所有的角色的checkbox对象
    getRolesCheckBox:host+'/roles/doGetRolesCheckBox',
    //删除roles
    deleteRole:host+'/roles/doDeleteObject',
    //添加roles
    postRole:host+'/roles/doInsertObject',
    //修改role
    putRole:host+'/roles/doUpdateObject',
    //基于rolesId查询对应menusIds
    selectMenusIdsByRoleId:host+'/roles/selectMenusIdsByRoleId',
    //纯属试验java数据层resultMap查两次的用法
    selectRolesMenusByRoleId:host+'/roles/doFindRolesMenusByRolesId',
    //查询users
    getUsers:host+'/users/doFindPageUsers',
    //mybatisAssociationPra练习
    getUserDeptRolesByUserId:host+'/users/doGetUserDeptRolesByUserId',
    //根据userId查询UserDeptRoleIds
    getUserUserRoleIdsByUserId:host+'/users/doGetUserDeptRoleIdsByUserId',
    //添加user
    postUser:host+'/users/doInsertUser',
    //根据userId修改valid
    updateValidByUserId:host+'/users/updateValidByUserId',
    //根据userId修改UserDeptRoleIds
    updateUserDeptRoleIdsById:host+'/users/updateUserDeptRoleIdsById',
}

export default urls;