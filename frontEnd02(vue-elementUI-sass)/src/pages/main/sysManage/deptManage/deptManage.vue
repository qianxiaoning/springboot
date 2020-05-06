<template>
    <div class="deptsPage">
        <el-dialog
        title="选择上级部门"
        :visible.sync="chooseParentDeptDialogVisible" class='chooseParentDeptForm'>
            <ul>    
                <li v-for="(item,index) in deptsData" :key="item.id" v-show="item.isShow">
                    <div :style="{paddingLeft: 30*item.level + 'px'}">
                        <span class="expand" v-if="item.hasNode" @click="recycleToggleNode(item,deptsData[index+1].isShow)">>></span>
                        <span :class="{chooseIndex:index===chooseParentDeptIndex}" class="parentDeptName" @click="chooseParentDeptAction(index)">{{item.name}}</span>
                    </div>
                </li>
            </ul>               
            <el-button @click="chooseParentDeptDialogVisible=false">取消</el-button>
            <el-button type="primary" @click="submitParentDept()">确定</el-button>     
        </el-dialog>
        <el-dialog :title="isPost?'添加部门信息':'修改部门信息'" :visible.sync="postDeptDialogVisible" class='deptForm'>
            <el-form :model="deptForm" ref="deptForm">
                <el-form-item label="部门名称：" :label-width="formLabelWidth" prop="name">
                    <el-input v-model="deptForm.name" autocomplete="on" placeholder="名称"></el-input>
                </el-form-item>
                <el-form-item label="上级部门：" :label-width="formLabelWidth" prop="parentId">
                    <el-input autocomplete="on" placeholder="请选择" readonly class='chooseParentDeptDom' :value="parentDeptName"></el-input>
                </el-form-item>
                <el-form-item label="排序号：" :label-width="formLabelWidth" prop="sort">
                    <el-input v-model.number="deptForm.sort" autocomplete="on" placeholder="排序号"></el-input>
                </el-form-item>
                <el-form-item label="备注：" :label-width="formLabelWidth" prop="note">
                    <el-input v-model="deptForm.note" autocomplete="on" placeholder="备注"></el-input>
                </el-form-item>
                <el-form-item class="postDeptFormBtns">
                    <el-button @click="postDeptDialogVisible=false">取消</el-button>
                    <el-button type="primary" @click="submitDeptInfo('deptForm')">提交</el-button>
                </el-form-item>
            </el-form>            
        </el-dialog>
        <div class="head flex jc-sb">
            <span>部门管理</span>
            <div class="right">
                <span @click="deleteDeptAction()">删除</span>
                <span @click="isPost=true;postDeptAction()">添加</span>
                <span @click="isPost=false;putDeptsAction()">修改</span>
            </div>
        </div>
        <div class="table">
            <table>
                <thead>
                    <tr>
                        <th>选择</th>
                        <th>部门id</th>
                        <th>部门名称</th>
                        <th>上级部门</th>
                        <th>排序号</th>
                    </tr>
                </thead>
                <tbody>                                   
                    <tr v-for="(item,index) in deptsData" :key="item.id" v-show="item.isShow">
                        <td>
                            <input type="radio" name='id' :value='item.id' v-model="deptId" @click="chooseDeptItemData=item">
                        </td>
                        <td>{{item.id}}</td>
                        <td :style="{paddingLeft: 20*item.level + 'px'}">
                            <span class="expand" v-if="item.hasNode" @click="recycleToggleNode(item,deptsData[index+1].isShow)">>></span>
                            {{item.name}}
                        </td>
                        <td>{{item.parentName}}</td>
                        <td>{{item.sort}}</td>
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
            deptsData:[],      
            deptId:'',
            postDeptDialogVisible:false,
            deptForm: {
                name: '',
                note:null,
                sort:null,
                parentId:null
            },            
            formLabelWidth: '100px',
            chooseParentDeptDialogVisible:false,
            chooseParentDeptIndex:null,
            parentDeptName:'',
            chooseDeptItemData:null,
            isPost:true
        }
    },
    mounted(){        
        this.getDepts();        
        this.chooseParentDept();
    },
    computed:{        
    },
    components:{
    },
    methods:{        
        putDeptsAction(){
            const deptForm = this.deptForm;
            const itemData = this.chooseDeptItemData;
            // this.deleteDept(this.deptId);
            if(itemData === null)return;
            this.postDeptDialogVisible = true;
            Object.keys(deptForm).forEach((keys)=>{
                deptForm[keys] = itemData[keys];
            });
            this.parentDeptName = itemData.parentName;
            // console.log(itemData);
            // console.log(deptForm);
        },
        async postDept(callback){  
            const res = await this.$http.post(this.$urls.postDept,this.deptForm);
            // console.log(res);
            Message({
                message:res.message,
                type:'success',
            });
            this.getDepts();
            this.postDeptDialogVisible = false;
            callback();
        },
        async putDept(callback){  
            const putData = Object.assign({id:this.chooseDeptItemData.id}, this.deptForm);
            console.log(putData);
            // return;
            const res = await this.$http.put(this.$urls.putDept,putData);
            // console.log(res);
            Message({
                message:res.message,
                type:'success',
            });
            this.getDepts();
            this.postDeptDialogVisible = false;
            callback();
        },
        submitParentDept(){
            const parentDept = this.deptsData[this.chooseParentDeptIndex];
            // console.log(parentDept);
            this.chooseParentDeptDialogVisible = false;
            if(!parentDept)return;
            this.deptForm.parentId = parentDept.id;
            this.parentDeptName = parentDept.name;
        },
        submitDeptInfo(formName){
            // console.log(this.deptForm);
            // console.log(this.isPost);
            if(this.isPost){
                //新增
                this.postDept(()=>{                    
                    // console.log(1);
                    this.$refs[formName].resetFields();
                    this.parentDeptName = '';
                });
            }else{
                //修改
                this.putDept(()=>{
                    // console.log(1);
                    this.$refs[formName].resetFields();
                    this.parentDeptName = '';
                });
            }            
        },
        //选择上级部门
        chooseParentDeptAction(index){
            this.chooseParentDeptIndex = index;
        },
        //打开上级部门对话框
        chooseParentDept(){            
            const dom = document.querySelector('.deptForm');            
            dom.addEventListener('click',(e)=>{
                let target = e.target || e.srcElement;
                while(target.className !== 'chooseParentDeptDom el-input el-input--small'){
                    if(target === dom){
                        target = null;
                        break;
                    }
                    target = target.parentNode;
                }
                if(target){
                    this.chooseParentDeptDialogVisible = true;
                }else{
                    // console.log('未找到')
                }
            })
        },
        //新增部门
        postDeptAction(){
            this.postDeptDialogVisible = true;
            // console.log(this.$refs);
            // this.$refs["deptForm"].resetFields();
            // this.parentDeptName = '';
        },
        //递归关闭子节点
        recycleToggleNode(item,isShow){            
            if(item.hasNode){
                this.deptsData.forEach((childItem)=>{
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
            // console.log(this.deptsData);
        },
        handleDeptsData(){
            // console.log(this.deptsData);
            const oldArray = JSON.parse(JSON.stringify(this.deptsData));
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
        //给deptsData排序
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
            this.deptsData = newArray;
        },
        async getDepts(){
            const res = await this.$http.get(this.$urls.getDepts);
            console.log(res);
            this.deptsData = res.data;
            this.handleDeptsData();
        },        
        async deleteDept(id){  
            if(!confirm("确认删除吗?"))return;          
            const res = await this.$http.delete(this.$urls.deleteDept,{id:id});
            console.log(res);
            Message({
                message:res.message,
                type:'success',
            });
            this.getDepts();
        },
        deleteDeptAction(){
            // console.log(this.deptId);
            this.deleteDept(this.deptId);
        },        
    }
}
</script>
<style lang="scss">
.postDeptFormBtns{
    .el-form-item__content{
        display: flex;justify-content: space-between;
    }
}
</style>
<style lang="scss" scoped>
.deptsPage{
    .chooseParentDeptForm{
        ul{
            margin-bottom: 20px;padding-bottom: 20px;border-bottom: 1px solid #ccc;
            .parentDeptName{
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
