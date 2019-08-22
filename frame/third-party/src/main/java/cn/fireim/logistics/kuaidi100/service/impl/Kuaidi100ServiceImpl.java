package cn.fireim.logistics.kuaidi100.service.impl;

import cn.fireim.logistics.kuaidi100.enitity.KuaidiDetail;
import cn.fireim.logistics.kuaidi100.service.Kuaidi100Service;
import cn.fireim.util.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.io.IOException;
import java.util.List;

import static cn.fireim.logistics.kuaidi100.url.UrlFinal.GET_KUAIDI_COMPANY_URL;
import static cn.fireim.logistics.kuaidi100.url.UrlFinal.GET_KUAIDI_INFO;

public class Kuaidi100ServiceImpl implements Kuaidi100Service{

    @Override
    public String getKuaidiCompanyCode(String orderNum) {
        String res=null;
        try {
             res=HttpUtil.postApi(GET_KUAIDI_COMPANY_URL.replace("ORDER_NUM",orderNum));
            JSONArray auto =JSON.parseObject(res).getJSONArray("auto");
            return auto.getJSONObject(0).getString("comCode");
        } catch (IOException e) {
            e.printStackTrace();
            res =null;
        }
        return res;
    }

    @Override
    public List<KuaidiDetail> getDetailInfo(String orderNum, String companyCode) {
        List<KuaidiDetail> kd=null;
        try {
            String res=HttpUtil.postApi(GET_KUAIDI_INFO.replace("ORDER_NUM",orderNum).replace("COMPANY",companyCode));
            kd = (List)JSON.parseObject(res).getJSONArray("data");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return kd;
    }
}
