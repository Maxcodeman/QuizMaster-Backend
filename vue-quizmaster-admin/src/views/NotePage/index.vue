<template>
    <div class="note-page">

      <div class="search-bar">
        <el-input
            placeholder="按用户id搜索"
            v-model="keyword"
            style="width: 600px"
            clearable
        >
        </el-input>
        <el-button type="primary" icon="el-icon-search" @click="pageNoteSearch()">搜索</el-button>
      </div>

      <el-table
          ref="multipleTable"
          :data="noteData"
          tooltip-effect="dark"
          style="width: 100%"
      >
        <el-table-column prop="noteId" label="笔记编号">
        </el-table-column>
        <el-table-column prop="noteContent" label="笔记内容">
        </el-table-column>
        <el-table-column prop="questionId" label="题目编号">
        </el-table-column>
        <el-table-column prop="userId" label="用户编号">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
                slot="reference"
                size="small"
                type="danger"
                @click="deleteNote(scope.row)"
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

export default {
  data() {
    return {
      //关键词
      keyword:"",
      noteData: [],
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
    pageNoteSelect() {
      axios
          .get("/admin/notes?page="+this.pageNo+"&pageSize="+this.pageSize+"&userId="+this.keyword)
          .then((res) => {
            if (res.data.code == 1) {
              this.noteData = res.data.data.rows;
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
    pageNoteSearch(){
      this.pageNo=1;
      this.pageNoteSelect();
    },

    /**
     * 删除笔记
     * @param row
     */
    deleteNote(row) {
      axios
          .delete("/admin/notes/" +row.noteId)
          .then((res) => {
            if (res.data.code == 1) {
              this.$message.success("删除成功");
              this.pageNoteSelect();
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
    this.pageNoteSelect()
  },
}
</script>

<style>
.note-page{
    padding: 20px;
}
.pagination {
  margin-top: 80px;
}
</style>