package com.ruoyi.book.controller;

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
import com.ruoyi.book.domain.Book;
import com.ruoyi.book.service.IBookService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 图书信息Controller
 * 
 * @author linna
 * @date 2026-03-24
 */
@Controller
@RequestMapping("/book/book")
public class BookController extends BaseController
{
    private String prefix = "book/book";

    @Autowired
    private IBookService bookService;

    @RequiresPermissions("book:book:view")
    @GetMapping()
    public String book()
    {
        return prefix + "/book";
    }

    /**
     * 查询图书信息列表
     */
    @RequiresPermissions("book:book:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Book book)
    {
        startPage();
        List<Book> list = bookService.selectBookList(book);
        return getDataTable(list);
    }

    /**
     * 导出图书信息列表
     */
    @RequiresPermissions("book:book:export")
    @Log(title = "图书信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Book book)
    {
        List<Book> list = bookService.selectBookList(book);
        ExcelUtil<Book> util = new ExcelUtil<Book>(Book.class);
        return util.exportExcel(list, "图书信息数据");
    }

    /**
     * 新增图书信息
     */
    @RequiresPermissions("book:book:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存图书信息
     */
    @RequiresPermissions("book:book:add")
    @Log(title = "图书信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Book book)
    {
        return toAjax(bookService.insertBook(book));
    }

    /**
     * 修改图书信息
     */
    @RequiresPermissions("book:book:edit")
    @GetMapping("/edit/{bookId}")
    public String edit(@PathVariable("bookId") Long bookId, ModelMap mmap)
    {
        Book book = bookService.selectBookByBookId(bookId);
        mmap.put("book", book);
        return prefix + "/edit";
    }

    /**
     * 修改保存图书信息
     */
    @RequiresPermissions("book:book:edit")
    @Log(title = "图书信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Book book)
    {
        return toAjax(bookService.updateBook(book));
    }

    /**
     * 删除图书信息
     */
    @RequiresPermissions("book:book:remove")
    @Log(title = "图书信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bookService.deleteBookByBookIds(ids));
    }
}
