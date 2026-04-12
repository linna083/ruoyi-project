package com.ruoyi.book.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 图书信息对象 book
 * 
 * @author linna
 * @date 2026-03-24
 */
public class Book extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图书ID */
    private Long bookId;

    /** 图书名称 */
    @Excel(name = "图书名称")
    private String bookName;

    /** 图书作者 */
    @Excel(name = "图书作者")
    private String bookAuthor;

    /** 图书封面 */
    @Excel(name = "图书封面")
    private String bookImage;

    /** 图书类型ID */
    @Excel(name = "图书类型ID")
    private Long typeId;

    public void setBookId(Long bookId) 
    {
        this.bookId = bookId;
    }

    public Long getBookId() 
    {
        return bookId;
    }

    public void setBookName(String bookName) 
    {
        this.bookName = bookName;
    }

    public String getBookName() 
    {
        return bookName;
    }

    public void setBookAuthor(String bookAuthor) 
    {
        this.bookAuthor = bookAuthor;
    }

    public String getBookAuthor() 
    {
        return bookAuthor;
    }

    public void setBookImage(String bookImage) 
    {
        this.bookImage = bookImage;
    }

    public String getBookImage() 
    {
        return bookImage;
    }

    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bookId", getBookId())
            .append("bookName", getBookName())
            .append("bookAuthor", getBookAuthor())
            .append("bookImage", getBookImage())
            .append("typeId", getTypeId())
            .toString();
    }
}
