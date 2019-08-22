package cn.fireim.base.framework.api.dao;

import cn.fireim.base.framework.api.enitity.log.Log;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    /**
     * 日志保存
     * @param log
     * @return
     */
    int saveLog(Log log);
}
