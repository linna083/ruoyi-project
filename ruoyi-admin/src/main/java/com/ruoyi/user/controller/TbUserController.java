package com.ruoyi.user.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.user.domain.TbUser;
import com.ruoyi.user.service.ITbUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户信息Controller
 * 
 * @author linna
 * @date 2026-03-17
 */
@Controller
@RequestMapping("/user/user")
public class TbUserController extends BaseController
{
    private String prefix = "user/user";

    @Autowired
    private ITbUserService tbUserService;

    @RequiresPermissions("user:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    /**
     * 查询用户信息列表
     */
    @RequiresPermissions("user:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbUser tbUser)
    {
        startPage();
        List<TbUser> list = tbUserService.selectTbUserList(tbUser);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @RequiresPermissions("user:user:export")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbUser tbUser)
    {
        List<TbUser> list = tbUserService.selectTbUserList(tbUser);
        ExcelUtil<TbUser> util = new ExcelUtil<TbUser>(TbUser.class);
        return util.exportExcel(list, "用户信息数据");
    }

    /**
     * 新增用户信息
     */
    @RequiresPermissions("user:user:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户信息
     */
    @RequiresPermissions("user:user:add")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbUser tbUser)
    {
        return toAjax(tbUserService.insertTbUser(tbUser));
    }

    /**
     * 修改用户信息
     */
    @RequiresPermissions("user:user:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbUser tbUser = tbUserService.selectTbUserById(id);
        mmap.put("tbUser", tbUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户信息
     */
    @RequiresPermissions("user:user:edit")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbUser tbUser)
    {
        return toAjax(tbUserService.updateTbUser(tbUser));
    }

    /**
     * 删除用户信息
     */
    @RequiresPermissions("user:user:remove")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbUserService.deleteTbUserByIds(ids));
    }
}
