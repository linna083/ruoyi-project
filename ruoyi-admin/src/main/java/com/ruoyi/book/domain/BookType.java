package com.ruoyi.book.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 图书类型对象 book_type
 * 
 * @author linna
 * @date 2026-04-11
 */
public class BookType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图书类型ID */
    private Long typeId;

    /** 图书类型名称 */
    @Excel(name = "图书类型名称")
    private String typeName;

    /** 图书信息信息 */
    private List<Book> bookList;

    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }

    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }

    public List<Book> getBookList()
    {
        return bookList;
    }

    public void setBookList(List<Book> bookList)
    {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("typeId", getTypeId())
            .append("typeName", getTypeName())
            .append("bookList", getBookList())
            .toString();
    }
}
