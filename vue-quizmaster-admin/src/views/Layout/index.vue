<template>
    <el-container>
        <el-aside width="210px">
            <Aside/>
        </el-aside>
        <el-container>
            <el-header><Header/></el-header>
            <el-main>
                <router-view></router-view>
            </el-main>
            <el-footer><Footer/></el-footer>
        </el-container>
    </el-container>
</template>

<script>
import Header from "@/components/Header"
import Footer from "@/components/Footer"
import Aside from "@/components/Aside"
export default {
    components:{Header,Footer,Aside}
}
</script>

<style>
.el-footer{
    height:100px;
}
.el-header {
    background-color: white;
    color: #333;
    height: 2vh;
    border: 1px solid #e0e0e0;
    text-align: center;
    display: inline-block;
}
.el-main{
    height: 100%;
}
.el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    height:2px;
    line-height: 2vh;
  }
.el-aside{
    background-color: #304156;
}
</style>