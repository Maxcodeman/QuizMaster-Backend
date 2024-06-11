<template>
  <div class="admin-page">

    <div class="search-bar">
      <el-input
          placeholder="按名称搜索"
          v-model="keyword"
          style="width: 600px"
          clearable
      >
      </el-input>
      <el-button type="primary" icon="el-icon-search" @click="pageAdminSearch()">搜索</el-button>
    </div>

    <div class="add-button">
      <el-button type="primary" @click="addDialogVisible=true"
      >新增<i class="el-icon-plus el-icon--right"></i
      ></el-button>
    </div>

    <el-table
        ref="multipleTable"
        :data="adminData"
        tooltip-effect="dark"
        style="width: 100%"
    >
      <el-table-column>
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="管理员id">
              <span>{{ props.row.adminId }}</span>
            </el-form-item>
            <el-form-item label="管理员名称">
              <span>{{ props.row.adminName }}</span>
            </el-form-item>
            <el-form-item label="手机号">
              <span>{{ props.row.mobile }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
    </el-table>

    <!-- 新增对话框 -->
    <el-dialog title="添加管理员" :visible.sync="addDialogVisible" width="30%">

      <el-form
          :model="addForm"
          :rules="rules"
          ref="addForm">
        <el-form-item label="管理员名称" prop="name">
          <el-input v-model="addForm.adminName"></el-input>
        </el-form-item>

        <el-form-item label="手机号" prop="mobile">
          <el-input v-model="addForm.mobile"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="mobile">
          <el-input show-password v-model="addForm.password"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button
            type="primary" @click=" () => {addDialogVisible = false;addAdmin(); }"
        >保存</el-button
        >
      </div>
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
      /*管理员数据*/
      adminData: [],
      //关键词
      keyword:"",
      /* 新增表单 */
      addForm:{
        adminId:"",
        adminName:"",
        mobile:"",
      },
      //新增表单字段规则
      rules: {
        adminName: [
          { required: true, message: '请输入名字', trigger: 'blur' },
          { min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur' }
        ],
        mobile: [
          { required: true, message: '请填写手机号码', trigger: 'blur' },
          {
            pattern: /^1[3456789]\d{9}$/,
            message: "手机号码格式不正确",
            trigger: ["blur", "change"],
          },
        ],
        password:[
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 25, message: '长度在 1 到 25 个字符', trigger: 'blur' }
        ]
      },
      /* 添加对话框的可视性 */
      addDialogVisible:false,
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
      this.pageSelect();
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.pageNo=val
      this.pageSelect();
      console.log(`当前页: ${val}`);
    },

    pageAdminSelect(){
      axios.get("/admin/admins?name="+this.keyword+"&page="+this.pageNo+"&pageSize="+this.pageSize).then((res) => {
        if (res.data.code == 1) {
          this.adminData = res.data.data.rows;
          this.total=res.data.data.total
        } else {
          this.$message.error(res.data.msg);
        }
      })
          .catch((err) => {
            console.log(err);
          });
    },

    //新增管理员
    addAdmin(){
      this.$refs.addForm.validate(valid => {
        if (valid) {
          // 验证通过，执行添加操作
          axios.post("/admin/admins", this.addForm)
              .then((res) => {
                if (res.data.code === 1) {
                  this.$message.success("添加成功");
                  location.reload()
                  // 可能还需要重置表单或关闭对话框等操作
                } else {
                  this.$message.error(res.data.msg);
                }
              })
              .catch((err) => {
                console.log(err);
              });
        } else {
          // 验证未通过，可选择给出提示或处理错误
          this.$message.warning("请检查表单填写是否有误！");
          return false; // 阻止后续操作
        }
      });
    },

    /* 根据关键字检索 */
    pageAdminSearch(){
        this.pageNo=1;
        this.pageAdminSelect();
    },

  },
  mounted() {
    this.pageAdminSelect();
  },
};
</script>

<style>
.admin-page {
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
.add-button {
  margin-top: 20px;
  margin-bottom: 20px;
  float: right;
}
.pagination {
  margin: 150px;

}
</style>