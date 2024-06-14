<template>
  <div class="question-page">
    <!--搜索表单-->
    <el-form :inline="true"  class="demo-form-inline">
      <el-form-item label="用户名">
        <el-input v-model="username" placeholder="用户名"></el-input>
      </el-form-item>

      <el-form-item label="手机号">
        <el-input v-model="mobile" placeholder="手机号"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
        <el-button type="info" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>


<!--    <div class="add-button">-->
<!--      <el-button type="primary" @click="addDialogVisible=true"-->
<!--      >新增<i class="el-icon-plus el-icon&#45;&#45;right"></i-->
<!--      ></el-button>-->
<!--    </div>-->

    <el-table
        ref="multipleTable"
        :data="userData"
        tooltip-effect="dark"
        style="width: 100%"
        stripe
        @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="userId" label="用户id">
      </el-table-column>
      <el-table-column prop="avatar" label="头像">
        <template slot-scope="scope">
          <img :src="scope.row.avatar" width="100px" height="70px" alt="头像">
        </template>
      </el-table-column>
      <el-table-column prop="username" label="用户名" >
      </el-table-column>
<!--      <el-table-column prop="password" label="密码">-->
<!--      </el-table-column>-->
      <el-table-column prop="mobile" label="手机号">
      </el-table-column>
      <el-table-column prop="state" label="状态">
        <template slot-scope="scope">
          {{scope.row.state?"启用":"禁用"}}
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间">
      </el-table-column>
      <el-table-column prop="updateTime" label="更新时间">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope" >
<!--          <el-button size="small" @click="selectById(scope.row)">编辑</el-button>-->
          <el-button size="small" type="primary" @click="()=>{
            updateForm.userId=scope.row.userId;
            updateForm.state=!scope.row.state;
            updateUser();}">启/禁用
          </el-button>
        </template>
      </el-table-column>
    </el-table>

<!--    <div style="margin-top: 20px; float: left">-->
<!--      <el-button type="danger" @click="deleteDialogVisible = true"-->
<!--      >删除-->
<!--      </el-button-->
<!--      >-->
<!--      <el-button @click="toggleSelection()">取消选择</el-button>-->
<!--    </div>-->

    <!-- 确认删除对话框 -->
    <el-dialog title="确认删除" :visible.sync="deleteDialogVisible" width="30%">
      <i class="el-icon-warning">你确定要删除选中的用户吗?</i>
      <span slot="footer" class="dialog-footer">
        <el-button @click="deleteDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click=" () => { deleteDialogVisible = false; deleteByIds(); }"
        >确 定</el-button
        >
      </span>
    </el-dialog>

    <!-- 编辑用户对话框 -->
<!--    <el-dialog title="编辑用户" :visible.sync="editDialogVisible" width="30%">-->
<!--      <el-form-->
<!--          :model="updateForm"-->
<!--          ref="updateForm">-->
<!--        <el-form-item label="用户id" prop="userId">-->
<!--          <el-input v-model="updateForm.userId" disabled></el-input>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="用户名" prop="username">-->
<!--          <el-input v-model="updateForm.username" type="text"></el-input>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="头像">-->
<!--          <el-upload-->
<!--              class="avatar-uploader"-->
<!--              action="http://localhost:8080/admin/upload"-->
<!--              :headers="uploadHeader"-->
<!--              name="image"-->
<!--              :show-file-list="false"-->
<!--              :on-success="handleAvatarSuccessForUpdate"-->
<!--              :before-upload="beforeAvatarUpload">-->
<!--            <img v-if="updateForm.avatar" :src="updateForm.avatar" class="avatar" />-->
<!--            <i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
<!--          </el-upload>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="密码" prop="password">-->
<!--          <el-input v-model="updateForm.password" type="text"></el-input>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="手机号" prop="mobile">-->
<!--          <el-input v-model="updateForm.mobile" type="text"></el-input>-->
<!--        </el-form-item>-->


<!--        <el-form-item label="状态" prop="type">-->
<!--          <el-select placeholder="请选择状态" v-model="updateForm.state">-->
<!--            <el-option v-for="item in typeOptions" :key="item.typeId" :label="item.typeName"-->
<!--                       :value="item.typeId==1?true:false"></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--      </el-form>-->

<!--      <span slot="footer" class="dialog-footer">-->
<!--        <el-button @click="editDialogVisible = false">取 消</el-button>-->
<!--        <el-button type="primary" @click=" () => {editDialogVisible = false;updateUser(); }"-->
<!--        >保存</el-button-->
<!--        >-->
<!--      </span>-->
<!--    </el-dialog>-->

    <!-- 新增用户对话框 -->
<!--    <el-dialog title="新增用户" :visible.sync="addDialogVisible" width="30%">-->

<!--      <el-form-->
<!--          :model="addForm"-->
<!--          ref="addForm">-->

<!--        <el-form-item label="用户名" prop="username">-->
<!--          <el-input v-model="addForm.username" type="text"></el-input>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="密码" prop="password">-->
<!--          <el-input v-model="addForm.password" type="text"></el-input>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="手机号" prop="mobile">-->
<!--          <el-input v-model="addForm.mobile" type="text"></el-input>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="头像">-->
<!--          <el-upload-->
<!--              class="avatar-uploader"-->
<!--              action="http://localhost:8080/admin/upload"-->
<!--              name="image"-->
<!--              :show-file-list="false"-->
<!--              :on-success="handleAvatarSuccessForAdd"-->
<!--              :before-upload="beforeAvatarUpload">-->
<!--            <img v-if="addForm.avatar" :src="addForm.avatar" class="avatar" />-->
<!--            <i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
<!--          </el-upload>-->
<!--        </el-form-item>-->
<!--      </el-form>-->

