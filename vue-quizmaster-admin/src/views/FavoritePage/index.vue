<template>
  <div class="category-page">

    <!--搜索表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="题目id">
        <el-input v-model="questionId" placeholder="题目id"></el-input>
      </el-form-item>

      <el-form-item label="用户id">
        <el-input v-model="userId" placeholder="用户id"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
        <el-button type="info" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>


    <el-table
        ref="multipleTable"
        :data="favoriteData"
        tooltip-effect="dark"
        style="width: 100%"
    >
      <el-table-column prop="favoriteId" label="收藏编号">
      </el-table-column>
      <el-table-column prop="questionId" label="题目ID">
      </el-table-column>
      <el-table-column prop="userId" label="用户ID">
      </el-table-column>
      <el-table-column prop="isFavorite" label="是否收藏">
        <template slot-scope="scope">
          {{scope.row.isFavorite?'是':'否'}}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
              slot="reference"
              size="small"
              type="danger"
              @click="deleteVisible=true;deleteId=scope.row.favoriteId"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 确认删除对话框 -->
    <el-dialog title="确认删除" :visible.sync="deleteVisible" width="30%">
      <i class="el-icon-warning">确定删除该收藏吗(该操作不可逆!)</i>
      <span slot="footer" class="dialog-footer">
        <el-button @click="deleteVisible = false">取 消</el-button>
        <el-button type="primary" @click=" () => { deleteVisible = false; deleteFavorite(); }"
        >确 定</el-button
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
export default {
  data() {
    return {
      //关键词
      userId:"",
      questionId:"",
      //要删除的分类id
      deleteId:0,
      favoriteData: [],
      addVisible: false,
      deleteVisible:false,
      /* 当前页码和页面尺寸及页数(已设置默认值) */
      pageNo:1,
      pageSize:10,
      total:20,
    };
  },
  methods: {
    // 查询方法
    onSubmit() {
      this.pageNo = 1;
      this.pageFavoriteSelect();
    },
    clear(){
      this.userId="";
      this.questionId="";
      this.pageFavoriteSelect()
    },
    /* 分页器改变 */
    handleSizeChange(val) {
      this.pageSize=val
      this.pageFavoriteSelect();
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.pageNo=val
      this.pageFavoriteSelect();
      console.log(`当前页: ${val}`);
    },
    //分页条件查询
    pageFavoriteSelect() {
      axios
          .get("/admin/favorite?questionId="+this.questionId+"&userId="+this.userId+"&page="+this.pageNo+"&pageSize="+this.pageSize)
          .then((res) => {
            if (res.data.code == 1) {
              this.favoriteData = res.data.data.rows;
              this.total=res.data.data.total
            } else {
              this.$message.error(res.data.msg);
            }
          })
          .catch((err) => {
            console.log(err);
          });
    },
    /**
     * 删除分类
     * @param row
     */
    deleteFavorite() {
      axios
          .delete("/admin/favorite/" +this.deleteId)
          .then((res) => {
            if (res.data.code == 1) {
              this.$message.success("删除成功");
              this.pageFavoriteSelect();
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
    this.pageFavoriteSelect()
  },
};
</script>

<style>
.category-page {
  padding: 20px;
}
.pagination {
  margin-top: 80px;
}
</style>