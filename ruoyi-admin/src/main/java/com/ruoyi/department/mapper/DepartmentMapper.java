package com.ruoyi.department.mapper;

import java.util.List;
import com.ruoyi.department.domain.Department;

/**
 * 部门信息Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-24
 */
public interface DepartmentMapper 
{
    /**
     * 查询部门信息
     * 
     * @param deptId 部门信息主键
     * @return 部门信息
     */
    public Department selectDepartmentByDeptId(Long deptId);

    /**
     * 查询部门信息列表
     * 
     * @param department 部门信息
     * @return 部门信息集合
     */
    public List<Department> selectDepartmentList(Department department);

    /**
     * 新增部门信息
     * 
     * @param department 部门信息
     * @return 结果
     */
    public int insertDepartment(Department department);

    /**
     * 修改部门信息
     * 
     * @param department 部门信息
     * @return 结果
     */
    public int updateDepartment(Department department);

    /**
     * 删除部门信息
     * 
     * @param deptId 部门信息主键
     * @return 结果
     */
    public int deleteDepartmentByDeptId(Long deptId);

    /**
     * 批量删除部门信息
     * 
     * @param deptIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDepartmentByDeptIds(Long[] deptIds);
}
