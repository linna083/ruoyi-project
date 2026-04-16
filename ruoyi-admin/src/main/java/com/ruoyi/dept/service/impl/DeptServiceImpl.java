package com.ruoyi.dept.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dept.mapper.DeptMapper;
import com.ruoyi.dept.domain.Dept;
import com.ruoyi.dept.service.IDeptService;

/**
 * 部门信息Service业务层处理
 * 
 * @author linna
 * @date 2026-04-16
 */
@Service
public class DeptServiceImpl implements IDeptService 
{
    @Autowired
    private DeptMapper deptMapper;

    /**
     * 查询部门信息
     * 
     * @param deptId 部门信息主键
     * @return 部门信息
     */
    @Override
    public Dept selectDeptByDeptId(Long deptId)
    {
        return deptMapper.selectDeptByDeptId(deptId);
    }

    /**
     * 查询部门信息列表
     * 
     * @param dept 部门信息
     * @return 部门信息
     */
    @Override
    public List<Dept> selectDeptList(Dept dept)
    {
        LoginUser loginUser=SecurityUtils.getLoginUser();
        SysUser user=loginUser.getUser();

        boolean isAdmin=SecurityUtils.isAdmin(user.getUserId());

        dept.getParams().put("adminFlag",isAdmin);
        dept.getParams().put("userName",user.getUserName());


        return deptMapper.selectDeptList(dept);
    }

    /**
     * 新增部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int insertDept(Dept dept)
    {
        dept.setCreateBy(SecurityUtils.getUsername());
        return deptMapper.insertDept(dept);
    }

    /**
     * 修改部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int updateDept(Dept dept)
    {
        return deptMapper.updateDept(dept);
    }

    /**
     * 批量删除部门信息
     * 
     * @param deptIds 需要删除的部门信息主键
     * @return 结果
     */
    @Override
    public int deleteDeptByDeptIds(Long[] deptIds)
    {
        return deptMapper.deleteDeptByDeptIds(deptIds);
    }

    /**
     * 删除部门信息信息
     * 
     * @param deptId 部门信息主键
     * @return 结果
     */
    @Override
    public int deleteDeptByDeptId(Long deptId)
    {
        return deptMapper.deleteDeptByDeptId(deptId);
    }
}
