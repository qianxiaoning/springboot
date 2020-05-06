<template>
    <div class="rolesPage">        
        <el-dialog :title="isPost?'添加角色信息':'修改角色信息'" :visible.sync="roleDialogVisible" class='roleForm'>
            <el-form :model="roleForm" ref="roleForm">                
                <el-form-item label="角色名称：" :label-width="formLabelWidth" prop=".roles.name">
                    <el-input v-model="roleForm.roles.name" autocomplete="on" placeholder="名称"></el-input>
                </el-form-item>
                <el-form-item label="角色描述：" :label-width="formLabelWidth" prop=".roles.note">
                    <el-input v-model="roleForm.roles.note" autocomplete="on" placeholder="描述"></el-input>
                </el-form-item>
                <el-form-item label="角色授权：" :label-width="formLabelWidth" prop="menusIds">
                    <ul>    
                        <li v-for="(item,index) in menusData" :key="item.id" v-show="item.isShow">
                            <div :style="{paddingLeft: 30*item.level + 'px'}">
                                <span class="expand" v-if="item.hasNode" @click="recycleToggleNode(item,menusData[index+1].isShow)">>></span>
                                <input type="checkbox" v-model="roleForm.menusIds" :value="item.id" @change="changeMenu(item,index,$event)">
                                <span>{{item.name}}</span>
                            </div>
                        </li>
                    </ul>
                </el-form-item>               
                <el-form-item class="postRoleFormBtns">
                    <el-button @click="roleDialogVisible=false">取消</el-button>
                    <el-button type="primary" @click="submitRoleInfo('roleForm')">提交</el-button>
                </el-form-item>
            </el-form>            
        </el-dialog>
        <div class="head flex jc-sb">
            <span>角色管理</span>
            <div class="right">
                <input type="text" v-model="username" placeholder="角色名">
                <span @click="getRoles(username,1)">搜索</span>
                <span @click="isPost=true;postRoleAction()">添加</span>
                <!-- <span @click="deleteRolesAction()">删除</span> -->
            </div>
        </div>
        <div class="table">
            <table>
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>角色名称</th>
                        <th>备注</th>
                        <th>创建时间</th>
                        <th>修改时间</th>
                        <th>创建用户</th>
                        <th>修改用户</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(item,index) in rolesData.records" :key="item.id">                       
                        <td>{{index+1}}</td>
                        <td>{{item.name}}</td>
                        <td>{{item.note}}</td>
                        <td>{{item.createdTime}}</td>
                        <td>{{item.modifiedTime}}</td>
                        <td>{{item.createdUser}}</td>
                        <td>{{item.modifiedUser}}</td>
                        <td class="tableListBtns">
                            <span @click="deleteRoleAction(item.id)">delete</span>
                            <span @click="isPost=false;updateRoleAction(item)">update</span>
                        </td>
                    </tr>
                </tbody>
            </table>    
        </div>
        <div class="btns">
            <span @click="getRoles(username,1)">首页</span>
            <span @click="lastPage()">上一页</span>
            <span @click="nextPage()">下一页</span>
            <span @click="getRoles(username,rolesData.pageCount)">尾页</span>
            <span>总记录数（{{rolesData.rowCount}}）</span>
            <span>总页数（{{rolesData.pageCount}}）</span>
            <span>当前页（{{rolesData.pageCurrent}}）</span>
        </div>    
    </div>    
