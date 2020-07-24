<template>
  <div class="enterpriseMain flex fd-c">
    <Head />
    <div class="bottom f-a flex">
      <div class="leftSide f-n">
        <ul>
          <router-link
            active-class="active"
            v-for="(item) in leftSideBarData"
            :key="item.url"
            :to="{name:item.url}"
            tag="li"
          >{{item.name}}</router-link>
        </ul>
      </div>
      <div class="right f-a">
        <div class="productRecommend">
          <h3>商品推荐</h3>
          <ul class="flex fw-w">
            <li v-for="(item) in productList" :key="item.id" @click="toProductDetail(item.id)">
              <h6>{{item.title}}</h6>
              <h6>{{item.sellPoint}}</h6>
              <img :src="item.images[0]" alt />
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Head from "@/components/Head";
export default {
  data() {
    return {
      productList: [],
      leftSideBarData: [
        {
          name: "jsonp测试",
          url: "testJsonp"
        },
        {
          name: "网站内容管理",
          url: "detail"
        }
      ],
      userInfo: {}
    };
  },
  components: {
    Head
  },
  mounted() {
    this.getProducts();
  },
  methods: {
    async getProducts() {
      const res = await this.$http.get(this.$urls.getProducts);
      console.log(res);
      this.productList = res.data;
    },
    toProductDetail(id) {
      this.$router.push({ name: "productDetail", query: { id: id } });
    }
  }
};
</script>
<style lang="scss" scoped>
.enterpriseMain {
  height: 100%;
  .bottom {
    .leftSide {
      width: 200px;
      background: #e9e9f7;
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
    .right {
      .productRecommend {
        border: 1px solid;
        height: 500px;
        overflow: auto;
        li {
          width: 33.33%;
          height: 187px;
          border: 1px solid #ccc;
          border-radius: 4px;
          cursor: pointer;
          overflow: hidden;
          h6 {
            font-weight: normal;
            font-size: 16px;
            text-align: center;
          }
          img {
            width: 100%;
          }
        }
      }
    }
  }
}
</style>
