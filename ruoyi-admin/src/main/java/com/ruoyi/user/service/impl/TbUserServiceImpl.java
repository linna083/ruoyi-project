package com.ruoyi.user.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.user.mapper.TbUserMapper;
import com.ruoyi.user.domain.TbUser;
import com.ruoyi.user.service.ITbUserService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户信息Service业务层处理
 * 
 * @author linna
 * @date 2026-03-17
 */
@Service
public class TbUserServiceImpl implements ITbUserService 
{
    @Autowired
    private TbUserMapper tbUserMapper;

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
    public int deleteTbUserByIds(String ids)
    {
        return tbUserMapper.deleteTbUserByIds(Convert.toStrArray(ids));
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
