import axios from 'axios';
import router from '../router/index'; // 路由实例

const instance = axios.create({
  // 设置基础URL等配置
  baseURL: 'http://localhost:8080',
  timeout: 5000, // 请求超时时间
});

// 添加请求拦截器
instance.interceptors.request.use(
  config => {
    // 在发送请求之前做些什么
    const token = localStorage.getItem('token'); // 从本地存储获取 Token
    if (token) {
      config.headers['token'] = `${token}`;
    }
    return config;
  },
  error => {
    // 对请求错误做些什么
    return Promise.reject(error);
  }
);

// 添加响应拦截器
instance.interceptors.response.use(
  response => {
    // 对响应数据做些什么
    return response;
  },
  error => {
    // 对响应错误做些什么
    if (error.response && error.msg == '未登录') {
      // Token 失效，重定向到登录页面
      localStorage.removeItem("token");
      router.push('/loginPage');
    }
    return Promise.reject(error);
  }
);

export default instance;
