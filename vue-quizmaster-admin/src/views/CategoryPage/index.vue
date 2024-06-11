<template>
  <div class="category-page">

    <div class="search-bar">
      <el-input
          placeholder="按名称搜索"
          v-model="keyword"
          style="width: 600px"
          clearable
      >
      </el-input>
      <el-button type="primary" icon="el-icon-search" @click="pageCategorySearch()">搜索</el-button>
    </div>
    
    <div style="margin-top: 100px">
      <el-popover placement="top" width="160" v-model="addVisible">
        <p>添加分类</p>
        <div style="text-align: right; margin: 0">
          <el-input
            v-model="inputCategory"
            placeholder="输入分类名称"
            size="small"
          ></el-input>
          <el-button size="mini" type="text" @click="addVisible = false"
            >取消</el-button
          >
          <el-button
            type="primary"
            size="mini"
            @click="
              () => {
                addVisible = false;
                addCategory(inputCategory);
              }
            "
            >保存</el-button
          >
        </div>
        <el-button slot="reference" type="primary"
          >新增<i class="el-icon-plus el-icon--right"></i
        ></el-button>
      </el-popover>
    </div>

    <!-- 编辑分类对话框 -->
    <el-dialog title="编辑分类" :visible.sync="updateVisible" width="30%">
      <el-form :model="updateForm" ref="updateForm">
        <el-form-item label="分类编号" prop="id">
          <el-input v-model="updateForm.categoryId" disabled></el-input>
        </el-form-item>
        <el-form-item label="分类名称" prop="type">
          <el-input v-model="updateForm.categoryName"></el-input>
        </el-form-item>
        <el-form-item label="题目数量" prop="type">
          <el-input v-model="updateForm.questionCount" disabled></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="updateVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="
            () => {
              updateVisible = false;
              updateCategory();
            }
          "
          >保存</el-button
        >
      </span>
    </el-dialog>

    <el-table
      ref="multipleTable"
      :data="categoryData"
      tooltip-effect="dark"
      style="width: 100%"
    >
      <el-table-column prop="categoryId" label="分类编号">
      </el-table-column>
      <el-table-column prop="categoryName" label="分类名称">
      </el-table-column>
      <el-table-column prop="questionCount" label="题目数量">
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
              @click="deleteVisible=true;deleteId=scope.row.categoryId"
          >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 确认删除对话框 -->
    <el-dialog title="确认删除" :visible.sync="deleteVisible" width="30%">
      <i class="el-icon-warning">确定删除分类吗?该分类的所有题目都会被删除(该操作不可逆!)</i>
      <span slot="footer" class="dialog-footer">
        <el-button @click="deleteVisible = false">取 消</el-button>
        <el-button type="primary" @click=" () => { deleteVisible = false; deleteCategory(deleteId); }"
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
      keyword:"",
      //要删除的分类id
      deleteId:0,
      categoryData: [],
      addVisible: false,
      deleteVisible:false,
      updateVisible: false,
      inputCategory: "",
      updateForm:{
        categoryId:"",
        categoryName:"",
        questionCount:0
      },
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
      this.pageCategorySelect();
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.pageNo=val
      this.pageCategorySelect();
      console.log(`当前页: ${val}`);
    },
    //分页条件查询
    pageCategorySelect() {
      axios
        .get("/admin/categories?categoryName="+this.keyword+"&page="+this.pageNo+"&pageSize="+this.pageSize)
        .then((res) => {
          if (res.data.code == 1) {
            this.categoryData = res.data.data.rows;
            this.total=res.data.data.total
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    addCategory(inputCategory) {
      axios
        .post("/admin/categories?categoryName=" + inputCategory)
        .then((res) => {
          if (res.data.code == 1) {
            this.$message.success("添加成功");
            this.pageCategorySelect();
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    /* 根据关键字检索 */
    pageCategorySearch(){
      this.pageNo=1;
      this.pageCategorySelect();
    },

    /**
     * 删除分类
     * @param row
     */
    deleteCategory() {
      axios
        .delete("/admin/categories/" +this.deleteId)
        .then((res) => {
          if (res.data.code == 1) {
            this.$message.success("删除成功");
            this.pageCategorySelect();
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },


    selectById(row) {
      this.updateVisible=true
      axios
        .get("/admin/categories/" + row.categoryId)
        .then((res) => {
          if (res.data.code == 1) {
            this.updateForm.categoryId = res.data.data.categoryId;
            this.updateForm.categoryName = res.data.data.categoryName;
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //修改分类
    updateCategory() {
      axios
        .put(
          "/admin/categories",this.updateForm
        )
        .then((res) => {
          if (res.data.code == 1) {
            this.$message.success("修改成功");
            this.pageCategorySelect();
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
    this.pageCategorySelect()
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