package com.ruoyi.user.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.user.mapper.TbUserMapper;
import com.ruoyi.user.domain.TbUser;
import com.ruoyi.user.service.ITbUserService;

import javax.validation.Validator;

/**
 * 用户信息Service业务层处理
 * 
 * @author test
 * @date 2026-03-17
 */
@Service
public class TbUserServiceImpl implements ITbUserService 
{
    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    protected Validator validator;

    public String importUser(List<TbUser> userList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(userList) || userList.size() == 0)
        {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (TbUser user : userList)
        {
            try
            {
                // 验证是否存在这个用户
                TbUser u = tbUserMapper.selectUserByUserName(user.getName());
                if (StringUtils.isNull(u))
                {
                    BeanValidators.validateWithException(validator, user);
  //                  deptService.checkDeptDataScope(user.getDeptId());
   //                 String password = configService.selectConfigByKey("sys.user.initPassword");
   //                 user.setPassword(SecurityUtils.encryptPassword(password));
                    user.setCreateBy(operName);
                    tbUserMapper.insertTbUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    BeanValidators.validateWithException(validator, user);
 //                   checkUserAllowed(u);
 //                   checkUserDataScope(u.getUserId());
  //                  deptService.checkDeptDataScope(user.getDeptId());
                    user.setId(u.getId());
 //                   user.setDeptId(u.getDeptId());
                    user.setUpdateBy(operName);
                    tbUserMapper.updateTbUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
  //              log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    /**
     * 查询用户信息
     * 
     * @param id 用户信息主键
     * @return 用户信息
     */
    @Override
    public TbUser selectTbUserById(Long id)
    {
        return tbUserMapper.selectTbUserById(id);
    }

    /**
     * 查询用户信息列表
     * 
     * @param tbUser 用户信息
     * @return 用户信息
     */
    @Override
    public List<TbUser> selectTbUserList(TbUser tbUser)
    {
        return tbUserMapper.selectTbUserList(tbUser);
    }

    /**
     * 新增用户信息
     * 
     * @param tbUser 用户信息
     * @return 结果
     */
    @Override
    public int insertTbUser(TbUser tbUser)
    {
        return tbUserMapper.insertTbUser(tbUser);
    }

    /**
     * 修改用户信息
     * 
     * @param tbUser 用户信息
     * @return 结果
     */
    @Override
    public int updateTbUser(TbUser tbUser)
    {
        return tbUserMapper.updateTbUser(tbUser);
    }

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteTbUserByIds(Long[] ids)
    {
        return tbUserMapper.deleteTbUserByIds(ids);
    }

    /**
     * 删除用户信息信息
     * 
     * @param id 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteTbUserById(Long id)
    {
        return tbUserMapper.deleteTbUserById(id);
    }
}
