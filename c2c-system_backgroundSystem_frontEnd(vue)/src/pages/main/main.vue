<template>
  <div class="enterpriseMain flex fd-c">
    <div class="top f-n flex jc-sb ai-c">
      <div class="left">c2c系统-后台站点</div>
      <!-- <div class="right">
        <span class="user">{{userInfo.username}}，欢迎回来</span>
        <el-button type="primary" @click="doLogout()">退出登录</el-button>
      </div> -->
    </div>
    <div class="bottom f-a flex">
      <div class="leftSide f-n">
        <div class="firstLevel" v-for="(item) in leftSideBarData" :key="item.name">
          {{item.name}}
          <ul>
            <router-link
              active-class="active"
              v-for="(secondItem) in item.second"
              :key="secondItem.name"
              :to="{name:secondItem.url}"
              tag="li"
            >{{secondItem.name}}</router-link>
          </ul>
        </div>
      </div>
      <div class="right f-a">
        <router-view class="view" />
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      leftSideBarData: [
        {
          name: "商品管理",
          url: "product",
          second: [
            { name: "新增商品", url: "addProduct" },
            { name: "查询商品", url: "findProduct" },
            // { name: "规格参数", url: "parameter" }            
          ]
        },
        // {
        //   name: "网站内容管理",
        //   url: "detail",
        //   second: [
        //     { name: "内容分类管理", url: "contentCategory" },
        //     { name: "内容管理", url: "contentManage" }            
        //   ]
        // }
      ],
      userInfo:{}
    };
  },
  mounted(){
    // this.getLoginUser();
  },
  methods: {
    doLogout() {
      this.logout();
    },
    async logout() {
      const res = await this.$http.post(this.$urls.logout);
    },
    async getLoginUser() {
      const res = await this.$http.get(this.$urls.getLoginUser);
      this.userInfo = res.data;
    }
  },
  components: {}
};
</script>
<style lang="scss" scoped>
.enterpriseMain {
  height: 100%;
  .top {
    height: 50px;
    background: #c3d8ff;
    padding: 0 20px;
    .right{
      .user{
        margin-right: 20px;
      }
    }
  }
  .bottom {
    .leftSide {
      width: 200px;
      background: #e9e9f7;
      .firstLevel {
        background: #cecece;
        li {
          background: #a5a5a5;
          margin-left: 10px;
          cursor: pointer;
          line-height: 40px;
          border-bottom: 1px solid #000;
          &.active {
            color: #6a7690;
          }
        }
      }
    }
    .right {
      .view {
        height: 100%;
      }
    }
  }
}
</style>
