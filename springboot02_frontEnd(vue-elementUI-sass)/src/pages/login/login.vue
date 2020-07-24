<template>
    <div class='login_wrap'>
        <div class="pop">
            <p>后台管理系统</p>
            <el-input v-model="username" placeholder="请输入账号" autocomplete="on"></el-input>
            <el-input type="password" v-model="password" placeholder="请输入密码" autocomplete="on"></el-input>
            <el-checkbox v-model="isRemember">记住账号密码</el-checkbox>
            <el-button @click='login()'>登录</el-button>
        </div>
    </div>
</template>
<script>
import jsmd5 from 'js-md5';
import { Message } from 'element-ui';
export default {
    data(){
        return{
            username:'admin',
            password:'1',
            isRemember: true
        }
    },
    methods:{
        async login(){                        
            const res = await this.$http.post(this.$urls.login,{
                username:this.username,
                password:this.password,
                isRemember:this.isRemember
            });
            // console.log(res);            
            if(res === undefined)return;
            this.$router.push({ name: 'main'});
        }
    }
}
</script>
<style lang="scss" scoped>
.login_wrap{
    height: 100%;position: relative;
    .pop{
        width: 400px;height: 300px;border: 1px solid #808080;transform: translate(-50%,-50%);position: absolute;left:50%;top:50%;text-align: center;padding: 0 20px;
        *{margin-top: 30px;}
    }
}
</style>