</template>
<script>
import { Message } from 'element-ui';
export default {
    data(){
        return{
            username:'',
            pageCurrent:1,
            rolesData:{},
            menusData:[],
            roleDialogVisible:false,
            formLabelWidth: '100px',
            // chooseParentRoleIndex:null,
            // chooseRoleItemData:null,
            isPost:true,
            roleForm: {
                roles:{
                    name: '',
                    note:'',
                    id:null
                },
                menusIds:[]
            },
        }
    },
    mounted(){
        this.getRoles(this.username,this.pageCurrent);
        this.getMenus(); 
        //纯属试验java数据层resultMap查两表的用法
        this.getRolesMenus(52);       
    },
    components:{
    },
    methods:{
        updateRoleAction(item){
            console.log(item);
            //修改的回显
            this.roleForm.roles.name = item.name;
            this.roleForm.roles.note = item.note;
            this.roleForm.roles.id = item.id;
            this.selectMenusIdsByRoleId(item.id);
            this.roleDialogVisible = true;
        },
        async selectMenusIdsByRoleId(id){
            const res = await this.$http.get(this.$urls.selectMenusIdsByRoleId,{rolesId:id});
            // console.log(res);
            this.roleForm.menusIds = res.data;
        },
        async deleteRole(id){  
            // console.log(id);
            if(!confirm("确认删除吗?"))return;          
            const res = await this.$http.delete(this.$urls.deleteRole,{id:id});
            // console.log(res);
            Message({
                message:res.message,
                type:'success',
            });
            this.getRoles(this.username,this.pageCurrent);
        },
        deleteRoleAction(id){
            // console.log(this.menuId);
            this.deleteRole(id);
        },
        changeMenu(item,index,e){
            // console.log(this.menusData);
            let menusIds = JSON.parse(JSON.stringify(this.roleForm.menusIds));
            //根节点必添加
            const checked = e.target.checked;
            // console.log(e.target.checked);
            if(checked){                
                menusIds.push(this.menusData[0].id);  
                //要求1.选中节点，祖先节点必须被选中
                let currentParentId = item.parentId;
                // debugger;
                while(index>1){
                    const prevObj = this.menusData[index-1];
                    if(prevObj.id === currentParentId){
                        menusIds.push(prevObj.id);
                        currentParentId = prevObj.parentId;
                    }
                    index--;
                }        
                //利用Set把数组去重
                menusIds = [...new Set(menusIds)];
                // debugger;                                  
            }else{
                //要求2.取消hasNode节点，子孙节点必须被取消
                if(item.hasNode){
                    // debugger;
                    //当前hasNode及子孙hasNode的id数组
                    let currentIds = [item.id];
                    //循环当前节点后面的节点
                    while(index < this.menusData.length-1){
                        // console.log(this.menusData);
                        const nextObj = this.menusData[index+1];
                        // debugger;
                        //nextObj的parentId在hasNode数组中能发现时
                        if(currentIds.findIndex(item => item === nextObj.parentId) > -1){
                            // debugger;
                            //判断nextObj的id是否在menuIds中，是否是选中状态
                            const childIdIsInMenuIds = menusIds.findIndex(item => item === nextObj.id);
                            //选中的，从MenuIds中删除
                            if(childIdIsInMenuIds>-1){
                                menusIds.splice(childIdIsInMenuIds, 1);
                            }
                            //子孙hasNode节点的id添加到hasNode数组中
                            if(nextObj.hasNode){
                                currentIds.push(nextObj.id);
                            }
                        }                        
                        index++;
                    }            
                    // debugger;        
                }
            }
            // debugger;
            this.roleForm.menusIds = menusIds;
            // console.log(e);
        },
        submitRoleInfo(formName){
            // console.log(this.roleForm);
            // console.log(this.isPost);
            if(this.isPost){
                //新增
                this.postRole(()=>{                    
                    // console.log(1);
                    this.$refs[formName].resetFields();
                });
            }else{
                //修改
                this.putRole(()=>{
                    // console.log(1);
                    this.$refs[formName].resetFields();
                });
            }            
        },
        async postRole(callback){  
            const res = await this.$http.post(this.$urls.postRole,this.roleForm);
            // console.log(res);
            Message({
                message:res.message,
                type:'success',
            });
            this.getRoles(this.username,this.pageCurrent);
            this.roleDialogVisible = false;
            callback();
        },
        async putRole(callback){  
            const res = await this.$http.put(this.$urls.putRole,this.roleForm);
            // console.log(res);
            Message({
                message:res.message,
                type:'success',
            });
            this.getRoles(this.username,this.pageCurrent);
            this.roleDialogVisible = false;
            callback();
        },
        //新增角色
        postRoleAction(){
            this.roleDialogVisible = true;
            // console.log(this.$refs);
            // this.$refs["roleForm"].resetFields();
        },
        async getRoles(username,pageCurrent){
            const res = await this.$http.get(this.$urls.getRoles,{name:username,pageCurrent:pageCurrent});
            console.log(res);
            this.rolesData = res.data;
            this.pageCurrent = this.rolesData.pageCurrent;
        },
        lastPage(){
            if(this.pageCurrent > 1){
                this.pageCurrent --;
                this.getRoles(this.username,this.pageCurrent);    
            }
        },
        nextPage(){
            if(this.pageCurrent < this.rolesData.pageCount){
                this.pageCurrent ++;
                this.getRoles(this.username,this.pageCurrent);    
            }
        },
        // async deleteRoles(ids){  
        //     // console.log(ids.toString());
        //     if(!confirm("确认删除吗?"))return;          
        //     const res = await this.$http.delete(this.$urls.deleteRoles,{ids:ids.toString()});
        //     console.log(res);
        //     Message({
        //         message:res.message,
        //         type:'success',
        //     });
        //     this.getRoles(this.username,this.pageCurrent);
        // },
        // deleteRolesAction(){
        //     this.deleteRoles(this.idsCheckbox);
        // },
        //递归关闭子节点
        recycleToggleNode(item,isShow){            
            if(item.hasNode){
                this.menusData.forEach((childItem)=>{
                    if(childItem.parentId==item.id){
                        if(isShow){
                            childItem.isShow = false;
                        }else{
                            childItem.isShow = true;
                        }
                        this.recycleToggleNode(childItem,isShow);
                    }
                })
            }
            // console.log(this.menusData);
        },
        handleMenusData(){
            // console.log(this.menusData);
            const oldArray = JSON.parse(JSON.stringify(this.menusData));
            // console.log(this.menusData);
            oldArray.forEach((item1)=>{
                //添加hasNode属性
                oldArray.forEach((item2)=>{
                    if(item2.parentId==item1.id){
                        item1.hasNode=true;
                        // this.$set(item1, 'hasNode', true);
                    }                    
                });
                //添加isShow属性
                if(item1.parentId){
                    //其它节点
                    item1.isShow=false;
                    // this.$set(item1, 'isShow', false);
                }else{
                    //根节点
                    item1.isShow=true;
                    // this.$set(item1, 'isShow', true);
                }
            });
            // console.log(this.menusData);
            this.sortMenusData(oldArray);
        },
        //给menusData排序
        sortMenusData(oldArray){
            //第一次循环，取出根节点
            // console.log(oldArray);
            const newArray = [];
            for (let index = oldArray.length - 1; index > -1; index--) {
                const item = oldArray[index];
                if(!item.parentId){
                    oldArray.splice(index,1);
                    // 并添加level为0
                    item.level = 0;
                    //从前插入新数组
                    newArray.unshift(item);
                }
            }            
            //第二次循环
            this.recycleHandleMenusData(oldArray,newArray,0);
            // console.log(oldArray);
            // console.log(newArray);
        },
        //递归其它节点，按parentId排序层级，并赋值level
        recycleHandleMenusData(oldArray,newArray,level){
            // console.log(oldArray);
            // console.log(newArray);
            for (let newIndex = newArray.length - 1; newIndex > -1; newIndex--) {
                const newItem = newArray[newIndex];
                //给符合当前level的循环
                if(newItem.level === level){
                    for (let oldIndex = oldArray.length - 1; oldIndex > -1; oldIndex--) {
                        const oldItem = oldArray[oldIndex];
                        //当前level的id 为 旧数组的parentId
                        if(newItem.id === oldItem.parentId){
                            //插入新数组新
                            //赋值对应level
                            oldItem.level = level + 1;
                            newArray.splice(newIndex + 1,0,oldItem);
                            //去旧
                            oldArray.splice(oldIndex,1);
                        }
                    }                    
                }
            }            
            //递归，和结束条件
            if(oldArray.length){
                level ++;
                this.recycleHandleMenusData(oldArray,newArray,level);
            }
            // console.log(oldArray);
            // console.log(newArray);
            this.menusData = newArray;
        },
        async getMenus(){
            const res = await this.$http.get(this.$urls.getMenus);
            // console.log(res);
            this.menusData = res.data;
            this.handleMenusData();
        },        
        async getRolesMenus(id){
            const res = await this.$http.get(this.$urls.selectRolesMenusByRoleId,{id:id});
            // console.log(res);
        },        
    }
}
</script>
<style lang="scss" scoped>
.rolesPage{
    .head{
        .right{
            span{border:1px solid #ccc;padding: 4px;cursor: pointer;border-radius:4px;}
        }
    }
    .table{
        height: 400px;overflow: auto;
        table{
            border-collapse: collapse;width: 100%;
            tr{
                border-bottom: 1px solid #ccc;
                th,td{border-right: 1px solid #ccc;}
                td.tableListBtns{
                    span{border:1px solid #ccc;cursor: pointer;}
                }
            }        
        }
    }
    .btns{
        text-align: right;
        span{border:1px solid #ccc;padding: 4px;cursor: pointer;border-radius:4px;}
    }
}
</style>
