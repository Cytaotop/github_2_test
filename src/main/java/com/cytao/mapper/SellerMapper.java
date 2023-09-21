package com.cytao.mapper;

import com.cytao.entity.Recomseller;
import com.cytao.entity.Seller;
import com.cytao.pojo.District;
import com.cytao.pojo.SelectBusiness;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SellerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Seller record);

    int insertSelective(Seller record);

    Seller selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);

    List<Seller> queryShop(@Param("shop")SelectBusiness business);

    List<District> getdistrict();

    int delsellers(@Param("id") String [] id);

    int delseller(@Param("id") String id);

    List<District> selectdistrcitShopCount(String[] district);

//  添加推荐的商家
    int insertrecomseller(List<Recomseller> recomsellers);

//  查询推荐的商家
    List<Recomseller> selectComSeller();

//  查询要被选择推荐的商家信息
    List<Recomseller> selectSellerByPrimaryKey(String[] id);

//  在新添加推荐商家之前删除所有商家
    int delAllRecomseller();

}