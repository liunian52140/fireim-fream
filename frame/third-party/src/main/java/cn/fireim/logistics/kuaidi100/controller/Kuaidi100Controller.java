package cn.fireim.logistics.kuaidi100.controller;

import cn.fireim.logistics.kuaidi100.enitity.KuaidiDetail;
import cn.fireim.logistics.kuaidi100.service.Kuaidi100Service;
import cn.fireim.logistics.kuaidi100.service.impl.Kuaidi100ServiceImpl;
import com.alibaba.fastjson.JSON;

import java.util.List;

public class Kuaidi100Controller {
    /**
     * 获取快递信息
     * @param orderNum
     * @return
     */
    public static List<KuaidiDetail> getLogisticsInfo(String orderNum){
        Kuaidi100Service ksService = new Kuaidi100ServiceImpl();
        String comCode =ksService.getKuaidiCompanyCode(orderNum);
        return ksService.getDetailInfo(orderNum,comCode);
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(getLogisticsInfo("75118017140274")));
    }
}
