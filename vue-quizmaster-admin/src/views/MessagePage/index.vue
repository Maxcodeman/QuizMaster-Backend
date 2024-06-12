<template>
  <div class="message-page">

    <div class="search-bar">
      <el-input
          placeholder="按管理员id搜索"
          v-model="keyword"
          style="width: 30%; margin: 20px"
          clearable
      >
      </el-input>
      <el-date-picker
          v-model="createTime"
          type="datetimerange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期">
      </el-date-picker>
      <el-button type="primary" icon="el-icon-search" @click="pageMessageSearch()">搜索</el-button>
    </div>

    <div class="add-button">
      <el-button type="primary" @click="addDialogVisible=true"
      >新增<i class="el-icon-plus el-icon--right"></i
      ></el-button>
    </div>

    <el-table
        ref="multipleTable"
        :data="messageData"
        tooltip-effect="dark"
        style="width: 100%"
    >
      <el-table-column prop="messageId" label="资讯编号">
      </el-table-column>
      <el-table-column prop="messageContent" label="资讯内容">
      </el-table-column>
      <el-table-column prop="adminId" label="管理员编号">
      </el-table-column>
      <el-table-column prop="view" label="浏览量">
      </el-table-column>
      <el-table-column prop="createTime" label="发布时间">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
              slot="reference"
              size="small"
              @click="selectById(scope.row)"
          >编辑</el-button
          >
          <el-button
              slot="reference"
              size="small"
              type="danger"
              @click="deleteMessage(scope.row)"
          >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 新增资讯对话框 -->
    <el-dialog title="新增资讯" :visible.sync="addDialogVisible" width="30%">

      <el-form
          :model="addForm"
          ref="addForm">
        <el-form-item label="资讯内容" prop="messageContent">
          <el-input v-model="addForm.messageContent" type="textarea" placeholder="请输入资讯内容"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click=" () => {addDialogVisible = false;addMessage(); }"
        >保存</el-button
        >
      </span>
    </el-dialog>

    <!-- 修改资讯对话框 -->
    <el-dialog title="修改资讯" :visible.sync="updateDialogVisible" width="30%">

      <el-form
          :model="updateForm"
          ref="updateForm">
        <el-form-item label="资讯编号" prop="messageId">
          <el-input v-model="updateForm.messageId" disabled></el-input>
        </el-form-item>

        <el-form-item label="资讯内容" prop="messageContent">
          <el-input v-model="updateForm.messageContent" type="textarea" placeholder="请输入资讯内容"></el-input>
        </el-form-item>

        <el-form-item label="浏览量" prop="view">
          <el-input-number v-model="updateForm.view"  label="描述文字"></el-input-number>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="updateDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click=" () => {updateDialogVisible = false;updateMessage(); }"
        >保存</el-button
        >
      </span>
    </el-dialog>

    <div class="pagination">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNo"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
      >
      </el-pagination>
    </div>

  </div>
</template>

<script>
import axios from "@/api/axios";
import {jwtDecode} from "jwt-decode";
import dayjs from "dayjs";

export default {
  data() {
    return {
      addDialogVisible:false,
      updateDialogVisible:false,
      //关键词
      keyword:"",
      messageData: [],
      createTime:[],
      /*开始时间和结束时间*/
      startTime:"",
      endTime:"",
      /* 当前页码和页面尺寸及页数(已设置默认值) */
      pageNo:1,
      pageSize:10,
      total:20,
      addForm:{
        adminId:"",
        messageContent:""
      },
      updateForm:{
        messageId:"",
        messageContent:"",
        view:0
      }
    };
  },
  methods: {
    selectById(row) {
      this.updateDialogVisible = true
      axios.get("/admin/messages/" + row.messageId).then((res) => {
        if (res.data.code == 1) {
          this.updateForm.messageId = res.data.data.messageId;
          this.updateForm.messageContent = res.data.data.messageContent;
          this.updateForm.view = res.data.data.view;
        } else {
          this.$message.error(res.data.msg);
        }
      })
          .catch((err) => {
            console.log(err);
          });
    },
    /* 修改资讯 */
    updateMessage() {
      axios.put("/admin/messages",this.updateForm).then((res) => {
        if (res.data.code == 1) {
          this.$message.success('修改成功');
          this.pageMessageSelect();
        } else {
          this.$message.error(res.data.msg);
        }
      })
          .catch((err) => {
            console.log(err);
          });
    },
    /* 分页器改变 */
    handleSizeChange(val) {
      this.pageSize=val
      this.pageMessageSelect();
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.pageNo=val
      this.pageMessageSelect();
      console.log(`当前页: ${val}`);
    },
    //分页条件查询
    pageMessageSelect() {
      axios
          .get("/admin/messages?page="+this.pageNo+"&pageSize="+this.pageSize+"&adminId="+this.keyword
          +"&startTime="+this.startTime+"&endTime="+this.endTime)
          .then((res) => {
            if (res.data.code == 1) {
              this.messageData = res.data.data.rows;
              this.total=res.data.data.total
            } else {
              this.$message.error(res.data.msg);
            }
          })
          .catch((err) => {
            console.log(err);
          });
    },

    /* 根据关键字检索 */
    pageMessageSearch(){
      if (this.createTime && this.createTime.length === 2) {
        // createTime不为空，且有开始时间和结束时间
        this.startTime = dayjs(this.createTime[0]).format("YYYY-MM-DDTHH:mm:ss");
        this.endTime = dayjs(this.createTime[1]).format("YYYY-MM-DDTHH:mm:ss");
      } else {
        // createTime为空或时间选择不完整，设置默认值或进行其他处理
        this.startTime = "";
        this.endTime = "";
      }
      this.pageNo=1;
      this.pageMessageSelect();
    },

    /**
     * 删除资讯
     * @param row
     */
    deleteMessage(row) {
      axios
          .delete("/admin/messages/" +row.messageId)
          .then((res) => {
            if (res.data.code == 1) {
              this.$message.success("删除成功");
              this.pageMessageSelect();
            } else {
              this.$message.error(res.data.msg);
            }
          })
          .catch((err) => {
            console.log(err);
          });
    },
    /**
     * 新增资讯
     */
    addMessage() {
      axios.post("/admin/messages",this.addForm).then((res) => {
        if (res.data.code == 1) {
          this.$message.success('添加成功');
          this.pageMessageSelect();
        } else {
          this.$message.error(res.data.msg);
        }
      })
          .catch((err) => {
            console.log(err);
          });
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
          this.addForm.adminId=decodedToken.id
        }catch (err){
          console.error('解析Token失败',err)
        }
      }else{
        console.warn('在本地存储中没有token')
      }
    }
  },
  mounted() {
    this.parseToken()
    this.pageMessageSelect()
  },
}
</script>

<style>
.message-page{
  padding: 20px;
}
.pagination {
  margin-top: 80px;
}
.add-button {
  margin-top: 20px;
  margin-bottom: 20px;
  float: right;
}
</style>