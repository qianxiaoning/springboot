<template>
    <div class="usersPage">
        <el-dialog
        title="选择上级部门"
        :visible.sync="chooseDeptDialogVisible" class='chooseDeptForm'>
            <ul>    
                <li v-for="(item,index) in deptList" :key="item.id" v-show="item.isShow">
                    <div :style="{paddingLeft: 30*item.level + 'px'}">
                        <span class="expand" v-if="item.hasNode" @click="recycleToggleNode(item,deptList[index+1].isShow)">>></span>
                        <span :class="{chooseIndex:index===chooseDeptIndex}" class="deptName" @click="chooseDeptAction(index)">{{item.name}}</span>
                    </div>
                </li>
            </ul>               
            <el-button @click="chooseDeptDialogVisible=false">取消</el-button>
            <el-button type="primary" @click="submitDept()">确定</el-button>     
        </el-dialog>
        <el-dialog :title="isPost?'添加用户信息':'修改用户信息'" :visible.sync="postUserDialogVisible" class='userForm'>
            <el-form :model="userForm" ref="userForm">
                <el-form-item label="用户名称：" :label-width="formLabelWidth" prop="username">
                    <el-input v-model="userForm.username" autocomplete="on" placeholder="名称"></el-input>
                </el-form-item>
                <el-form-item label="密码：" :label-width="formLabelWidth" prop="password">
                    <el-input v-model="userForm.password" autocomplete="on" placeholder="名称" type="password"></el-input>
                </el-form-item>
                <el-form-item label="所属部门：" :label-width="formLabelWidth" prop="deptId">
                    <el-input autocomplete="on" placeholder="请选择" readonly class='chooseDeptDom' :value="deptName"></el-input>
                </el-form-item>
                <el-form-item label="邮箱：" :label-width="formLabelWidth" prop="email">
                    <el-input v-model="userForm.email" autocomplete="on" placeholder="邮箱"></el-input>
                </el-form-item>
                <el-form-item label="手机号：" :label-width="formLabelWidth" prop="mobile">
                    <el-input v-model="userForm.mobile" autocomplete="on" placeholder="手机号"></el-input>
                </el-form-item>
                <el-form-item label="角色：" :label-width="formLabelWidth" prop="roleIds">
                    <el-checkbox-group v-model="userForm.roleIds">
                        <el-checkbox :label="item.id" v-for="(item) in checkBoxList" :key="item.id" border>{{item.name}}</el-checkbox>                        
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item class="postUserFormBtns">
                    <el-button @click="postUserDialogVisible=false">取消</el-button>
                    <el-button type="primary" @click="submitUserInfo('userForm')">提交</el-button>
                </el-form-item>
            </el-form>            
        </el-dialog>
        <div class="head flex jc-sb">
            <span>用户管理</span>
            <div class="right">
                <input type="text" v-model="username" placeholder="用户名">
                <span @click="getUsers(username,1)">搜索</span>
                <span @click="isPost=true;postUserAction()">添加</span>
                <span @click="isPost=false;putUsersAction()">修改</span>
            </div>
        </div>        
        <div class="table">
            <table>
                <thead>
                    <tr>
                        <th>id</th>
                        <th>用户名</th>
                        <th>部门</th>
                        <th>邮箱</th>
                        <th>手机号</th>
                        <th>状态</th>
                        <th>创建时间</th>
                        <th>修改时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(item,index) in usersData.records" :key="item.id">
                        <td>
                            <input type="radio" :id="item.id" v-model="userId" :value="item.id" @click="chooseUserItemIndex=index">
                        </td>
                        <td>{{item.username}}</td>
                        <td>{{item.depts&&item.depts.name}}</td>
                        <td>{{item.email}}</td>
                        <td>{{item.mobile}}</td>
                        <td>{{item.valid===1?'启用':'禁用'}}</td>
                        <td>{{item.createdTime}}</td>
                        <td>{{item.modifiedTime}}</td>
                        <td>
                            <el-button @click='editValid(item.id,item.valid)'>{{item.valid===1?'禁用':'启用'}}</el-button>
                        </td>
                    </tr>
                </tbody>
            </table>    
        </div>
        <div class="btns">
            <span @click="getUsers(username,1)">首页</span>
            <span @click="lastPage()">上一页</span>
            <span @click="nextPage()">下一页</span>
            <span @click="getUsers(username,usersData.pageCount)">尾页</span>
            <span>总记录数（{{usersData.rowCount}}）</span>
            <span>总页数（{{usersData.pageCount}}）</span>
            <span>当前页（{{usersData.pageCurrent}}）</span>
        </div>   
    </div>
