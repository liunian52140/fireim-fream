package cn.fireim.base.framework.api.service.impl;

import cn.fireim.base.framework.api.dao.AdminUserMapper;
import cn.fireim.base.framework.api.enitity.admin.Admin;
import cn.fireim.base.framework.api.enitity.admin.AdminReqVO;
import cn.fireim.base.framework.api.service.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public Admin selectAdminByUserName(AdminReqVO adminReqVO) {
        return adminUserMapper.selectAdminByUserName(adminReqVO);
    }
}
