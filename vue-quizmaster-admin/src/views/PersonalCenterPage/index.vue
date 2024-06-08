<template>
  <div class="personal-center-page">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="管理员ID" prop="id">
    <el-input v-model="ruleForm.id" :disabled="true"></el-input>
  </el-form-item>
  <el-form-item label="管理员名字" prop="name">
    <el-input v-model="ruleForm.name"></el-input>
  </el-form-item>
  <el-form-item label="状态" prop="state">
    <el-input placeholder="正常" :disabled="true" v-if="this.ruleForm.state==1"></el-input>
    <el-input placeholder="冻结" :disabled="true" v-else></el-input>
  </el-form-item>
  <el-form-item label="创建时间" prop="create">
    <el-input v-model="ruleForm.create" :disabled="true"></el-input>
  </el-form-item>
  <el-form-item label="更新时间" prop="update">
    <el-input v-model="ruleForm.update" :disabled="true"></el-input>
  </el-form-item>
  <el-form-item label="手机号码" prop="telephone">
    <el-input v-model="ruleForm.telephone"></el-input>
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
          state: '',
          create: '',
          update: '',
          telephone:'',
        },
        rules: {
          name: [
            { required: true, message: '请输入名字', trigger: 'blur' },
            { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
          ],
          telephone: [
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
        axios.get("/admin/info").then(
          (res)=>{
              if(res.data.code==1){
                this.ruleForm.id=res.data.data.adminId
                this.ruleForm.name=res.data.data.adminName
                this.ruleForm.state=res.data.data.adminState
                this.ruleForm.create=res.data.data.createTime
                this.ruleForm.update=res.data.data.updateTime
                this.ruleForm.telephone=res.data.data.telephone
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
      changeInfo(){
        axios.put("/admin/info?id="+this.ruleForm.id+"&name="+this.ruleForm.name+"&telephone="+this.ruleForm.telephone).then(
          (res)=>{
              if(res.data.code==1){
                this.$message.success("修改个人信息成功")
              }else{
                this.$message.error(res.data.msg)
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