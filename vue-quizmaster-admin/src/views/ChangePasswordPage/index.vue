<template>
  <div class="change-password-page">
    <div class="change-form">
        <h1 style="font-size: 28px;">修改密码</h1>
    <el-form
      :model="ruleForm"
      status-icon
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="管理员ID" prop="id">
        <el-input
          v-model="id"
          autocomplete="off"
          :disabled="true"
          style="width: 500px"
        ></el-input>
      </el-form-item>
      <el-form-item label="旧密码" prop="oldPassword">
        <el-input
          placeholder="请输入旧密码"
          type="password"
          v-model="ruleForm.oldPassword"
          autocomplete="off"
          style="width: 500px"
        ></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="password">
        <el-input
          placeholder="请输入新密码"
          type="password"
          v-model="ruleForm.password"
          autocomplete="off"
          style="width: 500px"
        ></el-input>
      </el-form-item>
      <el-form-item label="确认新密码" prop="checkPassword">
        <el-input
          placeholder="请再次输入新密码"
          type="password"
          v-model="ruleForm.checkPassword"
          autocomplete="off"
          style="width: 500px"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="changePassword"
          >保存</el-button
        >
      </el-form-item>
    </el-form>
</div>
  </div>
</template>

<script>
import axios from '@/api/axios';
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm.checkPassword !== "") {
          this.$refs.ruleForm.validateField("checkPassword");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      id: "",
      ruleForm: {
        oldPassword:"",
        password: "",
        checkPassword: "",
      },
      rules: {
        password: [{ validator: validatePass, trigger: "blur" }],
        checkPassword: [{ validator: validatePass2, trigger: "blur" }],
      },
    };
  },
  methods:{
    getId(){
        axios.get("/admin/id").then(
            (res)=>{
                if(res.data.code==1){
                    this.id=res.data.data
                }else{
                  this.$message.error(res.data.msg);
                }
            }
        ).catch((err)=>{
            console.log(err)
        }
        )
    },
    changePassword(){
      axios.put("/admin/password?id="+this.id+"&oldPassword="+this.ruleForm.oldPassword+"&password="+this.ruleForm.password).then(
            (res)=>{
                if(res.data.code==1){
                    this.$message.success("密码修改成功,请重新登录")
                    localStorage.removeItem('token')
                    this.$router.push("/loginPage");  
                }else{
                  this.$message.error(res.data.msg);
                }
            }
        ).catch((err)=>{
            console.log(err)
        }
        )
    }
  },
  mounted(){
    this.getId()
  }
};
</script>

<style>
.change-form{
  width: 800px;
  height: 500px;
  margin:auto
}
</style>