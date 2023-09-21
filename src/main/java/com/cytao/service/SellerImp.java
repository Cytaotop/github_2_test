package com.cytao.service;/*
    @时间 2023/9/12
    @用户 Litao
    
*/

import com.cytao.entity.Recomseller;
import com.cytao.entity.Seller;
import com.cytao.mapper.SellerMapper;
import com.cytao.pojo.District;
import com.cytao.pojo.SelectBusiness;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jnlp.ServiceManager;
import java.util.List;

@Service
public class SellerImp implements SellerInter {
    @Autowired
    private SellerMapper sellerMapper;

    @Override
    public PageInfo<Seller> getpage(SelectBusiness business, int page, int size) {
        PageHelper.startPage(page, size);
        List<Seller> sellers = sellerMapper.queryShop(business);
        PageInfo<Seller> pageInfo = new PageInfo<>(sellers);
        return pageInfo;
    }

    @Override
    public List<Seller> getSeller(SelectBusiness business) {
        List<Seller> sellers = sellerMapper.queryShop(business);
        return sellers;
    }

    @Override
    public List<District> getdistrict() {
        List<District> getdistrict = sellerMapper.getdistrict();
        return getdistrict;
    }

    @Override
    public boolean delsellers(String[] id) {
        int i = sellerMapper.delsellers(id);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delseller(String id) {
        int i = sellerMapper.delseller(id);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean upseller(Seller seller) {
        int i = sellerMapper.updateByPrimaryKeySelective(seller);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addseller(Seller seller) {
        int i = sellerMapper.insertSelective(seller);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<District> selectdistrcitShopCount(String[] district) {
        List<District> districts = sellerMapper.selectdistrcitShopCount(district);
        return districts;
    }


    @Override
    public boolean getSelctRecommendShop(String[] id) {
        int i=0;
        if(id == null||id.length==0){
            sellerMapper.delAllRecomseller();
            return true;
        }else {
            sellerMapper.delAllRecomseller();
            i = sellerMapper.insertrecomseller(sellerMapper.selectSellerByPrimaryKey(id));
        }
        if (i > 0) {
            return true;
        }
        return false;


    }

    @Override
    public List<Recomseller> getAllRecomseller() {
        List<Recomseller> recomsellers = sellerMapper.selectComSeller();
        return recomsellers;
    }


}
