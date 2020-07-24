<template>
  <div class="Head top f-n flex jc-sb ai-c">
    <div class="left" @click="toIndex()">c2c系统-前台站点</div>
    <div class="right">
      <span class="user" v-show="username">
        {{username}}，欢迎回来
        <span @click="doLogout()">[退出]</span>
      </span>
      <el-button type="primary" @click="login()" v-show="!username">登录</el-button>
      <el-button type="primary" @click="register()" v-show="!username">注册</el-button>
      <el-button type="primary" @click="toCart()">我的购物车</el-button>
    </div>
  </div>
</template>
<script>
import Cookies from 'js-cookie';
import {putJsonpScript} from '@/utils/utils';
import { Message } from 'element-ui';
export default {
  data() {
    return {
      username:''
    };
  },
  mounted(){
    window.getUserByCookieJsonpData = this.getUserByCookieJsonpData;     
    this.queryByCookie();
  },
  methods: {    
    toCart(){
      this.$router.push({ name: 'cart'});
    },
    doLogout(){
      this.logout();
      Message({
          message: "退出成功",
          type: "success",
          duration: 1000,
          onClose: () => {
              this.username = "";
          }
      });
    },
    getUserByCookieJsonpData(res){
      // console.log(res);
      const data = JSON.parse(res.data);
      this.username = data.username;
    },
    toIndex() {
      if(this.$route.name === "main")return;
        this.$router.push({ name: 'main'});
    },
    async logout() {
      const res = await this.$http.get(this.$urls.logout);
      // console.log(res);
    },
    login() {
        this.$router.push({ name: 'login'});
    },
    register() {
        this.$router.push({ name: 'register'});
    },    
    queryByCookie() {
      //判断ticket存在，就jsonp发起请求
      const ticket = Cookies.get('C2CSYSTEM_TICKET');
      const username = Cookies.get('C2CSYSTEM_USERNAME');
      if(!ticket || !username)return;

      putJsonpScript(`${this.$urls.queryByCookie}/${ticket}/${username}`,"getUserByCookieJsonpData");
    }
  }
};
</script>
<style lang="scss" scoped>
.Head {
  &.top {
    height: 50px;
    background: #c3d8ff;
    padding: 0 20px;
    .left {
      cursor: pointer;
    }
    .right {
      .user {
        margin-right: 20px;
        span{cursor: pointer;}
      }
    }
  }
}
</style>
