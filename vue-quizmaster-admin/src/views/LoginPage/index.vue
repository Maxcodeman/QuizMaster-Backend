<template>
  <div class="loginBody">
    <div class="loginDiv">
      <div class="login-content">
        <h1 class="login-title">面试达人后台管理系统</h1>
        <el-form
          :model="loginForm"
          label-width="100px"
          :rules="rules"
          ref="loginForm"
        >
          <el-form-item label="管理员ID" prop="adminId">
            <el-input
              style="width: 200px"
              type="text"
              v-model="loginForm.adminId"
              autocomplete="off"
              size="small"
            ></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
              style="width: 200px"
              type="code"
              v-model="loginForm.password"
              show-password
              autocomplete="off"
              size="small"
            ></el-input>
          </el-form-item>
          <el-form-item prop="remember">
            保持登录  <el-checkbox v-model="this.remember"></el-checkbox>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="login">登 录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  data() {
    return {
      remember:true,
      loginForm: {
        adminId: "",
        password: "",
      },
      rules: {
        adminId: [{ required: true, message: "请输入您的ID", trigger: "blur" }],
        password: [
          { required: true, message: "请输入您的密码", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    login() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          //valid成功为true，失败为false
          //去后台验证用户名密码，并返回token
          /* 先判断用户类型,再调用对应接口 */
            this.$axios
            .post("/login?remember="+this.remember, this.loginForm)
            .then((res) => {
              console.log(res.data);
              if (res.data.code == 1) {
                localStorage.setItem('token',res.data.data)
                this.$message({
                message: '登录成功',
                type: 'success'
                });
                this.$router.replace("/homePage");
              } else {
                this.$message.error(res.data.msg);
                return false;
              }
            });
        }
      });
    },
  },
};
</script>

<style scoped >
.loginBody {
  width: 100%;
  height: 100%;
  position: fixed;
  background: url("../../assets/interview.jpg");
  background-size: 100% ;
}
.loginDiv {
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -200px;
  margin-left: -250px;
  width: 450px;
  height: 350px;
  background: #fff;
  border-radius: 5%;
}
.login-title {
  margin: 20px 0;
  text-align: center;
}
.login-content {
  width: 400px;
  height: 250px;
  position: absolute;
  top: 25px;
  left: 25px;
}
</style>
