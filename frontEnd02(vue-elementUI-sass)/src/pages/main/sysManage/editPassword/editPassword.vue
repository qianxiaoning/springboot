<template>
  <div class="login_wrap">
    <div class="pop">
      <p>修改密码</p>
      <el-form ref="form" :model="editPasswordForm" label-width="80px">
        <el-form-item label="原密码">
          <el-input
            type="password"
            placeholder="原密码"
            autocomplete="on"
            v-model="editPasswordForm.oldPassword"
          ></el-input>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input
            type="password"
            placeholder="新密码"
            autocomplete="on"
            v-model="editPasswordForm.newPassword"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码确认">
          <el-input
            type="password"
            placeholder="密码确认"
            autocomplete="on"
            v-model="editPasswordForm.newConfirm"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button>取消</el-button>
          <el-button type="primary" @click="editPassword()">确定</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
import jsmd5 from "js-md5";
import { Message } from "element-ui";
export default {
  data() {
    return {
      editPasswordForm: {
        oldPassword: "",
        newPassword: "",
        newConfirm: ""
      }
    };
  },
  methods: {
    async editPassword() {
      const newPassword = this.editPasswordForm.newPassword;
      const newConfirm = this.editPasswordForm.newConfirm;
      if (newPassword !== newConfirm) {
        Message({
          message: "密码输入不一致",
          type: "warning"
        });
        return;
      }
      const res = await this.$http.put(
        this.$urls.editPassword,
        this.editPasswordForm
      );
      Message({
        message: res.message + "，请重新登录",
        type: "success",
        duration: 1000,
        onClose: async() => {
            await this.$http.post(this.$urls.logout);
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
.login_wrap {
  height: 100%;
  position: relative;
  .pop {
    width: 400px;
    height: 300px;
    border: 1px solid #808080;
    transform: translate(-50%, -50%);
    position: absolute;
    left: 50%;
    top: 50%;
    padding: 0 20px;
    p,
    form {
      margin-top: 30px;
    }
  }
}
</style>
