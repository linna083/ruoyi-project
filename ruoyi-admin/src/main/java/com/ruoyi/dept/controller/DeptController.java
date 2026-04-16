package com.ruoyi.dept.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.dept.domain.Dept;
import com.ruoyi.dept.service.IDeptService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 部门信息Controller
 * 
 * @author linna
 * @date 2026-04-16
 */
@RestController
@RequestMapping("/dept/dept")
public class DeptController extends BaseController
{
    @Autowired
    private IDeptService deptService;

    /**
     * 查询部门信息列表
     */
    @PreAuthorize("@ss.hasPermi('dept:dept:list')")
    @GetMapping("/list")
    public TableDataInfo list(Dept dept)
    {
        startPage();
        List<Dept> list = deptService.selectDeptList(dept);
        return getDataTable(list);
    }

    /**
     * 导出部门信息列表
     */
    @PreAuthorize("@ss.hasPermi('dept:dept:export')")
    @Log(title = "部门信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Dept dept)
    {
        List<Dept> list = deptService.selectDeptList(dept);
        ExcelUtil<Dept> util = new ExcelUtil<Dept>(Dept.class);
        util.exportExcel(response, list, "部门信息数据");
    }

    /**
     * 获取部门信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('dept:dept:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId)
    {
        return success(deptService.selectDeptByDeptId(deptId));
    }

    /**
     * 新增部门信息
     */
    @PreAuthorize("@ss.hasPermi('dept:dept:add')")
    @Log(title = "部门信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Dept dept)
    {
        return toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改部门信息
     */
    @PreAuthorize("@ss.hasPermi('dept:dept:edit')")
    @Log(title = "部门信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Dept dept)
    {
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除部门信息
     */
    @PreAuthorize("@ss.hasPermi('dept:dept:remove')")
    @Log(title = "部门信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deptIds}")
    public AjaxResult remove(@PathVariable Long[] deptIds)
    {
        return toAjax(deptService.deleteDeptByDeptIds(deptIds));
    }
}
