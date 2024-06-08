<template>
<div >
    <div>
        <div class="sidebar-title">
            <img src="../../assets/logo.png" width="30px" height="30px">
            <span>面试达人后台管理</span>
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
          <i class="el-icon-s-home"></i><span>首页</span>
        </el-menu-item>

        <el-submenu index="2">
          <template slot="title"><i class="el-icon-menu"></i><span>内容管理</span></template>
              <el-menu-item index="2-1" route="/questionPage">题目管理</el-menu-item>
              <el-menu-item index="2-2" route="/categoryPage">分类管理</el-menu-item>
              <el-menu-item index="2-3" route="/typePage">题型管理</el-menu-item>
              <el-menu-item index="2-4" route="/notePage">笔记管理</el-menu-item>
              <el-menu-item index="2-5" route="/commentPage">评论管理</el-menu-item>
        </el-submenu>

        <el-menu-item index="3" route="/userPage">
          <template slot="title"
            ><i class="el-icon-user"></i>用户管理</template
          >
        </el-menu-item>

        <el-menu-item index="4" route="/logPage">
          <template slot="title"
            ><i class="el-icon-document"></i>日志查看</template
          >
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
  height:95vh;
}
.sidebar-title{
    color:white;
    text-align: center;
}
.el-aside::-webkit-scrollbar {
      display: none;
}
.el-submenu {
    max-height: 35vh; /*设置最大高度为屏幕高度的35% 方便展示*/
}
</style>
