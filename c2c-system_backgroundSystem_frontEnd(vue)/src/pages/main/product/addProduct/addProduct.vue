<template>
  <div class="addProduct">
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
    <ul>
      <li class="flex">
        <div class="left">商品类目：</div>
        <div class="right">
          <button @click="categoryPopVisible=true">选择类目</button>
          <span>{{choosedProductCategory.text}}</span>
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
          <img
            :src="item"
            alt
            v-for="(item) in productInfo.image.split(',')"
            :key="item"
          />
        </div>
      </li>
      <li class="flex">
        <div class="left">商品描述：</div>
        <div class="right">
          <textarea name id cols="30" rows="10" v-model="productDescInfo.itemDesc"></textarea>
        </div>
      </li>
    </ul>
    <button @click="submitProduct()">提交</button>
  </div>
</template>
<script>
import { Message } from "element-ui";
export default {
  data() {
    return {
      categoryPopVisible: false,
      productCategoryList: [],
      productInfo: {
        title: "",
        sellPoint: "",
        price: null,
        num: null,
        barcode: "",
        image: "",
        cid: null
      },
      productDescInfo: {
        itemDesc: "<div><p style='color:#ccc'>121212</p></div>"
      },
      productCategoryItemIndex: null,
      choosedProductCategory: {},
    };
  },
  mounted() {
    this.getProductCategoryList();
  },
  computed: {},
  components: {},
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
    productCategoryPopCancel() {
      this.categoryPopVisible = false;
      this.productCategoryItemIndex = null;
      this.choosedProductCategory = {};
      this.getProductCategoryList();
    },
    productCategoryClick(item, index) {
      if (item.state === "closed") {
        this.getProductCategoryList(item.id);
      } else {
        this.productCategoryItemIndex = index;
        this.choosedProductCategory = item;
      }
    },
    async getProductCategoryList(id) {
      const res = await this.$http.get(this.$urls.getProductCategoryList, {
        id: id
      });
      this.productCategoryList = res.data;
    },
    submitProduct() {
      this.postProduct(this.productInfo, this.productDescInfo);
      Message({
        message: "新增成功",
        type: "success"
      });
    },
    async postProduct(productInfo, productDescInfo) {
      // console.log(productDescInfo);
      const res = await this.$http.post(this.$urls.postProduct, {
        product: productInfo,
        productDesc: productDescInfo
      });
    },
    chooseCategory() {
      const item = this.choosedProductCategory;
      //   console.log(this.choosedProductCategory);
      this.productInfo.cid = item.id;
    }
  }
};
</script>
<style lang="scss" scoped>
.addProduct {
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
  ul {
    li {
      border-bottom: 1px solid #ccc;
      .right {
        img {
          width: 80px;
          height: 80px;
          margin-right: 6px;
        }
      }
    }
  }
}
</style>