</template>
<script>
import { Message } from 'element-ui';
export default {
    data(){
        return{
            usersData:{},    
            username:'',
            pageCurrent:1,  
            userId:null,
            postUserDialogVisible:false,
            userForm: {
                id:null,
                username:'',
                password:'',
                deptId:null,
                email: '',
                mobile:'',
                roleIds:[]
            },            
            formLabelWidth: '100px',
            chooseDeptDialogVisible:false,
            deptName:'',
            chooseUserItemIndex:null,
            isPost:true,
            checkBoxList:[],
            deptList:[],
            chooseDeptIndex:null,
        }
    },
    mounted(){        
        //mybatisAssociationPra练习
        // this.getUserUserRolesByUserId();
        //根据userId查询UserUserRoleIds
        // this.getUserUserRoleIdsByUserId();
        this.getUsers(this.username,this.pageCurrent);    
        //查找所有的角色的checkbox对象
        this.getRolesCheckBox();
        //查询所有depts
        this.getDepts();
        //调动上级部门对话框
        this.chooseDept();
    },
    computed:{        
    },
    components:{
    },
    methods:{       
        lastPage(){
            if(this.pageCurrent > 1){
                this.pageCurrent --;
                this.getUsers(this.username,this.pageCurrent);    
            }
        },
        nextPage(){
            if(this.pageCurrent < this.usersData.pageCount){
                this.pageCurrent ++;
                this.getUsers(this.username,this.pageCurrent);    
            }
        },
        submitDept(){
            const dept = this.deptList[this.chooseDeptIndex];
            // console.log(dept);
            this.chooseDeptDialogVisible = false;
            if(!dept)return;
            this.userForm.deptId = dept.id;
            this.deptName = dept.name;
        },
        chooseDeptAction(index){
            this.chooseDeptIndex = index;
        }, 
        chooseDept(){            
            const dom = document.querySelector('.userForm');            
            dom.addEventListener('click',(e)=>{
                let target = e.target || e.srcElement;
                while(target.className !== 'chooseDeptDom el-input el-input--small'){
                    if(target === dom){
                        target = null;
                        break;
                    }
                    target = target.parentNode;
                }
                if(target){
                    this.chooseDeptDialogVisible = true;
                }else{
                    // console.log('未找到')
                }
            })
        },
        putUsersAction(){
            const userForm = this.userForm;
            const itemData = this.usersData.records[this.chooseUserItemIndex];
            // console.log(itemData);
            // return;
            // this.deleteUser(this.userId);
            if(!itemData)return;
            this.postUserDialogVisible = true;
            Object.keys(userForm).forEach((keys)=>{
                userForm[keys] = itemData[keys];
            });
            this.deptName = itemData.depts&&itemData.depts.name;
            // console.log(itemData);
            // console.log(userForm);
        },
        async postUser(callback){  
            const res = await this.$http.post(this.$urls.postUser,this.userForm);
            // console.log(res);
            Message({
                message:res.message,
                type:'success',
            });
            this.getUsers(this.username,this.pageCurrent);
            this.postUserDialogVisible = false;
            callback();
        },
        async putUser(callback){  
            // const putData = Object.assign({id:this.chooseUserItemData.id}, this.userForm);
            // console.log(putData);
            // return;
            const res = await this.$http.put(this.$urls.updateUserDeptRoleIdsById,this.userForm);
            // console.log(res);
            Message({
                message:res.message,
                type:'success',
            });
            this.getUsers(this.username,this.pageCurrent);
            this.postUserDialogVisible = false;
            callback();
        },        
        submitUserInfo(formName){
            console.log(this.userForm);
            // return;
            if(this.isPost){
                //新增
                this.postUser(()=>{                    
                    // console.log(1);
                    this.$refs[formName].resetFields();
                    this.deptName = '';
                });
            }else{
                //修改
                this.putUser(()=>{
                    // console.log(1);
                    this.$refs[formName].resetFields();
                    this.deptName = '';
                });
            }            
        },                
        //新增用户
        postUserAction(){
            this.postUserDialogVisible = true;
            // console.log(this.$refs);
            // this.$refs["userForm"].resetFields();
            // this.parentUserName = '';
        },
        //递归关闭子节点
        recycleToggleNode(item,isShow){            
            if(item.hasNode){
                this.deptList.forEach((childItem)=>{
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
        },
        handleDeptsData(){
            const oldArray = JSON.parse(JSON.stringify(this.deptList));
            console.log(oldArray);
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
            // console.log(oldArray);
            this.sortDeptsData(oldArray);
        },
        //给usersData排序
        sortDeptsData(oldArray){
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
            this.recycleHandleDeptsData(oldArray,newArray,0);
            // console.log(oldArray);
            // console.log(newArray);
        },
        //递归其它节点，按parentId排序层级，并赋值level
        recycleHandleDeptsData(oldArray,newArray,level){
            // console.log(oldArray);
            // console.log(newArray);
            for (let newIndex = newArray.length - 1; newIndex > -1; newIndex--) {
                // debugger;
                const newItem = newArray[newIndex];
                //给符合当前level的循环
                if(newItem.level === level){
                    for (let oldIndex = oldArray.length - 1; oldIndex > -1; oldIndex--) {
                        // debugger;
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
                        // debugger;
                    }                    
                }
            }            
            //递归，和结束条件
            if(oldArray.length){
                level ++;
                this.recycleHandleDeptsData(oldArray,newArray,level);
            }
            // console.log(oldArray);
            // console.log(newArray);
            this.deptList = newArray;
        },        
        //pra
        async getUserUserRolesByUserId(){
            const res = await this.$http.get(this.$urls.getUserUserRolesByUserId,{userId:15});
        },
        async editValid(id,valid){
            valid = valid === 0 ? 1 : 0;
            const res = await this.$http.put(this.$urls.updateValidByUserId,{id:id,valid:valid,username:"admin"});
            Message({
                message:res.message,
                type:'success',
            });
            this.getUsers(this.username,this.pageCurrent);
        },        
        //pra
        async getUserUserRoleIdsByUserId(){
            const res = await this.$http.get(this.$urls.getUserUserRoleIdsByUserId,{userId:15});
        },        
        async getRolesCheckBox(){
            const res = await this.$http.get(this.$urls.getRolesCheckBox);
            this.checkBoxList = res.data;
        },                
        async getDepts(){
            const res = await this.$http.get(this.$urls.getDepts);
            console.log(res);
            this.deptList = res.data;
            this.handleDeptsData();
        },                   
        async getUsers(username,pageCurrent){
            const res = await this.$http.get(this.$urls.getUsers,{username:username,pageCurrent:pageCurrent});
            console.log(res);
            this.usersData = res.data;
            this.pageCurrent = this.usersData.pageCurrent;
        },
        async deleteUser(id){  
            if(!confirm("确认删除吗?"))return;          
            const res = await this.$http.delete(this.$urls.deleteUser,{id:id});
            console.log(res);
            Message({
                message:res.message,
                type:'success',
            });
            this.getUsers(this.username,this.pageCurrent);
        },
        deleteUserAction(){
            // console.log(this.userId);
            this.deleteUser(this.userId);
        },        
    }
}
</script>
<style lang="scss">
.postUserFormBtns{
    .el-form-item__content{
        display: flex;justify-content: space-between;
    }
}
</style>
<style lang="scss" scoped>
.usersPage{
    .chooseDeptForm{
        ul{
            margin-bottom: 20px;padding-bottom: 20px;border-bottom: 1px solid #ccc;
            .deptName{
                cursor: pointer;
                &.chooseIndex{background: #ccc;}
            }    
        }
    }
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
                td:nth-child(1){text-align:center;}
            }        
            tbody{
                .expand{cursor: pointer;}
            }
        }
        // li{
        //     &.first{
        //         text-align: center;
        //     }
        //     border-bottom:1px solid #ccc;
        //     div{
        //         border-right:1px solid #ccc;
        //         &:nth-child(1){width:9%;}
        //         &:nth-child(2){width:9%;}
        //         &:nth-child(3){width:9%;}
        //         &:nth-child(4){width:9%;}
        //         &:nth-child(5){width:9%;}
        //         &:nth-child(6){width:9%;}
        //         &:nth-child(7){width:18%;}
        //         &:nth-child(8){width:28%;}
        //     }
        // }
    }    
    .btns{
        text-align: right;
        span{border:1px solid #ccc;padding: 4px;cursor: pointer;border-radius:4px;}
    }
}
</style>
