<template>
  <div class="category-page">
    <div>
      <el-popover placement="top" width="160" v-model="addVisible">
        <p>新增分类</p>
        <div style="text-align: right; margin: 0">
          <el-input
            v-model="inputAddCategory"
            placeholder="输入分类名字"
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
                addCategory(inputAddCategory);
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
    <el-dialog title="编辑分类" :visible.sync="editVisible" width="30%">
      <el-form :model="editForm" ref="editForm">
        <el-form-item label="分类编号" prop="id">
          <el-input v-model="editForm.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="分类名称" prop="type">
          <el-input v-model="editForm.name"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="
            () => {
              editVisible = false;
              editById();
            }
          "
          >保存</el-button
        >
      </span>
    </el-dialog>

    <el-table
      ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100%"
      :default-sort="{ prop: 'categoryId', order: 'ascending' }"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column prop="categoryId" label="分类编号" sortable width="200">
        <template slot-scope="scope">{{ scope.row.categoryId }}</template>
      </el-table-column>
      <el-table-column prop="categoryName" label="分类名称" width="200">
      </el-table-column>
      <el-table-column label="操作" width="120">
        <template slot-scope="scope">
          <el-button
            slot="reference"
            size="small"
            @click="selectById(scope.row)"
            >编辑</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <div style="margin-top: 20px; float: left">
      <el-popconfirm
        confirm-button-text="好的"
        cancel-button-text="不用了"
        icon="el-icon-info"
        icon-color="red"
        title="你确定删除选中的分类吗？(危险操作)"
        @confirm="deleteByIds()"
        @cancel="deleteDialogVisible = true"
      >
        <el-button
          type="danger"
          slot="reference"
          @click="
            () => {
              deleteDialogVisible = true;
            }
          "
          >删除</el-button
        >
      </el-popconfirm>
      <el-button @click="toggleSelection()">取消选择</el-button>
    </div>
  </div>
</template>

<script>
import axios from "@/api/axios";
export default {
  data() {
    return {
      tableData: [],
      addVisible: false,
      editVisible: false,
      inputAddCategory: "",
      multipleSelection: [],
      editForm:{
        id:"",
        name:""
      }
    };
  },
  methods: {
    toggleSelection(rows) {
      if (rows) {
        rows.forEach((row) => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    getAllCategories() {
      axios
        .get("/category")
        .then((res) => {
          if (res.data.code == 1) {
            this.tableData = res.data.data;
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
        .post("/category?categoryName=" + inputCategory)
        .then((res) => {
          if (res.data.code == 1) {
            this.$message.success("添加分类成功");
            this.getAllCategories();
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteByIds() {
      var categoryIds = this.multipleSelection.map((item) => item.categoryId);
      axios
        .delete("/category/" + categoryIds.join(","))
        .then((res) => {
          if (res.data.code == 1) {
            this.$message.success("删除分类成功");
            this.getAllCategories();
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    selectById(row) {
      this.editVisible=true
      axios
        .get("/category/" + row.categoryId)
        .then((res) => {
          if (res.data.code == 1) {
            this.editForm.id = res.data.data.categoryId;
            this.editForm.name = res.data.data.categoryName;
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    editById() {
      axios
        .put(
          "/category?id=" + this.editForm.id + "&name=" + this.editForm.name
        )
        .then((res) => {
          if (res.data.code == 1) {
            this.$message.success("修改分类成功");
            this.getAllCategories();
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
    this.getAllCategories();
  },
};
</script>

<style>
.category-page {
  padding: 100px;
}
</style>