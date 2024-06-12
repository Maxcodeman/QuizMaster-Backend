<template>
    <div class="comment-page">
      <div class="search-bar">
        <el-input
            placeholder="用户id"
            v-model="userId"
            style="width: 20%; margin:20px"
            clearable
        >
        </el-input>
        <el-input
            placeholder="题目id"
            v-model="questionId"
            style="width: 20%; margin:20px"
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
        <el-button type="primary" icon="el-icon-search" @click="pageCommentSearch()">搜索</el-button>
      </div>

      <el-table
          ref="multipleTable"
          :data="commentData"
          tooltip-effect="dark"
          style="width: 100%"
      >
        <el-table-column prop="commentId" label="评论编号">
        </el-table-column>
        <el-table-column prop="commentContent" label="评论内容">
        </el-table-column>
        <el-table-column prop="questionId" label="题目编号">
        </el-table-column>
        <el-table-column prop="userId" label="用户编号">
        </el-table-column>
        <el-table-column prop="createTime" label="评论时间">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
                slot="reference"
                size="small"
                type="danger"
                @click="deleteComment(scope.row)"
            >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>

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
import dayjs from "dayjs";

export default {
  data() {
    return {
      //关键词
      userId:"",
      questionId:"",
      createTime:[],
      /*开始时间和结束时间*/
      startTime:"",
      endTime:"",
      //评论数据
      commentData: [],
      /* 当前页码和页面尺寸及页数(已设置默认值) */
      pageNo:1,
      pageSize:10,
      total:20,
    };
  },
  methods: {
    /* 分页器改变 */
    handleSizeChange(val) {
      this.pageSize=val
      this.pageNoteSelect();
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.pageNo=val
      this.pageNoteSelect();
      console.log(`当前页: ${val}`);
    },
    //分页条件查询
    pageCommentSelect() {
      axios
          .get("/admin/comments?page="+this.pageNo+"&pageSize="+this.pageSize+"&userId="+this.userId
              +"&questionId="+this.questionId+"&startTime="+this.startTime+"&endTime="+this.endTime)
          .then((res) => {
            if (res.data.code == 1) {
              this.commentData = res.data.data.rows;
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
    pageCommentSearch(){
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
      this.pageCommentSelect();
    },

    /**
     * 删除评论
     * @param row
     */
    deleteComment(row) {
      axios
          .delete("/admin/comments/" +row.commentId)
          .then((res) => {
            if (res.data.code == 1) {
              this.$message.success("删除成功");
              this.pageCommentSelect();
            } else {
              this.$message.error(res.data.msg);
            }
          })
          .catch((err) => {
            console.log(err);
          });
    },
  },
  mounted() {
    this.pageCommentSelect()
  },
}
</script>

<style>
.comment-page{
    padding: 20px;
}
</style>