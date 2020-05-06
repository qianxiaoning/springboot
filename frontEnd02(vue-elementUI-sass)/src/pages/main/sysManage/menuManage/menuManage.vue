<template>
    <div class="menusPage">
        <el-dialog
        title="选择上级菜单"
        :visible.sync="chooseParentMenuDialogVisible" class='chooseParentMenuForm'>
            <ul>    
                <li v-for="(item,index) in menusData" :key="item.id" v-show="item.isShow">
                    <div :style="{paddingLeft: 30*item.level + 'px'}">
                        <span class="expand" v-if="item.hasNode" @click="recycleToggleNode(item,menusData[index+1].isShow)">>></span>
                        <span :class="{chooseIndex:index===chooseParentMenuIndex}" class="parentMenuName" @click="chooseParentMenuAction(index)">{{item.name}}</span>
                    </div>
                </li>
            </ul>               
            <el-button @click="chooseParentMenuDialogVisible=false">取消</el-button>
            <el-button type="primary" @click="submitParentMenu()">确定</el-button>     
        </el-dialog>
        <el-dialog :title="isPost?'添加菜单信息':'修改菜单信息'" :visible.sync="postMenuDialogVisible" class='menuForm'>
            <el-form :model="menuForm" ref="menuForm">
                <el-form-item label="类型：" :label-width="formLabelWidth" prop="type">
                    <el-radio v-model.number="menuForm.type" :label="1">菜单</el-radio>
                    <el-radio v-model.number="menuForm.type" :label="2">按钮</el-radio>                    
                </el-form-item>   
                <el-form-item label="菜单名称：" :label-width="formLabelWidth" prop="name">
                    <el-input v-model="menuForm.name" autocomplete="on" placeholder="名称"></el-input>
                </el-form-item>
                <el-form-item label="上级菜单：" :label-width="formLabelWidth" prop="parentId">
                    <el-input autocomplete="on" placeholder="请选择" readonly class='chooseParentMenuDom' :value="parentMenuName"></el-input>
                </el-form-item>
                <el-form-item label="菜单URL：" :label-width="formLabelWidth" prop="url">
                    <el-input v-model="menuForm.url" autocomplete="on" placeholder="url"></el-input>
                </el-form-item>
                <el-form-item label="授权标识：" :label-width="formLabelWidth" prop="permission">
                    <el-input v-model="menuForm.permission" autocomplete="on" placeholder="多个用逗号分隔，如：user:list,user:create"></el-input>
                </el-form-item>
                <el-form-item label="排序号：" :label-width="formLabelWidth" prop="sort">
                    <el-input v-model="menuForm.sort" autocomplete="on" placeholder="排序号"></el-input>
                </el-form-item>
                <el-form-item class="postMenuFormBtns">
                    <el-button @click="postMenuDialogVisible=false">取消</el-button>
                    <el-button type="primary" @click="submitMenuInfo('menuForm')">提交</el-button>
                </el-form-item>
            </el-form>            
        </el-dialog>
        <div class="head flex jc-sb">
            <span>菜单管理</span>
            <div class="right">
                <span @click="deleteMenuAction()">删除</span>
                <span @click="isPost=true;postMenuAction()">添加</span>
                <span @click="isPost=false;putMenusAction()">修改</span>
            </div>
        </div>
        <div class="table">
            <table>
                <thead>
                    <tr>
                        <th>选择</th>
                        <th>菜单id</th>
                        <th>菜单名称</th>
                        <th>上级菜单</th>
                        <th>类型</th>
                        <th>排序号</th>
                        <th>菜单url</th>
                        <th>授权标识</th>
                    </tr>
                </thead>
                <tbody>                                   
                    <tr v-for="(item,index) in menusData" :key="item.id" v-show="item.isShow">
                        <td>
                            <input type="radio" name='id' :value='item.id' v-model="menuId" @click="chooseMenuItemData=item">
                        </td>
                        <td :style="{paddingLeft: 20*item.level + 'px'}">
                            <span class="expand" v-if="item.hasNode" @click="recycleToggleNode(item,menusData[index+1].isShow)">>></span>
                            {{item.id}}
                        </td>
                        <td>{{item.name}}</td>
                        <td>{{item.parentName}}</td>
                        <td>{{item.type === 1 ? '菜单' : '按钮'}}</td>
                        <td>{{item.sort}}</td>
                        <td>{{item.url}}</td>
                        <td>{{item.permission}}</td>
                    </tr>
                </tbody>
            </table>            
        </div>    
    </div>
