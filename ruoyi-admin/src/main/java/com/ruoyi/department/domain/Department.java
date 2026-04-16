package com.ruoyi.department.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 部门信息对象 department
 * 
 * @author ruoyi
 * @date 2026-03-24
 */
public class Department extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门ID */
    private Long deptId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    /** 部门负责人 */
    @Excel(name = "部门负责人")
    private String deptChairman;

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }

    public void setDeptChairman(String deptChairman) 
    {
        this.deptChairman = deptChairman;
    }

    public String getDeptChairman() 
    {
        return deptChairman;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("deptChairman", getDeptChairman())
            .append("parentId", getParentId())
            .toString();
    }
}
