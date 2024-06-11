<template>
  <div class="personal-center-page">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="管理员ID" prop="id">
    <el-input v-model="ruleForm.adminId" :disabled="true"></el-input>
  </el-form-item>
  <el-form-item label="管理员名称" prop="name">
    <el-input v-model="ruleForm.adminName"></el-input>
  </el-form-item>
  <el-form-item label="手机号码" prop="mobile">
    <el-input v-model="ruleForm.mobile"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">保存</el-button>
  </el-form-item>
</el-form>
  </div>
</template>

<script>
import axios from '@/api/axios';
import {jwtDecode} from "jwt-decode";
export default {
   data() {
      return {
        ruleForm: {
          adminId: '',
          adminName: '',
          mobile:'',
        },
        rules: {
          adminName: [
            { required: true, message: '请输入名字', trigger: 'blur' },
            { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
          ],
          mobile: [
            { required: true, message: '请填写手机号码', trigger: 'blur' },
            {
              pattern: /^1[3456789]\d{9}$/,
              message: "手机号码格式不正确",
              trigger: ["blur", "change"],
            },
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.updateInfo()
          } else {
            this.$message.error("信息格式有误")
          }
        });
      },
      //获取个人信息
      getInfo(){
        axios.get("/admin/admins/"+this.ruleForm.adminId).then(
          (res)=>{
              if(res.data.code==1){
                this.ruleForm.adminName=res.data.data.adminName
                this.ruleForm.mobile=res.data.data.mobile
              }else{
                this.$message.error(res.data.msg)
              }
          }
        ).catch(
          (err)=>{
            console.log(err)
          }
        )
      },
      //保存个人信息
      updateInfo(){
        axios.put("/admin/admins",this.ruleForm).then(
          (res)=>{
              if(res.data.code==1){
                this.$message.success("保存成功")
              }else{
                this.$message.error("保存失败")
              }
          }
        ).catch(
          (err)=>{
            console.log(err)
          }
        )
      },

      //获取token
      getToken() {
        return localStorage.getItem('token');
      },
      //解析token
      parseToken(){
        const token=this.getToken();
        if(token){
          try{
            const decodedToken=jwtDecode(token)
            this.ruleForm.adminId=decodedToken.id
          }catch (err){
            console.error('解析Token失败',err)
          }
        }else{
          console.warn('在本地存储中没有token')
        }
      }

    },
    mounted() {
      this.parseToken()
      this.getInfo()
      },
};
</script>

<style>
</style>