</template>
<script>
import { Message } from 'element-ui';
export default {
    data(){
        return{
            menusData:[],      
            menuId:'',
            postMenuDialogVisible:false,
            menuForm: {
                name: '',
                url:'',
                type:null,
                sort:null,
                permission:'',
                parentId:null
            },            
            formLabelWidth: '100px',
            chooseParentMenuDialogVisible:false,
            chooseParentMenuIndex:null,
            parentMenuName:'',
            chooseMenuItemData:null,
            isPost:true
        }
    },
    mounted(){        
        this.getMenus();        
        this.chooseParentMenu();
    },
    computed:{        
    },
    components:{
    },
    methods:{        
        putMenusAction(){
            const menuForm = this.menuForm;
            const itemData = this.chooseMenuItemData;
            // this.deleteMenu(this.menuId);
            if(itemData === null)return;
            this.postMenuDialogVisible = true;
            Object.keys(menuForm).forEach((keys)=>{
                menuForm[keys] = itemData[keys];
            });
            this.parentMenuName = itemData.parentName;
            // console.log(itemData);
            // console.log(menuForm);
        },
        async postMenu(callback){  
            const res = await this.$http.post(this.$urls.postMenu,this.menuForm);
            // console.log(res);
            Message({
                message:res.message,
                type:'success',
            });
            this.getMenus();
            this.postMenuDialogVisible = false;
            callback();
        },
        async putMenu(callback){  
            const putData = Object.assign({id:this.chooseMenuItemData.id}, this.menuForm);
            console.log(putData);
            // return;
            const res = await this.$http.put(this.$urls.putMenu,putData);
            // console.log(res);
            Message({
                message:res.message,
                type:'success',
            });
            this.getMenus();
            this.postMenuDialogVisible = false;
            callback();
        },
        submitParentMenu(){
            const parentMenu = this.menusData[this.chooseParentMenuIndex];
            // console.log(parentMenu);
            this.chooseParentMenuDialogVisible = false;
            if(!parentMenu)return;
            this.menuForm.parentId = parentMenu.id;
            this.parentMenuName = parentMenu.name;
        },
        submitMenuInfo(formName){
            // console.log(this.menuForm);
            // console.log(this.isPost);
            if(this.isPost){
                //新增
                this.postMenu(()=>{                    
                    // console.log(1);
                    this.$refs[formName].resetFields();
                    this.parentMenuName = '';
                });
            }else{
                //修改
                this.putMenu(()=>{
                    // console.log(1);
                    this.$refs[formName].resetFields();
                    this.parentMenuName = '';
                });
            }            
        },
        //选择上级菜单
        chooseParentMenuAction(index){
            this.chooseParentMenuIndex = index;
        },
        //打开上级菜单对话框
        chooseParentMenu(){            
            const dom = document.querySelector('.menuForm');            
            dom.addEventListener('click',(e)=>{
                let target = e.target || e.srcElement;
                while(target.className !== 'chooseParentMenuDom el-input el-input--small'){
                    if(target === dom){
                        target = null;
                        break;
                    }
                    target = target.parentNode;
                }
                if(target){
                    this.chooseParentMenuDialogVisible = true;
                }else{
                    // console.log('未找到')
                }
            })
        },
        //新增菜单
        postMenuAction(){
            this.postMenuDialogVisible = true;
            // console.log(this.$refs);
            // this.$refs["menuForm"].resetFields();
            // this.parentMenuName = '';
        },
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
        async deleteMenu(id){  
            if(!confirm("确认删除吗?"))return;          
            const res = await this.$http.delete(this.$urls.deleteMenu,{id:id});
            console.log(res);
            Message({
                message:res.message,
                type:'success',
            });
            this.getMenus();
        },
        deleteMenuAction(){
            // console.log(this.menuId);
            this.deleteMenu(this.menuId);
        },        
    }
}
</script>
<style lang="scss">
.postMenuFormBtns{
    .el-form-item__content{
        display: flex;justify-content: space-between;
    }
}
</style>
<style lang="scss" scoped>
.menusPage{
    .chooseParentMenuForm{
        ul{
            margin-bottom: 20px;padding-bottom: 20px;border-bottom: 1px solid #ccc;
            .parentMenuName{
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
}
</style>
