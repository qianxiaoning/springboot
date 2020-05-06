<template>
    <div class="logsPage">
        <div class="head flex jc-sb">
            <span>日志管理</span>
            <div class="right">
                <input type="text" v-model="username" placeholder="用户名">
                <span @click="getLogs(username,1)">搜索</span>
                <span @click="deleteLogsAction()">删除</span>
            </div>
        </div>
        <div class="table">
            <table>
                <thead>
                    <tr>
                        <th>选择</th>
                        <th>用户名</th>
                        <th>操作</th>
                        <th>请求方法</th>
                        <th>请求参数</th>
                        <th>ip</th>
                        <th>执行时长</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(item) in logsData.records" :key="item.id">
                        <td>
                            <input type="checkbox" :id="item.id" v-model="idsCheckbox" :value="item.id">
                        </td>
                        <td>{{item.username}}</td>
                        <td>{{item.operation}}</td>
                        <td>{{item.method}}</td>
                        <td>{{item.params}}</td>
                        <td>{{item.ip}}</td>
                        <td>{{item.operationTime}}</td>
                    </tr>
                </tbody>
            </table>    
        </div>
        <div class="btns">
            <span @click="getLogs(username,1)">首页</span>
            <span @click="lastPage()">上一页</span>
            <span @click="nextPage()">下一页</span>
            <span @click="getLogs(username,logsData.pageCount)">尾页</span>
            <span>总记录数（{{logsData.rowCount}}）</span>
            <span>总页数（{{logsData.pageCount}}）</span>
            <span>当前页（{{logsData.pageCurrent}}）</span>
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
            logsData:{},
            idsCheckbox:[]
        }
    },
    mounted(){
        this.getLogs(this.username,this.pageCurrent);
    },
    components:{
    },
    methods:{
        async getLogs(username,pageCurrent){
            const res = await this.$http.get(this.$urls.getLogs,{username:username,pageCurrent:pageCurrent});
            console.log(res);
            this.logsData = res.data;
            this.pageCurrent = this.logsData.pageCurrent;
        },
        lastPage(){
            if(this.pageCurrent > 1){
                this.pageCurrent --;
                this.getLogs(this.username,this.pageCurrent);    
            }
        },
        nextPage(){
            if(this.pageCurrent < this.logsData.pageCount){
                this.pageCurrent ++;
                this.getLogs(this.username,this.pageCurrent);    
            }
        },
        async deleteLogs(ids){  
            // console.log(ids.toString());
            if(!confirm("确认删除吗?"))return;          
            const res = await this.$http.delete(this.$urls.deleteLogs,{ids:ids.toString()});
            console.log(res);
            Message({
                message:res.message,
                type:'success',
            });
            this.getLogs(this.username,this.pageCurrent);
        },
        deleteLogsAction(){
            this.deleteLogs(this.idsCheckbox);
        }
    }
}
</script>
<style lang="scss" scoped>
.logsPage{
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
            }        
        }
    }
    .btns{
        text-align: right;
        span{border:1px solid #ccc;padding: 4px;cursor: pointer;border-radius:4px;}
    }
}
</style>
