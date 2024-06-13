<template>
  <div class="home-page">
    <el-carousel :interval="4000" type="card" height="250px">
      <el-carousel-item v-for="item in 1" :key="item">
        <h2 class="medium" v-if="item==1">欢迎使用刷题宝后台管理系统</h2>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
export default {};
</script>

<style>
.home-page {
  padding: 120px;
}
.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>