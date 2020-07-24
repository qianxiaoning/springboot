<template>
  <div class="c2cSystemWebOrder flex fd-c">
    <Head />
    <div class="bottom f-a">
      <h3 class="title">订单提交成功</h3>
      <div class="info">
        <p>订单号：{{orderInfo.orderId}}</p>
        <p>{{orderInfo.paymentType===1?"在线支付":""}}：{{orderInfo.payment}}元</p>
        <p>{{orderInfo.shippingName}}</p>
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
      routeQuery:{},
      orderInfo: {},   
    };
  },
  components: { Head },
  async mounted() {
    this.routeQuery = this.$route.query;
    this.queryOrder(this.routeQuery.orderId);
  },
  methods: {    
    async queryOrder(orderId) {
      const res = await this.$http.get(
        this.$urls.queryOrder,{id:orderId});
      // console.log(res);
      this.orderInfo = res.data;
    },      
  }
};
</script>
<style lang="scss" scoped>
.c2cSystemWebOrder {
  height: 100%;
  .bottom {
    .title {
      border-bottom: 1px solid;padding: 0 10px;color:#82bb86;
    }
    .info{
      padding: 0 10px;
      border-bottom: 1px solid;
      p{margin: 10px 0;}
    }
  }
}
</style>

