package com.cytao.service;/*
    @时间 2023/9/11
    @用户 Litao
    
*/

import com.cytao.entity.Recomseller;
import com.cytao.entity.Seller;
import com.cytao.pojo.District;
import com.cytao.pojo.SelectBusiness;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SellerInter {

    PageInfo<Seller> getpage(SelectBusiness business,int page,int size);

    List<Seller> getSeller(SelectBusiness business);

    List<District> getdistrict();

    boolean delsellers(String[] id);

    boolean delseller(String id);

    boolean upseller(Seller seller);

    boolean addseller(Seller seller);

//  城市的商家数量
    List<District> selectdistrcitShopCount(String[] district);

    /**
     * 推荐的商家信息
     * @param id
     * @return
     */
    boolean getSelctRecommendShop(String[] id);

    /**
     * 获取全部已经推荐的商家
     * @return
     */
    List<Recomseller> getAllRecomseller();

}
