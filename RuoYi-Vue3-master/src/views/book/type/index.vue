<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="图书类型名称" prop="typeName">
        <el-input
          v-model="queryParams.typeName"
          placeholder="请输入图书类型名称"
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
          v-hasPermi="['book:type:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['book:type:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['book:type:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['book:type:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="typeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="图书类型ID" align="center" prop="typeId" />
      <el-table-column label="图书类型名称" align="center" prop="typeName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['book:type:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['book:type:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改图书类型对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="typeRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图书类型名称" prop="typeName">
          <el-input v-model="form.typeName" placeholder="请输入图书类型名称" />
        </el-form-item>
        <el-divider content-position="center">图书信息信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="Plus" @click="handleAddBook">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="Delete" @click="handleDeleteBook">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="bookList" :row-class-name="rowBookIndex" @selection-change="handleBookSelectionChange" ref="book">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="图书名称" prop="bookName" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.bookName" placeholder="请输入图书名称" />
            </template>
          </el-table-column>
          <el-table-column label="图书作者" prop="bookAuthor" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.bookAuthor" placeholder="请输入图书作者" />
            </template>
          </el-table-column>
        </el-table>
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

<script setup name="Type">
import { listType, getType, delType, addType, updateType } from "@/api/book/type"

const { proxy } = getCurrentInstance()
const { book_type } = proxy.useDict('book_type')

const typeList = ref([])
const bookList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const checkedBook = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    typeName: null
  },
  rules: {
    typeName: [
      { required: true, message: "图书类型名称不能为空", trigger: "blur" }
    ]
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询图书类型列表 */
function getList() {
  loading.value = true
  listType(queryParams.value).then(response => {
    typeList.value = response.rows
    total.value = response.total
    loading.value = false
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
    typeId: null,
    typeName: null
  }
  bookList.value = []
  proxy.resetForm("typeRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.typeId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加图书类型"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _typeId = row.typeId || ids.value
  getType(_typeId).then(response => {
    form.value = response.data
    bookList.value = response.data.bookList
    open.value = true
    title.value = "修改图书类型"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["typeRef"].validate(valid => {
    if (valid) {
      form.value.bookList = bookList.value
      if (form.value.typeId != null) {
        updateType(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addType(form.value).then(response => {
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
  const _typeIds = row.typeId || ids.value
  proxy.$modal.confirm('是否确认删除图书类型编号为"' + _typeIds + '"的数据项？').then(function() {
    return delType(_typeIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 图书信息序号 */
function rowBookIndex({ row, rowIndex }) {
  row.index = rowIndex + 1
}

/** 图书信息添加按钮操作 */
function handleAddBook() {
  let obj = {}
  obj.bookName = ""
  obj.bookAuthor = ""
  obj.bookImage = ""
  bookList.value.push(obj)
}

/** 图书信息删除按钮操作 */
function handleDeleteBook() {
  if (checkedBook.value.length == 0) {
    proxy.$modal.msgError("请先选择要删除的图书信息数据")
  } else {
    const books = bookList.value
    const checkedBooks = checkedBook.value
    bookList.value = books.filter(function(item) {
      return checkedBooks.indexOf(item.index) == -1
    })
  }
}

/** 复选框选中数据 */
function handleBookSelectionChange(selection) {
  checkedBook.value = selection.map(item => item.index)
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('book/type/export', {
    ...queryParams.value
  }, `type_${new Date().getTime()}.xlsx`)
}

getList()
</script>
