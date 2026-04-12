package com.ruoyi.book.service;

import java.util.List;
import com.ruoyi.book.domain.Book;

/**
 * 图书信息Service接口
 * 
 * @author linna
 * @date 2026-03-24
 */
public interface IBookService 
{
    /**
     * 查询图书信息
     * 
     * @param bookId 图书信息主键
     * @return 图书信息
     */
    public Book selectBookByBookId(Long bookId);

    /**
     * 查询图书信息列表
     * 
     * @param book 图书信息
     * @return 图书信息集合
     */
    public List<Book> selectBookList(Book book);

    /**
     * 新增图书信息
     * 
     * @param book 图书信息
     * @return 结果
     */
    public int insertBook(Book book);

    /**
     * 修改图书信息
     * 
     * @param book 图书信息
     * @return 结果
     */
    public int updateBook(Book book);

    /**
     * 批量删除图书信息
     * 
     * @param bookIds 需要删除的图书信息主键集合
     * @return 结果
     */
    public int deleteBookByBookIds(String bookIds);

    /**
     * 删除图书信息信息
     * 
     * @param bookId 图书信息主键
     * @return 结果
     */
    public int deleteBookByBookId(Long bookId);
}
