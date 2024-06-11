<template>
  <div class="question-page">
    <div class="import-button">
      <el-button type="primary"
      >导入<i class="el-icon-upload el-icon--right"></i
      ></el-button>
    </div>

    <div class="search-bar">
      <el-input
          placeholder="按题目描述搜索"
          v-model="keyword"
          style="width: 600px"
          clearable
      >
      </el-input>
      <el-button type="primary" icon="el-icon-search" @click="pageQuestionSearch()">搜索</el-button>
    </div>

    <!-- 条件筛选器 -->
    <div class="type-selector">
      <el-select v-model="selectedType" placeholder="按题型筛选" clearable @change="handleTypeChange">
        <el-option
            v-for="item in typeOptions"
            :key="item.typeId"
            :label="item.typeName"
            :value="item.typeId"
        >
        </el-option>
      </el-select>
    </div>

    <div class="category-selector">
      <el-select
          v-model="selectedCategoryId"
          placeholder="按分类筛选"
          clearable
          @change="handleCategoryChange"
      >
        <el-option
            v-for="item in categoryOptions"
            :key="item.categoryId"
            :label="item.categoryName"
            :value="item.categoryId"
        >
        </el-option>
      </el-select>
    </div>

    <div class="add-button">
      <el-button type="primary" @click="addDialogVisible=true"
      >新增<i class="el-icon-plus el-icon--right"></i
      ></el-button>
    </div>

    <el-table
        ref="multipleTable"
        :data="questionData"
        tooltip-effect="dark"
        style="width: 100%"
        stripe
        @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="questionId" label="题目编号">
      </el-table-column>
      <el-table-column prop="typeId" label="题型" :formatter="formatType">
      </el-table-column>
      <el-table-column prop="categoryId" label="分类" :formatter="formatCategory">
      </el-table-column>
      <el-table-column prop="questionDesc" label="题目描述">
      </el-table-column>

      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="A选项">
              <span>{{ props.row.optionA }}</span>
            </el-form-item>
            <el-form-item label="B选项">
              <span>{{ props.row.optionB }}</span>
            </el-form-item>
            <el-form-item label="C选项">
              <span>{{ props.row.optionC }}</span>
            </el-form-item>
            <el-form-item label="D选项">
              <span>{{ props.row.optionD }}</span>
            </el-form-item>
            <el-form-item label="答案">
              <span>{{ props.row.answer }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="120">
        <template slot-scope="scope">
          <el-button size="small" @click="selectById(scope.row)"
          >编辑
          </el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <div style="margin-top: 20px; float: left">
      <el-button type="danger" @click="deleteDialogVisible = true"
      >删除
      </el-button
      >
      <el-button @click="toggleSelection()">取消选择</el-button>
    </div>

    <!-- 确认删除对话框 -->
    <el-dialog title="确认删除" :visible.sync="deleteDialogVisible" width="30%">
      <i class="el-icon-warning">你确定要删除选中的题目吗?</i>
      <span slot="footer" class="dialog-footer">
        <el-button @click="deleteDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click=" () => { deleteDialogVisible = false; deleteByIds(); }"
        >确 定</el-button
        >
      </span>
    </el-dialog>

    <!-- 导入题目对话框 -->
    <el-dialog title="导入题目" :visible.sync="editDialogVisible" width="30%">

      <el-form
          :model="importForm"
          ref="importForm">
        <el-form-item label="分类" prop="category">
          <el-select placeholder="请选择分类" v-model="updateForm.categoryId">
            <el-option v-for="item in categoryOptions" :key="item.categoryId" :label="item.categoryName"
                       :value="item.categoryId"></el-option>
          </el-select>

          <el-form-item label="分类" prop="category">
            <el-select placeholder="请选择分类" v-model="updateForm.categoryId">
              <el-option v-for="item in categoryOptions" :key="item.categoryId" :label="item.categoryName"
                         :value="item.categoryId"></el-option>
            </el-select>
          </el-form-item>
        </el-form-item>


      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click=" () => {editDialogVisible = false;updateQuestion(); }"
        >保存</el-button
        >
      </span>
    </el-dialog>

    <!-- 编辑题目对话框 -->
    <el-dialog title="编辑题目" :visible.sync="editDialogVisible" width="30%">

      <el-form
          :model="updateForm"
          ref="updateForm">
        <el-form-item label="题目编号" prop="questionId">
          <el-input v-model="updateForm.questionId" disabled></el-input>
        </el-form-item>
        <el-form-item label="题型" prop="type">
          <el-select placeholder="请选择题型" v-model="updateForm.type">
            <el-option v-for="item in typeOptions" :key="item.typeId" :label="item.typeName"
                       :value="item.typeId"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分类" prop="category">
          <el-select placeholder="请选择分类" v-model="updateForm.categoryId">
            <el-option v-for="item in categoryOptions" :key="item.categoryId" :label="item.categoryName"
                       :value="item.categoryId"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="题目描述" prop="questionDesc">
          <el-input v-model="updateForm.questionDesc" type="textarea"></el-input>
        </el-form-item>

        <el-form-item label="A选项" prop="optionA">
          <el-input v-model="updateForm.optionA" type="textarea"></el-input>
        </el-form-item>

        <el-form-item label="B选项" prop="optionB">
          <el-input v-model="updateForm.optionB" type="textarea"></el-input>
        </el-form-item>
        <el-form-item label="C选项" prop="optionC">
          <el-input v-model="updateForm.optionC" type="textarea"></el-input>
        </el-form-item>
        <el-form-item label="D选项" prop="optionD">
          <el-input v-model="updateForm.optionD" type="textarea"></el-input>
        </el-form-item>
        <el-form-item label="答案" prop="answer">
          <el-input v-model="updateForm.answer"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click=" () => {editDialogVisible = false;updateQuestion(); }"
        >保存</el-button
        >
      </span>
    </el-dialog>

    <!-- 新增题目对话框 -->
    <el-dialog title="新增题目" :visible.sync="addDialogVisible" width="30%">

      <el-form
          :model="addForm"
          ref="addForm">
        <el-form-item label="题型" prop="type">
          <el-select placeholder="请选择题型" v-model="addForm.type">
            <el-option v-for="item in typeOptions" :key="item.typeId" :label="item.typeName"
                       :value="item.typeId"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分类" prop="category">
          <el-select placeholder="请选择分类" v-model="addForm.categoryId">
            <el-option v-for="item in categoryOptions" :key="item.categoryId" :label="item.categoryName"
                       :value="item.categoryId"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="题目描述" prop="questionDesc">
          <el-input v-model="addForm.questionDesc" type="textarea"></el-input>
        </el-form-item>


        <el-form-item label="A选项" prop="optionA">
          <el-input v-model="addForm.optionA" type="textarea"></el-input>
        </el-form-item>

        <el-form-item label="B选项" prop="optionB">
          <el-input v-model="addForm.optionB" type="textarea"></el-input>
        </el-form-item>
        <el-form-item label="C选项" prop="optionC">
          <el-input v-model="addForm.optionC" type="textarea"></el-input>
        </el-form-item>
        <el-form-item label="D选项" prop="optionD">
          <el-input v-model="addForm.optionD" type="textarea"></el-input>
        </el-form-item>
        <el-form-item label="答案" prop="answer">
          <el-input v-model="addForm.answer"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click=" () => {addDialogVisible = false;addQuestion(); }"
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

export default {
  data() {
    return {
      /*表格数据*/
      questionData: [],
      //关键词
      keyword: "",
      /*导入表单*/
      importForm:{
        categoryId:""
      },
      /* 编辑表单 */
      updateForm: {
        questionId: "",
        questionDesc: "",
        type: "",
        categoryId: "",
        optionA: "",
        optionB: "",
        optionC: "",
        optionD: "",
        answer: ""
      },
      /* 新增表单 */
      addForm: {
        questionDesc: "",
        type: "",
        categoryId: "",
        optionA: "",
        optionB: "",
        optionC: "",
        optionD: "",
        answer: ""
      },
      multipleSelection: [],
      /* 题型和题目分类选项 */
      typeOptions: [
        { typeId: 1, typeName: "单选" },
        { typeId: 2, typeName: "多选" },
        { typeId: 3, typeName: "判断" }
      ],
      categoryOptions: [],
      /* 当前选中的题型和题目分类 */
      selectedType: "",
      selectedCategoryId: "",
      /* 确认删除对话框的可视性 */
      deleteDialogVisible: false,
      /* 编辑对话框的可视性 */
      editDialogVisible: false,
      /* 添加对话框的可视性 */
      addDialogVisible: false,
      /* 当前页码和页面尺寸及页数(已设置默认值) */
      pageNo: 1,
      pageSize: 5,
      total: 20,
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
    //获取前50个分类
    getCategory() {
      axios
          .get("/admin/categories?page="+1+"&pageSize="+50)
          .then((res) => {
            if (res.data.code == 1) {
              this.categoryOptions = res.data.data.rows;
            } else {
              this.$message.error(res.data.msg);
            }
          })
          .catch((err) => {
            console.log(err);
          });
    },
    selectById(row) {
      this.editDialogVisible = true
      axios.get("/admin/questions/" + row.questionId).then((res) => {
        if (res.data.code == 1) {
          this.updateForm.questionId = res.data.data.questionId;
          this.updateForm.questionDesc = res.data.data.questionDesc;
          this.updateForm.optionA = res.data.data.optionA;
          this.updateForm.optionB = res.data.data.optionB;
          this.updateForm.optionC = res.data.data.optionC;
          this.updateForm.optionD = res.data.data.optionD;
          this.updateForm.answer = res.data.data.answer;
          this.updateForm.type = res.data.data.type;
          this.updateForm.categoryId = res.data.data.categoryId;
        } else {
          this.$message.error(res.data.msg);
        }
      })
          .catch((err) => {
            console.log(err);
          });
    },
    /* 修改题目 */
    updateQuestion() {
      axios.put("/admin/questions",this.updateForm).then((res) => {
        if (res.data.code == 1) {
          this.$message.success('修改成功');
          this.pageQuestionSelect();
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
      this.pageSize = val
      this.pageQuestionSelect();
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.pageNo = val
      this.pageQuestionSelect();
      console.log(`当前页: ${val}`);
    },
    pageQuestionSelect() {
      axios.get("/admin/questions?page=" + this.pageNo + "&pageSize=" + this.pageSize
          +"&questionDesc="+this.keyword+"&categoryId="+this.selectedCategoryId+"&type="+this.selectedType).then((res) => {
        if (res.data.code == 1) {
          this.questionData = res.data.data.rows;
          this.total = res.data.data.total
        } else {
          this.$message.error(res.data.msg);
        }
      })
          .catch((err) => {
            console.log(err);
          });
    },

    /* 根据关键字检索 */
    pageQuestionSearch() {
      this.pageNo = 1
      this.pageQuestionSelect()
    },
    handleTypeChange() {
      this.pageQuestionSearch()
    },
    handleCategoryChange() {
      this.pageQuestionSearch()
    },

    // 根据题型的数字返回对应的名字
    formatType(row) {
      const typeId = row.type;
      const type = this.typeOptions.find(option => option.typeId === typeId);
      return type ? type.typeName : ''; // 返回题型名字或空字符串
    },

    // 根据分类的数字返回对应的名字
    formatCategory(row) {
      const categoryId = row.categoryId;
      const category = this.categoryOptions.find(option => option.categoryId === categoryId);
      return category ? category.categoryName : ''; // 返回分类名字或空字符串
    },

    /* 根据id集合删除题目 */
    deleteByIds() {
      var questionIds = this.multipleSelection.map(item => item.questionId);
      axios.delete("/admin/questions/" + questionIds.join(',')).then((res) => {
        if (res.data.code == 1) {
          this.$message.success('删除成功');
          this.pageQuestionSelect();
        } else {
          this.$message.error(res.data.msg);
        }
      })
          .catch((err) => {
            console.log(err);
          });
    },
    //新增题目
    addQuestion() {
      axios.post("/admin/questions",this.addForm).then((res) => {
        if (res.data.code == 1) {
          this.$message.success('添加成功');
          this.pageQuestionSelect();
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
    this.getCategory();
    this.pageQuestionSelect();
  },
};
</script>

<style>
.question-page {
  padding: 20px;
}

.demo-table-expand {
  font-size: 0;
}

.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}

.import-button {
  margin-bottom: 20px;
  float: left;
}

.add-button {
  margin-top: 20px;
  margin-bottom: 20px;
  float: right;
}

.pagination {
  margin: 150px;

}

.type-selector {
  margin-top: 20px;
  margin-bottom: 20px;
  float: left;
  position: relative;
  right: 90px;
}

.category-selector {
  margin-top: 20px;
  margin-bottom: 20px;
  float: left;
  position: relative;
  right: 80px;
}
</style>