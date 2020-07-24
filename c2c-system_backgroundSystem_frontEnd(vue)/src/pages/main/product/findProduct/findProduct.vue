<template>
  <div class="findProductPage">
    <el-dialog title="选择类目" :visible.sync="categoryPopVisible" class="chooseProductCategory">
      <ul>
        <li
          v-for="(item,index) in productCategoryList"
          :key="item.id"
          @click="productCategoryClick(item,index)"
        >
          <div>
            <span :class="productCategoryItemIndex===index?'choosed':''">{{item.text}}</span>
          </div>
        </li>
      </ul>
      <el-button @click="productCategoryPopCancel()">取消</el-button>
      <el-button type="primary" @click="categoryPopVisible=false;chooseCategory()">确定</el-button>
    </el-dialog>
    <el-dialog title="编辑商品" :visible.sync="editProductPopVisible" class="editProductPop">
      <ul>
        <li class="flex">
          <div class="left">商品类目：</div>
          <div class="right">
            <button @click="categoryPopVisible=true">选择类目</button>
            <span>{{productInfo.categoryName}}</span>
          </div>
        </li>
        <li class="flex">
          <div class="left">商品标题：</div>
          <div class="right">
            <input type="text" v-model="productInfo.title" />
          </div>
        </li>
        <li class="flex">
          <div class="left">商品卖点：</div>
          <div class="right">
            <input type="text" v-model="productInfo.sellPoint" />
          </div>
        </li>
        <li class="flex">
          <div class="left">商品价格：</div>
          <div class="right">
            <input type="number" v-model.number="productInfo.price" />
          </div>
        </li>
        <li class="flex">
          <div class="left">库存数量：</div>
          <div class="right">
            <input type="number" v-model.number="productInfo.num" />
          </div>
        </li>
        <li class="flex">
          <div class="left">条形码：</div>
          <div class="right">
            <input type="text" v-model="productInfo.barcode" />
          </div>
        </li>
        <li class="flex">
          <div class="left">商品图片：</div>
          <div class="right">
            <input type="file" @change="uploadPic()" id="uploadPic" multiple />
            <br />
            <img :src="item" alt v-for="(item) in productInfo.image.split(',')" :key="item" />
          </div>
        </li>
        <li class="flex">
          <div class="left f-n">
            现有商品描述
            <span style="color:red">（正式项目中，用富文本编辑器插件渲染用户自定义html，避免xss攻击）</span>
            ：
          </div>
          <div class="right productDescDiv f-a" v-html="productDescInfo.itemDesc"></div>
        </li>
        <li class="flex">
          <div class="left">商品描述修改：</div>
          <div class="right">
            <textarea name id cols="30" rows="10" v-model="productDescInfo.itemDesc"></textarea>
          </div>
        </li>
      </ul>
      <el-button @click="editProductPopVisible=false">取消</el-button>
      <el-button type="primary" @click="editProductPopVisible=false;submitEdit()">确定</el-button>
    </el-dialog>
    <h3>商品列表</h3>
    <button @click="editProduct()">编辑</button>
    <button @click="deleteProductAction()">删除</button>
    <button @click="reshelfProductAction()">上架</button>
    <button @click="instockProductAction()">下架</button>
    <div class="table">
      <table>
        <thead>
          <tr>
            <th>选择</th>
            <th>商品ID</th>
            <th>商品标题</th>
            <th>叶子类目</th>
            <th>卖点</th>
            <th>价格</th>
            <th>库存数量</th>
            <th>条形码</th>
            <th>状态</th>
            <th>创建日期</th>
            <th>更新日期</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item,index) in product.rows" :key="item.id">
            <td>
              <input
                type="checkbox"
                name="id"
                :value="item.id"
                @change="checkboxChoose($event,index)"
              />
            </td>
            <td>{{item.id}}</td>
            <td>{{item.title}}</td>
            <td>{{item.categoryName}}</td>
            <td>{{item.sellPoint}}</td>
            <td>{{item.price}}</td>
            <td>{{item.num}}</td>
            <td>{{item.barcode}}</td>
            <td>{{item.status === 1 ? '正常' : '下架'}}</td>
            <td>{{item.created}}</td>
            <td>{{item.updated}}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="foot">
      页数：
      <input type="text" placeholder="页数" v-model="pageInput" />
      条数：
      <input type="text" placeholder="条数" v-model="rowsInput" />
      <button @click="getProduct(pageInput,rowsInput)">搜索</button>
    </div>
  </div>
