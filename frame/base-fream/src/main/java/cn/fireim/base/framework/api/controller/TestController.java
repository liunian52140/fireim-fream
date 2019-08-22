package cn.fireim.base.framework.api.controller;

import cn.fireim.base.framework.comm.annotation.LogSave;
import cn.fireim.base.framework.comm.annotation.NoAuthorization;
import cn.fireim.base.framework.comm.constant.BaseEnum;
import cn.fireim.base.framework.comm.constant.Result;
import cn.fireim.logistics.kuaidi100.controller.Kuaidi100Controller;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@ApiModel(value="测试模块",description = "用于测试使用")
@RequestMapping("/api/test")
@RestController
public class TestController {

    private Logger logger= Logger.getLogger(this.getClass());

    /**
     * 测试
     * @return
     */

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息",produces = "application/json")
    @LogSave(key = "id",value="操作值",moudleName = "用户管理",handleType = "删除")
    @NoAuthorization
    @RequestMapping("/system")
    Result<String> get(){
        Result<String> res = new Result<>();
        res.setDate("my name is 999");
        res.setResult(BaseEnum.SUCCESS);
        return  res;
    }
    @ApiOperation(value="获取快递详细信息", notes="根据orderNum获取快递信息",produces = "application/json")
    @LogSave(key = "orderNum",value="快递单号",moudleName = "快递信息",handleType = "快递查询")
    @RequestMapping("/searchKuaiDi")
    Result<Object> getKuaidiInfo(String orderNum){
        Result<Object> res = new Result<>();
        try{
            res.setResult(Kuaidi100Controller.getLogisticsInfo(orderNum), BaseEnum.SUCCESS);
        }catch (Exception e){
            logger.info("查询快递出错-----快递单号为："+orderNum);
            res.setResult(BaseEnum.FAILD);
        }
        return res;
    }
}
