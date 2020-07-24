<template>
    <div class='login_wrap'>
        <div class="pop">
            <p>c2c系统-前台站点</p>
            <el-input v-model="userInfo.username" placeholder="请输入账号" autocomplete="on" @change="validateUser(userInfo.username,1)">
                <span slot="prepend" class="inputHead">用户名：</span>
            </el-input>
            <el-input type="password" v-model="userInfo.password" placeholder="请输入密码" autocomplete="on">
                <span slot="prepend" class="inputHead">输入密码：</span>
            </el-input>
            <el-input type="password" v-model="userInfo.confirmPassword" placeholder="确认密码" autocomplete="on">
                <span slot="prepend" class="inputHead">确认密码：</span>
            </el-input>
            <el-input type="password" v-model="userInfo.phone" placeholder="请输入密码" autocomplete="on" @change="validateUser(userInfo.phone,2)">
                <span slot="prepend" class="inputHead">手机：</span>
            </el-input>
            <el-checkbox v-model="isRemember">同意xx协议</el-checkbox>
            <el-button type="primary" @click='doRegister()'>注册</el-button>            
        </div>
    </div>
</template>
<script>
import jsmd5 from 'js-md5';
import { Message } from 'element-ui';
import {putJsonpScript} from '@/utils/utils';
export default {
    data(){
        return{
            userInfo:{
                username:'',
                password:'',
                confirmPassword:'',
                phone: ''
            },
            isRemember:true,
            inputType:null
        }
    },    
    mounted(){
        window.getValidateResultJsonpData = this.getValidateResultJsonpData;        
    },
    methods:{
        getValidateResultJsonpData(res){
            const type = this.inputType;
            const inputType = ["用户名","手机","邮箱"];
            if(!res.data)return;
            Message({
                message: `${inputType[type-1]}已存在`,
                type: "error"
            });
        },
        //1 username、2 phone、3 email
        validateUser(value,type){
            this.inputType = type;
            //put jsonp
            putJsonpScript(`${this.$urls.validateUser}/${value}/${type}`,"getValidateResultJsonpData");
        },
        async doRegister(){                        
            const res = await this.$http.post(this.$urls.doRegister,this.userInfo);
            // console.log(res);     
            Message({
                message: "注册成功",
                type: "success",
                duration: 1000,
                onClose: () => {
                    if(res.code === 200){
                        this.$router.push({ name: 'main'});
                    }
                }
            });                    
        }
    }
}
</script>
<style lang="scss" scoped>
.login_wrap{
    height: 100%;position: relative;
    .pop{
        width: 400px;height: 360px;border: 1px solid #808080;transform: translate(-50%,-50%);position: absolute;left:50%;top:50%;text-align: center;padding: 0 20px;
        *{margin-top: 30px;}
    }
}
</style>
