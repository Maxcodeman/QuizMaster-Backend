import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from "@/views/Layout"

Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    component:Layout,
    redirect:"/homePage",
    children:[{
      path:"homePage",
      component:() => import("@/views/HomePage"),
      name:"home",
      meta:{
        title:"首页"
      }
    },
      {
        path:"questionPage",
        component:() => import("@/views/QuestionPage"),
        name:"question",
        meta:{
          title:"题目管理"
        }
      },{
        path:"categoryPage",
        component:() => import("@/views/CategoryPage"),
        name:"category",
        meta:{
          title:"分类管理"
        }
      },{
        path:"notePage",
        component:() => import("@/views/NotePage"),
        name:"note",
        meta:{
          title:"笔记管理"
        }
      },{
        path:"commentPage",
        component:() => import("@/views/CommentPage"),
        name:"comment",
        meta:{
          title:"评论管理"
        }
      },{
        path:"userPage",
        component:() => import("@/views/UserPage"),
        name:"user",
        meta:{
          title:"用户管理"
        }
      },{
        path:"logPage",
        component:() => import("@/views/LogPage"),
        name:"log",
        meta:{
          title:"日志查看"
        }
      },{
        path:"personalCenterPage",
        component:() => import("@/views/PersonalCenterPage"),
        name:"personal-center",
        meta:{
          title:"个人信息"
        }
      },{
        path:"changePasswordPage",
        component:() => import("@/views/ChangePasswordPage"),
        name:"change-password",
        meta:{
          title:"修改密码"
        }
      },{
        path:"adminPage",
        component:() => import("@/views/AdminPage"),
        name:"admin",
        meta:{
          title:"管理员管理"
        }
      },
    ]
  },

  {
    path:'/loginPage',
    name:'login',
    component: () => import("@/views/LoginPage")
  }
]


const router = new VueRouter({
  routes
})

router.beforeEach((to,from,next)=>{
      var tokenStr = localStorage.getItem('token')
      /* 未携带token令牌 */
      if(!tokenStr){
        if(to.path=='/loginPage'){
          return next();
        }
        else{
          return next('loginPage')
        }
        /* 已携带token令牌 */
      } else{
        /* 直接转向首页 */
        if(to.path=='/loginPage'){
          return next('homePage');
        }
        else{
          return next()
        }
      }
    }
)

export default router
