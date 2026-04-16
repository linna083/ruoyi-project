package com.ruoyi.department.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.department.mapper.DepartmentMapper;
import com.ruoyi.department.domain.Department;
import com.ruoyi.department.service.IDepartmentService;

/**
 * 部门信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-24
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService 
{
    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 查询部门信息
     * 
     * @param deptId 部门信息主键
     * @return 部门信息
     */
    @Override
    public Department selectDepartmentByDeptId(Long deptId)
    {
        return departmentMapper.selectDepartmentByDeptId(deptId);
    }

    /**
     * 查询部门信息列表
     * 
     * @param department 部门信息
     * @return 部门信息
     */
    @Override
    public List<Department> selectDepartmentList(Department department)
    {
        return departmentMapper.selectDepartmentList(department);
    }

    /**
     * 新增部门信息
     * 
     * @param department 部门信息
     * @return 结果
     */
    @Override
    public int insertDepartment(Department department)
    {
        return departmentMapper.insertDepartment(department);
    }

    /**
     * 修改部门信息
     * 
     * @param department 部门信息
     * @return 结果
     */
    @Override
    public int updateDepartment(Department department)
    {
        return departmentMapper.updateDepartment(department);
    }

    /**
     * 批量删除部门信息
     * 
     * @param deptIds 需要删除的部门信息主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentByDeptIds(Long[] deptIds)
    {
        return departmentMapper.deleteDepartmentByDeptIds(deptIds);
    }

    /**
     * 删除部门信息信息
     * 
     * @param deptId 部门信息主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentByDeptId(Long deptId)
    {
        return departmentMapper.deleteDepartmentByDeptId(deptId);
    }
}