</template>
<script>
import jsmd5 from "js-md5";
import { Message } from "element-ui";
export default {
  data() {
    return {
      product: {},
      pageInput: 1,
      rowsInput: 3,
      editProductPopVisible: false,
      productInfo: {
        title: "",
        sellPoint: "",
        price: null,
        num: null,
        barcode: "",
        image: "",
        cid: null,
        id: null
      },
      productDescInfo: {
        itemDesc: ""
      },
      productCategoryList: [],
      categoryPopVisible: false,
      productCategoryItemIndex: null,
      choosedProductCategory: {},
      chooseItems: []
    };
  },
  mounted() {
    this.getProduct(this.pageInput, this.rowsInput);
    this.getProductCategoryList();
  },
  methods: {
    uploadPic() {
      let picFiles = new FormData();
      const uploadPicDom = document.querySelector("#uploadPic");
      const files = uploadPicDom.files;
      if (!files.length) return;
      for (let i = 0; i < files.length; i++) {
        picFiles.append("fileImages", files[i]);
      }
      // console.log(picFiles);
      // console.log(picFiles.getAll("fileImages"));
      this.$http.post(this.$urls.uploadImg, picFiles, 1);
      this.uploadImg(picFiles);
      Message({
        message: "上传成功",
        type: "success"
      });
    },
    async uploadImg(formData) {
      const fileStringArray = [];
      const res = await this.$http.post(this.$urls.uploadImg, formData, 1);
      // console.log(res);
      res.data.forEach((item)=>{
        this.productInfo.image += "," + item.url;
      });
      if(this.productInfo.image.indexOf(",") === 0){
        this.productInfo.image = this.productInfo.image.replace(",","");
      }
      // console.log(this.productInfo.image);
    },
    async reshelfProductAction() {
      const ids = this.chooseItems;
      // console.log(ids);
      const idsString = ids.join();
      await this.reshelfProduct(idsString);
      Message({
        message: "上架完成",
        type: "success"
      });
      this.getProduct(this.pageInput, this.rowsInput);
    },
    async instockProductAction() {
      const ids = this.chooseItems;
      // console.log(ids);
      const idsString = ids.join();
      await this.instockProduct(idsString);
      Message({
        message: "下架完成",
        type: "success"
      });
      this.getProduct(this.pageInput, this.rowsInput);
    },
    checkboxChoose(e, index) {
      const array = this.chooseItems;
      const isChoosed = e.target.checked;
      const item = this.product.rows[index];
      // console.log(e);
      // console.log(item);
      if (isChoosed) {
        array.push(item.id);
        this.chooseItems = [...new Set(array)];
        this.productInfo = JSON.parse(JSON.stringify(item));
        this.getProductDescById(item.id);
      } else {
      }
    },
    async getProductCategoryList(id) {
      const res = await this.$http.get(this.$urls.getProductCategoryList, {
        id: id
      });
      this.productCategoryList = res.data;
    },
    editProduct() {
      this.editProductPopVisible = true;
    },
    async deleteProductAction() {
      if (!confirm("确认删除吗?")) return;
      const ids = this.chooseItems;
      // console.log(ids);
      const idsString = ids.join();
      await this.deleteProduct(idsString);
      Message({
        message: "删除完成",
        type: "success"
      });
      this.getProduct(this.pageInput, this.rowsInput);
    },
    productCategoryClick(item, index) {
      if (item.state === "closed") {
        this.getProductCategoryList(item.id);
      } else {
        this.productCategoryItemIndex = index;
        this.choosedProductCategory = item;
      }
    },
    productCategoryPopCancel() {
      this.categoryPopVisible = false;
      this.productCategoryItemIndex = null;
      this.getProductCategoryList();
    },
    chooseCategory() {
      const item = this.choosedProductCategory;
      //   console.log(this.choosedProductCategory);
      this.productInfo.cid = item.id;
      this.productInfo.categoryName = item.text;
    },
    async submitEdit() {
      await this.putProduct(this.productInfo, this.productDescInfo);
      Message({
        message: "修改完成",
        type: "success"
      });
      this.getProduct(this.pageInput, this.rowsInput);
    },
    async putProduct(productInfo, productDescInfo) {
      // console.log(this.productInfo);
      const res = await this.$http.put(this.$urls.putProduct, {
        product: productInfo,
        productDesc: productDescInfo
      });
      // this.product = res.data;
    },
    async deleteProduct(ids) {
      const res = await this.$http.delete(this.$urls.deleteProduct, {
        ids: ids
      });
    },
    async getProductDescById(id) {
      const res = await this.$http.get(this.$urls.getProductDescById + id);
      // console.log(res);
      if (!res.data) return;
      this.productDescInfo = res.data;
    },
    async reshelfProduct(ids) {
      const res = await this.$http.put(this.$urls.reshelfProduct, ids);
    },
    async instockProduct(ids) {
      const res = await this.$http.put(this.$urls.instockProduct, ids);
    },
    async getProduct(page, rows) {
      const res = await this.$http.get(this.$urls.getProduct, {
        page: page,
        rows: rows
      });
      // console.log(res);
      this.product = res.data;
    }
  }
};
</script>
<style lang="scss" scoped>
.findProductPage {
  .chooseProductCategory {
    li {
      cursor: pointer;
      span {
        &.choosed {
          background: #ccc;
        }
      }
    }
  }
  .editProductPop {
    li {
      .left {
        width: 80px;
      }
      .right {
        img {
          width: 80px;
          height: 80px;
          margin-right: 6px;
        }
        &.productDescDiv {
          overflow: auto;
          height: 200px;
        }
      }
    }
  }
  .table {
    height: 500px;
    overflow: auto;
    table {
      border-collapse: collapse;
      width: 100%;
      tr {
        border-bottom: 1px solid #ccc;
        th,
        td {
          border-right: 1px solid #ccc;
        }
        td:nth-child(1) {
          text-align: center;
        }
      }
    }
  }
}
</style>
