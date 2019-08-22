package cn.fireim.base.framework.api.dao;

import cn.fireim.base.framework.api.enitity.admin.Admin;
import cn.fireim.base.framework.api.enitity.admin.AdminReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminUserMapper {

    @Select("select * from base_admin_user where user_name = #{adminReqVO.userName}")
    Admin selectAdminByUserName(@Param("adminReqVO") AdminReqVO adminReqVO);

}
