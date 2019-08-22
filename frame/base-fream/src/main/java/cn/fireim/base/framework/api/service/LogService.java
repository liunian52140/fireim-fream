package cn.fireim.base.framework.api.service;

import cn.fireim.base.framework.api.enitity.log.Log;

public interface LogService {
    /**
     * 日志保存模块
     * @param log
     */
    void saveLog(Log log);
}
