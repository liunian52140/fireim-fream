package cn.fireim.logistics.kuaidi100.service;

import cn.fireim.logistics.kuaidi100.enitity.KuaidiDetail;

import java.util.List;

public interface Kuaidi100Service {
     String  getKuaidiCompanyCode(String orderNum);

     List<KuaidiDetail> getDetailInfo(String orderNum, String CompanyCode);
}
