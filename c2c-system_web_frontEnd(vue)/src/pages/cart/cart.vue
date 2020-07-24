<template>
  <div class="c2cSystemWebCart flex fd-c">
    <Head />
    <div class="bottom f-a">
      <h3 class="title">我的购物车</h3>
      <div class="cartList">
        <table>
          <thead>
            <tr>
              <th>选择</th>
              <th>商品</th>
              <th>价格</th>
              <th>数量</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item,index) in cartList" :key="item.id">
              <td>
                <input type="checkbox" name id/>
              </td>
              <td>
                <img
                  :src="item.itemImage"
                  alt
                />
                {{item.itemTitle}}
              </td>
              <td>{{item.itemPrice}}</td>
              <td>
                <input type="number" name id v-model="item.num" @change="doUpdateNum(item)"/>
              </td>
              <td>
                <span @click="doDeleteCart(item.itemId,index)">删除</span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="totalPrice flex jc-sa">
        <span>合计 {{total}}元</span>
        <button @click="toOrderPage()">去结算</button>
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
      total:null   
    };
  },
  components: { Head },
  async mounted() {
    await this.queryCartByUserId();
    this.calculateTotalPrice();
  },
  methods: {
    toOrderPage(){
      this.$router.push({ name: 'order'});
    },
    async queryCartByUserId() {
      const res = await this.$http.get(
        this.$urls.queryCartByUserId);
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
    async doUpdateNum(item){
      await this.updateNum(item);
      this.calculateTotalPrice();
    },
    async updateNum(item){
      const res = await this.$http.put(
        this.$urls.updateCartNum + `/${item.itemId}/${item.num}`);
      // console.log(res);
    },
    async doDeleteCart(id,index){
      await this.deleteCart(id);
      Message({
          message: "删除成功",
          type: "success"          
      });
      this.cartList.splice(index,1);
    },
    async deleteCart(id){
      const res = await this.$http.delete(
        this.$urls.deleteCart + `/${id}`);
      // console.log(res);
    },
  }
};
</script>
<style lang="scss" scoped>
.c2cSystemWebCart {
  height: 100%;
  .bottom {
    .title {
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
            span {
              cursor: pointer;
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

