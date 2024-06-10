<template>
  <div class="personal-center-page">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="管理员ID" prop="id">
    <el-input v-model="ruleForm.id" :disabled="true"></el-input>
  </el-form-item>
  <el-form-item label="管理员名称" prop="name">
    <el-input v-model="ruleForm.name"></el-input>
  </el-form-item>
  <el-form-item label="手机号码" prop="mobile">
    <el-input v-model="ruleForm.mobile"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">修改</el-button>
  </el-form-item>
</el-form>
  </div>
</template>

<script>
import axios from '@/api/axios';
export default {
   data() {
      return {
        ruleForm: {
          id: '',
          name: '',
          mobile:'',
        },
        rules: {
          name: [
            { required: true, message: '请输入名字', trigger: 'blur' },
            { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
          ],
          mobile: [
            { required: true, message: '请填写手机号码', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      getInfo(){
        axios.get("/admins/admin").then(
          (res)=>{
              if(res.data.code==1){
                this.ruleForm.id=res.data.data.adminId
                this.ruleForm.name=res.data.data.adminName
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
      updateInfo(){
        axios.put("/admin/admin?id="+this.ruleForm.id+"&name="+this.ruleForm.name+"&telephone="+this.ruleForm.telephone).then(
          (res)=>{
              if(res.data.code==1){
                this.$message.success("修改信息成功")
              }else{
                this.$message.error("修改信息失败")
              }
          }
        ).catch(
          (err)=>{
            console.log(err)
          }
        )
      }
    },
    mounted() {
        this.getInfo()
      },
};
</script>

<style>
</style>