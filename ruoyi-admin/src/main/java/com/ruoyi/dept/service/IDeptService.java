package com.ruoyi.dept.service;

import java.util.List;
import com.ruoyi.dept.domain.Dept;

/**
 * 部门信息Service接口
 * 
 * @author linna
 * @date 2026-04-16
 */
public interface IDeptService 
{
    /**
     * 查询部门信息
     * 
     * @param deptId 部门信息主键
     * @return 部门信息
     */
    public Dept selectDeptByDeptId(Long deptId);

    /**
     * 查询部门信息列表
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public List<Dept> selectDeptList(Dept dept);

    /**
     * 新增部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int insertDept(Dept dept);

    /**
     * 修改部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int updateDept(Dept dept);

    /**
     * 批量删除部门信息
     * 
     * @param deptIds 需要删除的部门信息主键集合
     * @return 结果
     */
    public int deleteDeptByDeptIds(Long[] deptIds);

    /**
     * 删除部门信息信息
     * 
     * @param deptId 部门信息主键
     * @return 结果
     */
    public int deleteDeptByDeptId(Long deptId);
}
