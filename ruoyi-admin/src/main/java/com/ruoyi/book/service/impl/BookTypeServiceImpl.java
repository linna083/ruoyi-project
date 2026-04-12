package com.ruoyi.book.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.book.domain.Book;
import com.ruoyi.book.mapper.BookTypeMapper;
import com.ruoyi.book.domain.BookType;
import com.ruoyi.book.service.IBookTypeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 图书类型Service业务层处理
 * 
 * @author linna
 * @date 2026-03-24
 */
@Service
public class BookTypeServiceImpl implements IBookTypeService 
{
    @Autowired
    private BookTypeMapper bookTypeMapper;

    /**
     * 查询图书类型
     * 
     * @param typeId 图书类型主键
     * @return 图书类型
     */
    @Override
    public BookType selectBookTypeByTypeId(Long typeId)
    {
        return bookTypeMapper.selectBookTypeByTypeId(typeId);
    }

    /**
     * 查询图书类型列表
     * 
     * @param bookType 图书类型
     * @return 图书类型
     */
    @Override
    public List<BookType> selectBookTypeList(BookType bookType)
    {
        return bookTypeMapper.selectBookTypeList(bookType);
    }

    /**
     * 新增图书类型
     * 
     * @param bookType 图书类型
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBookType(BookType bookType)
    {
        int rows = bookTypeMapper.insertBookType(bookType);
        insertBook(bookType);
        return rows;
    }

    /**
     * 修改图书类型
     * 
     * @param bookType 图书类型
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBookType(BookType bookType)
    {
        bookTypeMapper.deleteBookByTypeId(bookType.getTypeId());
        insertBook(bookType);
        return bookTypeMapper.updateBookType(bookType);
    }

    /**
     * 批量删除图书类型
     * 
     * @param typeIds 需要删除的图书类型主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBookTypeByTypeIds(String typeIds)
    {
        bookTypeMapper.deleteBookByTypeIds(Convert.toStrArray(typeIds));
        return bookTypeMapper.deleteBookTypeByTypeIds(Convert.toStrArray(typeIds));
    }

    /**
     * 删除图书类型信息
     * 
     * @param typeId 图书类型主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBookTypeByTypeId(Long typeId)
    {
        bookTypeMapper.deleteBookByTypeId(typeId);
        return bookTypeMapper.deleteBookTypeByTypeId(typeId);
    }

    /**
     * 新增图书信息信息
     * 
     * @param bookType 图书类型对象
     */
    public void insertBook(BookType bookType)
    {
        List<Book> bookList = bookType.getBookList();
        Long typeId = bookType.getTypeId();
        if (StringUtils.isNotNull(bookList))
        {
            List<Book> list = new ArrayList<Book>();
            for (Book book : bookList)
            {
                book.setTypeId(typeId);
                list.add(book);
            }
            if (list.size() > 0)
            {
                bookTypeMapper.batchBook(list);
            }
        }
    }
}
