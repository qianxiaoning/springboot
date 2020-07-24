<template>
  <div class="enterpriseMain flex fd-c">
    <Head />
    <div class="bottom f-a">
      <div class="productInfo flex">
        <div class="left f-n">
          商品图片：
          <img
            :src="productDetail.product.images[0]"
            alt
          />
        </div>
        <div class="right f-a">
          <h5>商品名：{{productDetail.product.title}}</h5>
          <h5>商品卖点：{{productDetail.product.sellPoint}}</h5>
          <p>价格：{{productDetail.product.price}} 元</p>
          <p>商品编号：{{productDetail.product.id}}</p>
          购买数量：<input type="number" v-model="numImput"/>
          <br />
          <button @click="doAddCart()">加入购物车</button>
        </div>
      </div>      
      <p class="tip">下面是商品描述：（正式项目中得注意，避免用户自定义html的xss攻击）</p>
      <div class="productDescInfo" v-html="productDetail.productDesc.itemDesc">
      </div>
    </div>
  </div>
</template>
<script>
import Head from "@/components/Head";
import { Message } from 'element-ui';
export default {
  data() {
    return {
      indexQuery: {},
      productDetail: {
        product:{
          images:[]
        },
        productDesc:{}
      },
      numImput:null
    };
  },
  components: { Head },
  mounted() {
    this.indexQuery = this.$route.query;
    this.getProductById();
  },
  methods: {
    async getProductById() {
      const res = await this.$http.get(
        this.$urls.getProductById + `/${this.indexQuery.id}`
      );
      // console.log(res);
      this.productDetail = res.data;
    },
    async addCart(cart){
      const res = await this.$http.post(
        this.$urls.addCart + `/${cart.itemId}`,cart
      );
      // console.log(res);
    },
    async doAddCart(){
      const productInfo = this.productDetail.product;
      const cart = {
        itemId:productInfo.id,
        itemTitle:productInfo.title,
        itemImage:productInfo.image,
        itemPrice:productInfo.price,
        num:this.numImput
      };
      await this.addCart(cart);
      Message({
          message: "添加成功",
          type: "success"          
      });
    }
  }
};
</script>
<style lang="scss" scoped>
.enterpriseMain {
  height: 100%;
  .bottom {
    .productInfo {
      height: 332px;
      border: 1px solid #000;
      .left {
        width: 36%;
        img {
          max-width: 100%;
          max-height: 100%;
        }
      }
      .right {
        h5 {
          font-weight: normal;
          font-size: 18px;
        }
        * {
          margin-bottom: 20px;
        }
        button{cursor: pointer;}
      }
    }
    .tip{border:1px solid;}
    .productDescInfo {
      padding: 20px;
    }
  }
}
</style>
<style lang="scss">
.enterpriseMain {
  height: 100%;
  .bottom {    
    .productDescInfo {
      img{max-width: 100%;}
    }
  }
}
</style>
