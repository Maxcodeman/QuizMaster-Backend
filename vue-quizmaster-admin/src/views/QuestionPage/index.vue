<template>
  <div class="question-page">
    <div class="upload-button">
      <el-button type="primary"
        >上传<i class="el-icon-upload el-icon--right"></i
      ></el-button>
    </div>

    <div class="search-bar">
      <el-input
        placeholder="按关键字搜索"
        v-model="keyword"
        style="width: 600px"
        clearable
      >
      </el-input>
      <el-button type="primary" icon="el-icon-search" @click="selectByKeyword()">搜搜看</el-button>
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
        v-model="selectedCategory"
        placeholder="按题目分类筛选"
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
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100%"
      :default-sort="{ prop: 'questionId', order: 'ascending' }"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="题目答案">
              <span>{{ props.row.questionAnswer }}</span>
            </el-form-item>
            <el-form-item label="创建时间">
              <span>{{ props.row.createTime }}</span>
            </el-form-item>
            <el-form-item label="更新时间">
              <span>{{ props.row.updateTime }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column prop="questionId" label="题目编号" sortable width="120">
        <template slot-scope="scope">{{ scope.row.questionId }}</template>
      </el-table-column>
      <el-table-column prop="questionCategory" label="题目分类" width="120" :formatter="formatCategoryType">
      </el-table-column>
      <el-table-column prop="questionType" label="题型" width="120" :formatter="formatQuestionType">
      </el-table-column>
      <el-table-column prop="questionContent" label="题目内容">
      </el-table-column>
      <el-table-column
        prop="starCount"
        label="收藏次数"
        sortable
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        prop="wrongCount"
        label="错误次数"
        sortable=""
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column label="操作" width="120">
          <template slot-scope="scope">
            <el-button size="small" @click="selectById(scope.row)"
              >编辑</el-button
            >
          </template>
      </el-table-column>
    </el-table>

    <div style="margin-top: 20px; float: left">
      <el-button type="danger" @click="deleteDialogVisible = true"
        >删除</el-button
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

    <!-- 编辑题目对话框 -->
    <el-dialog title="编辑题目" :visible.sync="editDialogVisible" width="30%">
      
      <el-form
      :model="editForm"
      ref="editForm">
        <el-form-item label="题目编号" prop="id">
                <el-input v-model="editForm.id" disabled></el-input>
              </el-form-item>
              <el-form-item label="题型" prop="type">
                <el-select placeholder="请选择题型" v-model="editForm.type">
                  <el-option v-for="item in typeOptions" :key="item.typeId" :label="item.typeName" :value="item.typeId"></el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="题目分类" prop="category">
                <el-select placeholder="请选择题目分类" v-model="editForm.category">
                  <el-option v-for="item in categoryOptions" :key="item.categoryId" :label="item.categoryName" :value="item.categoryId"></el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="题目内容" prop="content">
                <el-input v-model="editForm.content" type="textarea"></el-input>
              </el-form-item>

              <el-form-item label="题目答案" prop="answer">
                <el-input v-model="editForm.answer" type="textarea"></el-input>
              </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click=" () => {editDialogVisible = false;editById(); }"
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
                  <el-option v-for="item in typeOptions" :key="item.typeId" :label="item.typeName" :value="item.typeId"></el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="题目分类" prop="category">
                <el-select placeholder="请选择题目分类" v-model="addForm.category">
                  <el-option v-for="item in categoryOptions" :key="item.categoryId" :label="item.categoryName" :value="item.categoryId"></el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="题目内容" prop="content">
                <el-input v-model="addForm.content" type="textarea"></el-input>
              </el-form-item>

              <el-form-item label="题目答案" prop="answer">
                <el-input v-model="addForm.answer" type="textarea"></el-input>
              </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click=" () => {addDialogVisible = false;addOne(); }"
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
      tableData: [],

      /* 编辑表单 */
      editForm:{
        id:"",
        type:"",
        category:"",
        content:"",
        answer:"",
      },
      /* 新增表单 */
      addForm:{
        type:"",
        category:"",
        content:"",
        answer:"",
      },
      multipleSelection: [],
      /* 题型和题目分类选项 */
      typeOptions: [],
      categoryOptions: [],
      /* 当前选中的题型和题目分类 */
      selectedType: "",
      selectedCategory: "",
      /* 确认删除对话框的可视性 */
      deleteDialogVisible: false,
      /* 编辑对话框的可视性 */
      editDialogVisible:false,
      /* 添加对话框的可视性 */
      addDialogVisible:false,
      /* 当前页码和页面尺寸及页数(已设置默认值) */
      pageNo:1,
      pageSize:5,
      total:20,
      /* 关键字 */
      keyword:'',
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

    getQuestionType() {
      axios
        .get("/type")
        .then((res) => {
          if (res.data.code == 1) {
            this.typeOptions = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    getQuestionCategory() {
      axios
        .get("/category")
        .then((res) => {
          if (res.data.code == 1) {
            this.categoryOptions = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    selectById(row){
      this.editDialogVisible=true
      console.log(row.questionId)
      axios.get("/question?id="+row.questionId).then((res) => {
          if (res.data.code == 1) {
            this.editForm.id = res.data.data.questionId;
            this.editForm.type=res.data.data.questionType;
            this.editForm.category=res.data.data.questionCategory;
            this.editForm.content=res.data.data.questionContent;
            this.editForm.answer=res.data.data.questionAnswer;
            
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    /* 根据id修改 */
    editById(){
      console.log(this.editForm.id)
      axios.put("/question?id="+this.editForm.id+"&type="+this.editForm.type
      +"&category="+this.editForm.category+"&content="+this.editForm.content
      +"&answer="+this.editForm.answer).then((res) => {
          if (res.data.code == 1) {
            this.$message.success('修改成功');
            this.pageSelect();
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
      this.pageSelect();
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.pageNo=val
      this.pageSelect();
      console.log(`当前页: ${val}`);
    },
    pageSelect(){
      axios.get("/question/"+this.pageNo+"/"+this.pageSize).then((res) => {
          if (res.data.code == 1) {
            this.tableData = res.data.data.list;
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
    selectByKeyword(){
      axios.get("/question/search",{
        params: {
          keyword: this.keyword,
          pageNo: this.pageNo,
          pageSize: this.pageSize,
          typeId:this.selectedType,
          categoryId:this.selectedCategory
        },
      }).then((res) => {
          if (res.data.code == 1) {
            this.tableData = res.data.data.list;
            this.total=res.data.data.total
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    handleTypeChange(){
      this.selectByKeyword()
    },
    handleCategoryChange(){
      this.selectByKeyword()
    },

    // 根据题型的数字返回对应的名字
  formatQuestionType(row) {
    const typeId = row.questionType;
    const type = this.typeOptions.find(option => option.typeId === typeId);
    return type ? type.typeName : ''; // 返回题型名字或空字符串
  },

  // 根据题目分类的数字返回对应的名字
  formatCategoryType(row) {
    const categoryId = row.questionCategory;
    const category = this.categoryOptions.find(option => option.categoryId === categoryId);
    return category ? category.categoryName : ''; // 返回题型名字或空字符串
  },
  
  /* 根据id集合删除题目 */
  deleteByIds(){
    var questionIds = this.multipleSelection.map(item => item.questionId);
    axios.delete("/question/"+questionIds.join(',')).then((res) => {
          if (res.data.code == 1) {
            this.$message.success('删除成功');
            this.pageSelect();
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          console.log(err);
        });
  },
  addOne(){
      axios.post("/question?type="+this.addForm.type+"&category="+this.addForm.category
      +"&content="+this.addForm.content+"&answer="+this.addForm.answer).then((res) => {
          if (res.data.code == 1) {
            this.$message.success('新增成功');
            this.pageSelect();
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
    this.getQuestionType();
    this.getQuestionCategory();
    this.pageSelect();
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
.upload-button {
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