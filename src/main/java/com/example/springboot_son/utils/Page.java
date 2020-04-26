package com.example.springboot_son.utils;

import com.example.springboot_son.entity.VideoInfo;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by cengyujun on 2020/4/24 11:41 上午
 */

@ToString
@Data
public class Page<T> {
    private int pageNum;//当前页数

    private int pageSize;//页大小

    private int totalSize;//总页数

    private List list;

    public Page(PageInfo pageInfo) {
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.totalSize = pageInfo.getPages();
        this.list = pageInfo.getList();
    }

    public Page(com.github.pagehelper.PageInfo<VideoInfo> pageInfo) {
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.totalSize = pageInfo.getPages();
        this.list = pageInfo.getList();
    }
}