<!--      <span slot="footer" class="dialog-footer">-->
<!--        <el-button @click="addDialogVisible = false">取 消</el-button>-->
<!--        <el-button type="primary" @click=" () => {addDialogVisible = false;addUser(); }"-->
<!--        >保存</el-button-->
<!--        >-->
<!--      </span>-->
<!--    </el-dialog>-->

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
      //上传文件请求头
      uploadHeader:{
        token: localStorage.getItem("token")
      },
      //上传文件列表
      fileList: [],
      /*表格数据*/
      userData: [],
      //关键词
      keyword: "",
      username:"",
      mobile:"",
      image: "",
      /*导入表单*/
      importForm:{
        categoryId:""
      },
      /* 编辑表单 */
      updateForm: {
        userId: "",
        username: "",
        password: "",
        state: true,
        createTime: "",
        updateTime: "",
        avatar: "",
        mobile: ""
      },
      /* 新增表单 */
      addForm: {
        username: "",
        password: "",
        mobile: "",
        avatar: ""
      },
      multipleSelection: [],
      /* 题型和题目分类选项 */
      typeOptions: [
        { typeId: 1, typeName: "启用" },
        { typeId: 2, typeName: "禁用" }
      ],
      categoryOptions: [],
      /* 当前选中的题型和题目分类 */
      selectedType: "",
      selectedCategoryId: "",
      /*导入对话框的可视性*/
      importDialogVisible:false,
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
    // 查询方法
    onSubmit() {
      this.pageNo = 1;
      this.pageUserSelect();
    },

    clear(){
      this.username="";
      this.mobile="";
      this.pageUserSelect()
    },
    //文件上传相关
    handleAvatarSuccessForAdd(res) {
      this.addForm.avatar=res.data;
    },
    handleAvatarSuccessForUpdate(res) {
      this.updateForm.avatar=res.data;
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 10;
      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 10MB!");
      }
      return isJPG && isLt2M;
    },


    handleSuccess(res, file, fileList) {
      if (res.code==1) {
        console.log(file)
        console.log(fileList)
        this.$message.success('文件上传成功');
        this.importDialogVisible = false;
        this.pageUserSelect();
      } else {
        this.$message.error('文件上传失败');
      }
    },
    handleError(err, file, fileList) {
      console.log(file)
      console.log(fileList)
      this.$message.error('文件上传失败');
    },

    submitUpload() {
      this.$refs.upload.submit();
    },

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
      axios.get("/admin/users/" + row.userId).then((res) => {
        if (res.data.code == 1) {
          this.updateForm.userId = res.data.data.userId;
          this.updateForm.avatar = res.data.data.avatar;
          this.updateForm.username = res.data.data.username;
          this.updateForm.password = res.data.data.password;
          this.updateForm.state = res.data.data.state;
          this.updateForm.mobile = res.data.data.mobile;
        } else {
          this.$message.error(res.data.msg);
        }
      })
          .catch((err) => {
            console.log(err);
          });
    },

    /* 修改用户 */
    updateUser() {
      axios.put("/admin/users",this.updateForm).then((res) => {
        if (res.data.code == 1) {
          this.$message.success('修改成功');
          this.pageUserSelect();
          this.updateForm.userId=""
          this.updateForm.username=""
          this.updateForm.password=""
          this.updateForm.avatar=""
          this.updateForm.mobile=""
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
      this.pageUserSelect();
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.pageNo = val
      this.pageUserSelect();
      console.log(`当前页: ${val}`);
    },
    pageUserSelect() {
      axios.get("/admin/users?page=" + this.pageNo + "&pageSize=" + this.pageSize + "&username=" + this.username
          + "&mobile=" + this.mobile).then((res) => {
        if (res.data.code == 1) {
          this.userData = res.data.data.rows;
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
      this.pageUserSelect()
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
      var userIds = this.multipleSelection.map(item => item.userId);
      axios.delete("/admin/users/" + userIds.join(',')).then((res) => {
        if (res.data.code == 1) {
          this.$message.success('删除成功');
          this.pageUserSelect();
        } else {
          this.$message.error(res.data.msg);
        }
      })
          .catch((err) => {
            console.log(err);
          });
    },
    //新增用户
    addUser() {
      axios.post("/admin/users",this.addForm).then((res) => {
        if (res.data.code == 1) {
          this.$message.success('添加成功');
          this.pageUserSelect();
          this.addForm =  {
            username: "",
            password: "",
            mobile: "",
            avatar: ""
          };
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
    this.pageUserSelect();
  },
};
</script>

<style>
.question-page {
  padding: 20px;
}

.add-button {
  margin-top: 20px;
  margin-bottom: 20px;
  float: right;
}

.pagination {
  margin: 150px;

}

//文件上传类样式
.el-upload .avatar-uploader  {
  border: 1px dashed #000000;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  line-height: 100px;
  text-align: left;
}
.avatar {
  width: 100px;
  height: 100px;
  display: block;
}
</style>