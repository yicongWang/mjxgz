package com.zhiyi.mjxgz.common.util;

import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象扩展工具类
 * Created by DW on 2017/3/22.
 */
public class PageInfoExtUtils {


    /**
     * 返回封装好的PageInfo对象
     * @param list   分页对象数据集
     * @param pageNum   当前页数
     * @param pageSize  每页展示条数
     * @return
     */
    public static PageInfo getPageInfo(List list, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null || pageNum<=0 ? 1 : pageNum;
        pageSize = pageSize == null || pageSize<=0 ? 10 : pageSize;
        list = list == null ? new ArrayList() : list;
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setSize(pageSize);
        pageInfo.setTotal(list.size());
        int pages = 0;
        if (pageInfo.getTotal() % pageSize == 0) {
            pages = (int) pageInfo.getTotal() / pageSize;
        } else {
            pages = (int) pageInfo.getTotal() / pageSize +1;
        }
        pageInfo.setPages(pages);
        if(pageInfo.getPageNum()>pages){
            pageInfo.setPageNum(pages);
        }
        if(pageInfo.getSize() == 0) {
            pageInfo.setStartRow(0);
            pageInfo.setEndRow(0);
        } else {
            pageInfo.setStartRow(pageInfo.getStartRow() +1);
            pageInfo.setEndRow(pageInfo.getStartRow() - 1 + pageInfo.getSize());

        }
        if(list.size()>0) {
            int startIndex = (pageInfo.getPageNum() - 1) * pageSize;
            int endIndex = list.size() > startIndex + pageInfo.getSize() ? startIndex + pageInfo.getSize() : list.size();
            pageInfo.setList(list.subList(startIndex, endIndex));
        }else{
            pageInfo.setList(list);
        }
        pageInfo.setNavigatePages(8);
        calcNavigatepageNums(pageInfo);
        calcPage(pageInfo);
        judgePageBoudary(pageInfo);
        return pageInfo;
    }

    /**
     * 返回封装好的PageInfo对象==》【无数据，未设置list】
     * @param tatalRecord       总记录数
     * @param pageNum           当前页数
     * @param pageSize          每页展示条数
     * @return
     */
    public static PageInfo getPageInfo(Long tatalRecord, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null || pageNum<=0 ? 1 : pageNum;
        pageSize = pageSize == null || pageSize<=0 ? 10 : pageSize;
//        list = list == null ? new ArrayList() : list;
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setSize(pageSize);
        pageInfo.setTotal(tatalRecord);
        int pages = 0;
        if (pageInfo.getTotal() % pageSize == 0) {
            pages = (int) pageInfo.getTotal() / pageSize;
        } else {
            pages = (int) pageInfo.getTotal() / pageSize +1;
        }
        pageInfo.setPages(pages);
        if(pageInfo.getPageNum()>pages){
            pageInfo.setPageNum(pages);
        }
        if(pageInfo.getSize() == 0) {
            pageInfo.setStartRow(0);
            pageInfo.setEndRow(0);
        } else {
            pageInfo.setStartRow(pageInfo.getStartRow() +1);
            pageInfo.setEndRow(pageInfo.getStartRow() - 1 + pageInfo.getSize());

        }
        /*if(list.size()>0) {
            int startIndex = (pageInfo.getPageNum() - 1) * pageSize;
            int endIndex = list.size() > startIndex + pageInfo.getSize() ? startIndex + pageInfo.getSize() : list.size();
            pageInfo.setList(list.subList(startIndex, endIndex));
        }else{
            pageInfo.setList(list);
        }*/
        pageInfo.setNavigatePages(8);
        calcNavigatepageNums(pageInfo);
        calcPage(pageInfo);
        judgePageBoudary(pageInfo);
        return pageInfo;
    }



    private static void calcNavigatepageNums(PageInfo pageInfo) {
        int startNum;
        if(pageInfo.getPages() <= pageInfo.getNavigatePages()) {
            pageInfo.setNavigatepageNums( new int[pageInfo.getPages()]);

            for(startNum = 0; startNum < pageInfo.getPages(); ++startNum) {
                pageInfo.getNavigatepageNums()[startNum]= startNum + 1;
            }
        } else {
            pageInfo.setNavigatepageNums(new int[pageInfo.getNavigatePages()]);
            startNum = pageInfo.getPageNum() - pageInfo.getNavigatePages() / 2;
            int endNum = pageInfo.getPageNum() + pageInfo.getNavigatePages() / 2;
            int i;
            if(startNum < 1) {
                startNum = 1;
                for(i = 0; i < pageInfo.getNavigatePages(); ++i) {
                    pageInfo.getNavigatepageNums()[i] =startNum++;
                }
            } else if(endNum > pageInfo.getPages()) {
                endNum = pageInfo.getPages();

                for(i = pageInfo.getNavigatePages() - 1; i >= 0; --i) {
                    pageInfo.getNavigatepageNums()[i] = endNum--;
                }
            } else {
                for(i = 0; i < pageInfo.getNavigatePages(); ++i) {
                    pageInfo.getNavigatepageNums()[i] = startNum++;
                }
            }
        }

    }

    private static void calcPage(PageInfo pageInfo) {
        if(pageInfo.getNavigatepageNums() != null && pageInfo.getNavigatepageNums().length > 0) {
            pageInfo.setFirstPage(pageInfo.getNavigatepageNums()[0]);
            pageInfo.setLastPage(pageInfo.getNavigatepageNums()[pageInfo.getNavigatepageNums().length - 1]);
            if(pageInfo.getPageNum() > 1) {
                pageInfo.setPrePage(pageInfo.getPageNum() - 1);
            }

            if(pageInfo.getPageNum() < pageInfo.getPages()) {
                pageInfo.setNextPage(pageInfo.getPageNum() + 1) ;
            }
        }

    }

    private static void judgePageBoudary(PageInfo pageInfo) {
        pageInfo.setIsFirstPage(pageInfo.getPageNum() == 1);
        pageInfo.setIsLastPage (pageInfo.getPageNum() == pageInfo.getPages());
        pageInfo.setHasPreviousPage(pageInfo.getPageNum() > 1);
        pageInfo.setHasNextPage (pageInfo.getPageNum() < pageInfo.getPages());
    }

}
