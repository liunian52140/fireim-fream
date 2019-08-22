package cn.fireim.base.framework.api.service;

import cn.fireim.base.framework.api.enitity.admin.Admin;
import cn.fireim.base.framework.api.enitity.admin.AdminReqVO;

public interface AdminUserService {
    Admin selectAdminByUserName(AdminReqVO  adminReqVO);
}
