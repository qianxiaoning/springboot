<template>
    <div class='login_wrap'>
        <div class="pop">
            <p>后台管理系统</p>
            <el-input v-model="name" placeholder="请输入账号"></el-input>
            <el-input type="password" v-model="password" placeholder="请输入密码"></el-input>
            <el-button @click='login'>登录</el-button>
        </div>
    </div>
</template>
<script>
import jsmd5 from 'js-md5'
export default {
    data(){
        return{
            name:'',
            password:''
        }
    },
    methods:{
        async login(){                        
            // const res = await this.$http.post(this.$urls.login,{});
            // console.log(res);
            this.$router.push({
                path: '/main'
            });
        }
    }
}
</script>
<style lang="scss" scoped>
.login_wrap{
    height: 100%;position: relative;
    .pop{
        width: 400px;height: 300px;border: 1px solid #ccc;transform: translate(-50%,-50%);position: absolute;left:50%;top:50%;text-align: center;padding: 0 20px;
        *{margin-top: 30px;}
    }
}
</style>
