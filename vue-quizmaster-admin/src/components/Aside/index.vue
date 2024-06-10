<template>
  <div>
    <div>
      <div class="sidebar-title">
        <img src="../../assets/logo.png" width="30px" height="30px">
        <span>刷题宝后台管理系统</span>
      </div>
      <el-menu
          default-active="1"
          class="el-menu-vertical-demo"
          background-color="#304156"
          text-color="#fff"
          active-text-color="#ffd04b"
          router="true"
      >
        <el-menu-item index="1" route="/homePage">
          <i class="el-icon-s-home"></i>首页
        </el-menu-item>

        <el-menu-item index="2" route="/questionPage">
          <i class="el-icon-question"></i>题目管理</el-menu-item>

        <el-menu-item index="3" route="/categoryPage"><i class="el-icon-folder"></i>分类管理</el-menu-item>

        <el-menu-item index="4" route="/notePage"><i class="el-icon-notebook-1"></i>笔记管理</el-menu-item>

        <el-menu-item index="5" route="/commentPage"><i class="el-icon-chat-dot-square"></i>评论管理</el-menu-item>

        <el-menu-item index="6" route="/commentPage"><i class="el-icon-message"></i>资讯管理</el-menu-item>

        <el-menu-item index="7" route="/userPage"><i class="el-icon-user"></i>用户管理

        </el-menu-item>

        <el-menu-item index="8" route="/favoritePage"><i class="el-icon-collection"></i>收藏夹管理</el-menu-item>

        <el-menu-item index="9" route="/wrongPage"><i class="el-icon-document-delete"></i>错题本管理</el-menu-item>

        <el-menu-item index="10" route="/logPage"><i class="el-icon-document"></i>日志管理
        </el-menu-item>

        <el-menu-item index="11" route="/adminPage"><i class="el-icon-s-custom"></i>管理员管理
        </el-menu-item>
      </el-menu>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
.el-menu {
  text-align: left;
  height: 95vh;
}

.sidebar-title {
  color: white;
  text-align: center;
}

.el-aside::-webkit-scrollbar {
  display: none;
}

.el-submenu {
  max-height: 35vh; /*设置最大高度为屏幕高度的35% 方便展示*/
}
</style>
