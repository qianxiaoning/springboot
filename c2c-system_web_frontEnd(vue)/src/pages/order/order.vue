<template>
  <div class="c2cSystemWebOrder flex fd-c">
    <Head />
    <div class="bottom f-a">
      <h3 class="title">确认订单信息</h3>
      <div class="info">
        <h3>收货人信息</h3>
        <p>{{order.orderShipping.receiverName}} {{order.orderShipping.receiverMobile}}</p>
        <p>{{order.orderShipping.receiverCity==="1"?"哈尔滨":""}} {{order.orderShipping.receiverDistrict==="1"?"莱莱区":""}} {{order.orderShipping.receiverAddress}}</p>
      </div>
      <div class="info">
        <h3>支付和配送方式</h3>
        <p>{{order.paymentType===1?"在线支付":""}}</p>
        <p>{{order.shippingName}}</p>
      </div>
      <div class="cartList">
        <table>
          <thead>
            <tr>
              <th>商品</th>
              <th>价格</th>
              <th>数量</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item) in cartList" :key="item.id">              
              <td>
                <img
                  :src="item.itemImage"
                  alt
                />
                {{item.itemTitle}}
              </td>
              <td>{{item.itemPrice}}</td>
              <td>{{item.num}}</td>              
            </tr>
          </tbody>
        </table>
      </div>
      <div class="totalPrice flex jc-sa">
        <span>应付金额 {{total}}元</span>
        <button @click="doSubmitOrder()">提交订单</button>
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
      cartList: [],   
      total:null,
      order:{
        //实付金额
        payment:'20',
        //实付类型
        paymentType:1,
        //邮费
        postFee:"11",        
        //物流名称
        shippingName:"哈哈物流",
        //物流单号
        shippingCode:"2121121",
        //买家留言
        buyerMessage:"尽快发货",
        //买家昵称
        buyerNick:"大佬",
        //是否评价
        buyerRate:1,
        //订单物流
        orderShipping:{
          //收货人姓名
          receiverName:"李四",
          //收货人固定电话
          receiverPhone:"62332129",
          //收货人移动电话
          receiverMobile:"1232424343",
          //收货人省份
          receiverState:"1",
          //城市
          receiverCity:"1",
          //区县
          receiverDistrict:"1",
          //收货地址
          receiverAddress:"和平路19号",
          //邮政编码
          receiverZip:"12112"
        },
        //订单商品
        orderItems:[
          {
            //商品id
            itemId:"1212",
            //购买数量
            num:2,
            //商品标题
            title:"好电脑",
            //商品单价
            price:12,
            //商品总金额
            totalFee:33,
            //商品图片地址
            picPath:"qqwqwq.jpg"
          }
        ]
      }   
    };
  },
  components: { Head },
  async mounted() {
    await this.queryCartByUserId();
    this.calculateTotalPrice();
  },
  methods: {
    getOrderParam(){      
      const orderItems = [];      
      const cartList = this.cartList;
      cartList.forEach((item)=>{
        const orderItem = {};
        orderItem.itemId = item.itemId;
        orderItem.num = item.num;
        orderItem.title = item.itemTitle;
        orderItem.price = item.itemPrice;
        orderItem.totalFee = item.itemPrice*item.num;
        orderItem.picPath = item.itemImage;
        orderItems.push(orderItem);
      });
      this.order.orderItems = orderItems;
    },
    doSubmitOrder(){
      this.getOrderParam();
      // console.log(this.order);
      this.submitOrder(this.order);
    },
    async submitOrder(order) {
      const res = await this.$http.post(
        this.$urls.submitOrder,order);
      console.log(res);
      // if(){}
      this.$router.push({ name: 'orderSuccess',query: { orderId:res.data}});
    },
    async queryCartByUserId() {
      const res = await this.$http.get(
        this.$urls.queryCartByUserId2);
      // console.log(res);
      this.cartList = res.data;
    },
    calculateTotalPrice(){
      let price = null;
      this.cartList.forEach(item => {
        price += item.itemPrice*item.num;
      });
      this.total = price;
    },        
  }
};
</script>
<style lang="scss" scoped>
.c2cSystemWebOrder {
  height: 100%;
  .bottom {
    .title {
      border-bottom: 1px solid;
    }
    .info{
      padding: 0 10px;
      border-bottom: 1px solid;
    }
    .cartList {
      height: 400px;
      overflow: auto;
      table {
        border-collapse: collapse;
        width: 100%;
        tr {
          border-bottom: 1px solid #ccc;
          th,
          td {
            border-right: 1px solid #ccc;
            text-align: center;
            max-width: 100px;
            overflow: auto;
            img {
              height: 80px;
            }            
          }
        }
      }
    }
    .totalPrice {
      margin-top: 20px;
      background: #f3f3f3;
      padding: 10px;
      button {
        cursor: pointer;
      }
    }
  }
}
</style>

