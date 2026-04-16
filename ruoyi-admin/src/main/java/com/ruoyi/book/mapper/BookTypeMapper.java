package com.ruoyi.book.mapper;

import java.util.List;
import com.ruoyi.book.domain.BookType;
import com.ruoyi.book.domain.Book;

/**
 * 图书类型Mapper接口
 * 
 * @author linna
 * @date 2026-04-11
 */
public interface BookTypeMapper 
{
    /**
     * 查询图书类型
     * 
     * @param typeId 图书类型主键
     * @return 图书类型
     */
    public BookType selectBookTypeByTypeId(Long typeId);

    /**
     * 查询图书类型列表
     * 
     * @param bookType 图书类型
     * @return 图书类型集合
     */
    public List<BookType> selectBookTypeList(BookType bookType);

    /**
     * 新增图书类型
     * 
     * @param bookType 图书类型
     * @return 结果
     */
    public int insertBookType(BookType bookType);

    /**
     * 修改图书类型
     * 
     * @param bookType 图书类型
     * @return 结果
     */
    public int updateBookType(BookType bookType);

    /**
     * 删除图书类型
     * 
     * @param typeId 图书类型主键
     * @return 结果
     */
    public int deleteBookTypeByTypeId(Long typeId);

    /**
     * 批量删除图书类型
     * 
     * @param typeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookTypeByTypeIds(Long[] typeIds);

    /**
     * 批量删除图书信息
     * 
     * @param typeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookByTypeIds(Long[] typeIds);
    
    /**
     * 批量新增图书信息
     * 
     * @param bookList 图书信息列表
     * @return 结果
     */
    public int batchBook(List<Book> bookList);
    

    /**
     * 通过图书类型主键删除图书信息信息
     * 
     * @param typeId 图书类型ID
     * @return 结果
     */
    public int deleteBookByTypeId(Long typeId);
}
