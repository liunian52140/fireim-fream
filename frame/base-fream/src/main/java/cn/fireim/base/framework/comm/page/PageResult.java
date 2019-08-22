package cn.fireim.base.framework.comm.page;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    /*serialVersionUID*/
    private static final long serialVersionUID = 1L;

    private List<T> dataList;//数据

    private int pageNo;//当前页

    private int pageSize;//条数

    private long total;//总条数

    private int pages;//总页面数目

    public static <T> PageResult<T> toPageResult(List<T> datas, PageResult<T> result) {
        //超限返0
        Page<T> page = (Page<T>) datas;
        if (result.getPageNo() > page.getPages()) {
            return result;
        };
        if (datas instanceof Page) {
            result.setPageNo(page.getPageNum());
            result.setPageSize(page.getPageSize());
            result.setDataList(page.getResult());
            result.setTotal(page.getTotal());
            result.setPages(page.getPages());
        } else {
            result.setPageNo(1);
            result.setPageSize(datas.size());
            result.setDataList(datas);
            result.setTotal(datas.size());
        }

        return result;
    }
}
