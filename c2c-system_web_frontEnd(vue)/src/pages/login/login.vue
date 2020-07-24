<template>
    <div class='login_wrap'>
        <div class="pop">
            <p>c2c系统-前台站点</p>
            <el-input v-model="username" placeholder="请输入账号" autocomplete="on">
                <span slot="prepend" class="inputHead">用户名：</span>
            </el-input>
            <el-input type="password" v-model="password" placeholder="请输入密码" autocomplete="on">
                <span slot="prepend" class="inputHead">密码：</span>
            </el-input>            
            <el-button type="primary" @click='login()'>登录</el-button>
        </div>
    </div>
</template>
<script>
import jsmd5 from 'js-md5';
import { Message } from 'element-ui';
export default {
    data(){
        return{
            username:'q',
            password:'1',
        }
    },    
    methods:{
        async login(){                        
            const res = await this.$http.post(this.$urls.doLogin,{
                username:this.username,
                password:this.password
            });
            // console.log(res);            
            // if(res === undefined)return;
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
        .inputHead{color:#555;font-size: 16px;}
    }
}
</style>
