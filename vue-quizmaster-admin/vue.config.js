const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // 配置devServer
  devServer: {
    host: 'localhost', // 项目运行的ip
    port: 9527, // 项目运行的端口号
  }
})
