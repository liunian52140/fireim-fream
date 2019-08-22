package cn.fireim.base.framework.api.controller;

import cn.fireim.base.framework.api.enitity.admin.Admin;
import cn.fireim.base.framework.api.enitity.admin.AdminReqVO;
import cn.fireim.base.framework.api.enitity.admin.AdminResVO;
import cn.fireim.base.framework.api.enitity.admin.AdminSession;
import cn.fireim.base.framework.api.service.AdminUserService;
import cn.fireim.base.framework.comm.annotation.NoAuthorization;
import cn.fireim.base.framework.comm.caches.CacheFinal;
import cn.fireim.base.framework.comm.caches.RedisHandle;
import cn.fireim.base.framework.comm.conf.WebMvcConf;
import cn.fireim.base.framework.comm.constant.BaseEnum;
import cn.fireim.base.framework.comm.constant.Result;
import cn.fireim.util.EncryptUtil;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/admin")
public class AdminUserController{

    @Autowired
    private AdminUserService adminUserServiceImpl;

    @Autowired
    private WebMvcConf webMvcConf;

    @Autowired
    private  RedisHandle redisHandle;

    @NoAuthorization
    @RequestMapping("/login")
    @ApiOperation(value="登陆接口", notes="传入用户名密码登陆",produces = "application/json")
    Result<AdminResVO> login(AdminReqVO adminReqVO, HttpServletResponse response){
        Result<AdminResVO> res = new Result<>();
        AdminResVO adminResVO = new AdminResVO();
        Admin admin = adminUserServiceImpl.selectAdminByUserName(adminReqVO);
        if(admin == null){
            res.setResult(BaseEnum.ACCOUNTS_NOT_FOND);
            return res;
        }
        if(admin.getStatus().equals("2")){
            res.setResult(BaseEnum.ACCOUNTS_FROZEN);
            return res;
        }

        if(admin.getPassWord().equals(EncryptUtil.sha256Aes(adminReqVO.getPassWord()+admin.getOffsetValue()))){
            //存储tokene.,,
           String token = RandomStringUtils.randomAlphabetic(webMvcConf.getFrameBaseProperties().getTokenLength());
            adminResVO.setAdminSession(new AdminSession(admin));
            redisHandle.setKey(CacheFinal.USER_AUTHORIZATION_TOKEN+token,webMvcConf.getFrameBaseProperties().getCachesTimeOut()*60, JSON.toJSONString(adminResVO));
            response.setHeader(CacheFinal.TOKEN_HEADER,token);
            res.setResult(adminResVO,BaseEnum.SUCCESS);
        }
        return res;
    }


}
