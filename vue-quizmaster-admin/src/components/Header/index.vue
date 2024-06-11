<template>
  <div class="header">
    <!-- 面包屑根据当前路由的名称选择性地展示 -->
    <div class="breadcrumb">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item to="/">首页</el-breadcrumb-item>
        <el-breadcrumb-item to="/questionPage" v-if="$route.name == 'question'"
          >题目管理</el-breadcrumb-item
        >
        <el-breadcrumb-item
          to="/categoryPage"
          v-else-if="$route.name == 'category'"
          >分类管理</el-breadcrumb-item
        >
        <el-breadcrumb-item to="/typePage" v-else-if="$route.name == 'type'"
          >题型管理</el-breadcrumb-item
        >
        <el-breadcrumb-item to="/userPage" v-else-if="$route.name == 'user'"
          >用户管理</el-breadcrumb-item
        >
        <el-breadcrumb-item to="/personalCenterPage" v-else-if="$route.name == 'personal-center'"
          >个人信息</el-breadcrumb-item
        >
        <el-breadcrumb-item to="/changePasswordPage" v-else-if="$route.name == 'change-password'"
          >修改密码</el-breadcrumb-item
        >
        <el-breadcrumb-item to="/logPage" v-else-if="$route.name == 'log'"
          >日志查看</el-breadcrumb-item
        >
        <el-breadcrumb-item to="/notePage" v-else-if="$route.name == 'note'"
          >笔记管理</el-breadcrumb-item
        >
        <el-breadcrumb-item to="/commentPage" v-else-if="$route.name == 'comment'"
          >评论管理</el-breadcrumb-item
        >
        <el-breadcrumb-item to="/adminPage" v-else-if="$route.name == 'admin'"
        >管理员管理</el-breadcrumb-item
        >
      </el-breadcrumb>
    </div>

    <div class="personal-center">
      <el-dropdown>


        <el-avatar icon="el-icon-user-solid">
          </el-avatar>
        <span>{{adminId}}</span>
          <el-dropdown-menu slot="dropdown" >
            <el-dropdown-item @click.native="personalCenter"><i class="el-icon-info"></i>个人信息</el-dropdown-item>
            <el-dropdown-item @click.native="changePassword"><i class="el-icon-setting"></i>修改密码</el-dropdown-item>
            <el-dropdown-item @click.native="logout"><i class="el-icon-switch-button"></i>退出登录</el-dropdown-item>
          </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import {jwtDecode} from "jwt-decode";

export default {
  data() {
    return {
      adminId:""
    };
  },

  methods: {
    personalCenter(){
      this.$router.push("/personalCenterPage");
    },
    changePassword(){
      this.$router.push("/changePasswordPage");
    },
    /* 退出登录 */
    logout(){
       // 清除本地存储中的 token 令牌
      localStorage.removeItem('token');
      //显示退出登录消息
      this.$message('退出登录');
      // 使用路由导航到登录页面
      this.$router.push("/loginPage"); 
    },
    clickTest(){
      console.log('Test Clicked');
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
          this.adminId=decodedToken.id
        }catch (err){
          console.error('解析Token失败',err)
        }
      }else{
        console.warn('在本地存储中没有token')
      }
    }
  },

  /* 页面挂载时执行的方法列表 */
  mounted() {
    this.parseToken()
  },
};
</script>

<style>
.breadcrumb {
  padding: 20px 20px 20px 0px;
  float:left;
}
.personal-center{
  padding-top: 8px;
  float:right;
}
</style>