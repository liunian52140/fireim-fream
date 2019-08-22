package cn.fireim.base.framework.api.service.impl;

import cn.fireim.base.framework.api.dao.LogMapper;
import cn.fireim.base.framework.api.enitity.log.Log;
import cn.fireim.base.framework.api.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public void saveLog(Log log) {
        logMapper.saveLog(log);
    }
}
