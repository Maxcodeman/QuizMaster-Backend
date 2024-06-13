<template>
    <div class="log-page">
      <div class="search-bar">
        <el-input
            placeholder="管理员id"
            v-model="adminId"
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
        <el-button type="primary" icon="el-icon-search" @click="pageLogSearch()">搜索</el-button>
      </div>

      <el-table
          ref="multipleTable"
          :data="logData"
          tooltip-effect="dark"
          style="width: 100%"
      >
        <el-table-column prop="logId" label="日志编号">
        </el-table-column>
        <el-table-column prop="adminId" label="管理员编号">
        </el-table-column>
        <el-table-column prop="className" label="操作类名">
        </el-table-column>
        <el-table-column prop="methodName" label="操作方法名">
        </el-table-column>
        <el-table-column prop="methodParams" label="操作方法参数">
        </el-table-column>
        <el-table-column prop="returnValue" label="操作方法返回值">
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间">
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
      adminId:"",
      createTime:[],
      /*开始时间和结束时间*/
      startTime:"",
      endTime:"",
      //日志数据
      logData: [],
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
      this.pageLogSelect();
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.pageNo=val
      this.pageLogSelect();
      console.log(`当前页: ${val}`);
    },
    //分页条件查询
    pageLogSelect() {
      axios
          .get("/admin/logs?page="+this.pageNo+"&pageSize="+this.pageSize+"&adminId="+this.adminId
              +"&startTime="+this.startTime+"&endTime="+this.endTime)
          .then((res) => {
            if (res.data.code == 1) {
              this.logData = res.data.data.rows;
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
    pageLogSearch(){
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
      this.pageLogSelect();
    },

  },
  mounted() {
    this.pageLogSelect()
  },
}
</script>

<style>
.log-page{
    padding: 20px;
}
.pagination {
  margin-top: 80px;
}
</style>