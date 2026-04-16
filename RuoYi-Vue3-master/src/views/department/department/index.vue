<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="部门名称" prop="deptName">
        <el-input
          v-model="queryParams.deptName"
          placeholder="请输入部门名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="部门负责人" prop="deptChairman">
        <el-input
          v-model="queryParams.deptChairman"
          placeholder="请输入部门负责人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上级部门ID" prop="parentId">
        <el-input
          v-model="queryParams.parentId"
          placeholder="请输入上级部门ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['department:department:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="Sort"
          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="departmentList"
      row-key="deptId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="部门名称" prop="deptName" />
      <el-table-column label="部门负责人" align="center" prop="deptChairman" />
      <el-table-column label="上级部门ID" align="center" prop="parentId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['department:department:edit']">修改</el-button>
          <el-button link type="primary" icon="Plus" @click="handleAdd(scope.row)" v-hasPermi="['department:department:add']">新增</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['department:department:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改部门信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="departmentRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="部门名称" prop="deptName">
          <el-input v-model="form.deptName" placeholder="请输入部门名称" />
        </el-form-item>
        <el-form-item label="部门负责人" prop="deptChairman">
          <el-input v-model="form.deptChairman" placeholder="请输入部门负责人" />
        </el-form-item>
        <el-form-item label="上级部门ID" prop="parentId">
          <el-tree-select
            v-model="form.parentId"
            :data="departmentOptions"
            :props="{ value: 'deptId', label: 'deptName', children: 'children' }"
            value-key="deptId"
            placeholder="请选择上级部门ID"
            check-strictly
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Department">
import { listDepartment, getDepartment, delDepartment, addDepartment, updateDepartment } from "@/api/department/department"

const { proxy } = getCurrentInstance()

const departmentList = ref([])
const departmentOptions = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const title = ref("")
const isExpandAll = ref(true)
const refreshTable = ref(true)

const data = reactive({
  form: {},
  queryParams: {
    deptName: null,
    deptChairman: null,
    parentId: null
  },
  rules: {
    deptName: [
      { required: true, message: "部门名称不能为空", trigger: "blur" }
    ],
    deptChairman: [
      { required: true, message: "部门负责人不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询部门信息列表 */
function getList() {
  loading.value = true
  listDepartment(queryParams.value).then(response => {
    departmentList.value = proxy.handleTree(response.data, "deptId", "parentId")
    loading.value = false
  })
}

/** 查询部门信息下拉树结构 */
function getTreeselect() {
  listDepartment().then(response => {
    departmentOptions.value = []
    const data = { deptId: 0, deptName: '顶级节点', children: [] }
    data.children = proxy.handleTree(response.data, "deptId", "parentId")
    departmentOptions.value.push(data)
  })
}
	
// 取消按钮
function cancel() {
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    deptId: null,
    deptName: null,
    deptChairman: null,
    parentId: null
  }
  proxy.resetForm("departmentRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

/** 新增按钮操作 */
function handleAdd(row) {
  reset()
  getTreeselect()
  if (row != null && row.deptId) {
    form.value.parentId = row.deptId
  } else {
    form.value.parentId = 0
  }
  open.value = true
  title.value = "添加部门信息"
}

/** 展开/折叠操作 */
function toggleExpandAll() {
  refreshTable.value = false
  isExpandAll.value = !isExpandAll.value
  nextTick(() => {
    refreshTable.value = true
  })
}

/** 修改按钮操作 */
async function handleUpdate(row) {
  reset()
  await getTreeselect()
  if (row != null) {
    form.value.parentId = row.parentId
  }
  getDepartment(row.deptId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改部门信息"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["departmentRef"].validate(valid => {
    if (valid) {
      if (form.value.deptId != null) {
        updateDepartment(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addDepartment(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  proxy.$modal.confirm('是否确认删除部门信息编号为"' + row.deptId + '"的数据项？').then(function() {
    return delDepartment(row.deptId)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

getList()
</script>
