package com.cytao.controller;/*
    @时间 2023/9/11
    @用户 Litao
    
*/

import com.cytao.entity.Recomseller;
import com.cytao.entity.Seller;
import com.cytao.entity.User;
import com.cytao.pojo.District;
import com.cytao.pojo.SelectBusiness;
import com.cytao.service.SellerInter;
import com.cytao.service.UserInter;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("seller")
public class ControllerSeller {
    @Autowired
    private SellerInter sellerInter;

    @Value("${file.uploadFolder}")
    private String path;

    /**
     * 分页显示商家
     * @param business
     * @param page
     * @param size
     * @return
     */
    @GetMapping("qureyseller")
    public PageInfo<Seller> qureyseller(SelectBusiness business, int page, int size) {
        PageInfo<Seller> getpage = sellerInter.getpage(business, page, size);
        return getpage;
    }

    /**
     * 获取全部的商家
     * @param business
     * @return
     */
    @GetMapping("getseller")
    public List<Seller> getseller (SelectBusiness business){
        return sellerInter.getSeller(business);
    }

    /**
     * 通过推荐的商家id返回商家信息
     * @param ids
     * @return
     */
    @GetMapping("recommendshop")
    public boolean recommendshop(String[] ids){
        return sellerInter.getSelctRecommendShop(ids);
    }

//  获取所有城市
    @GetMapping("getdistrict")
    public List<District> getdistrict() {
        List<District> getdistrict = sellerInter.getdistrict();
        return getdistrict;
    }

    /**
     * 删除一个商家
     * @param id
     * @return
     */
    @PostMapping("delseller")
    public boolean delseller(String id) {
        return sellerInter.delseller(id);
    }

    /**
     * 删除多个商家
     * @param id
     * @return
     */
    @PostMapping("delsellers")
    public boolean delsellers(String[] id) {
        return sellerInter.delsellers(id);
    }

    /**
     * 更新商家
     * @param file 新图片
     * @param seller 信息
     * @return
     */
    @PostMapping("upseller")
    public boolean upseller(MultipartFile file, Seller seller) {
        if (seller.getId() != null && (!"".equals(seller.getId()))) {
            addOrUpimg(file, seller);
            return sellerInter.upseller(seller);
        }
        return false;
    }

    /**
     * 新增商家
     * @param file 商家图片
     * @param seller 商家信息
     * @return
     */
    @PostMapping("addseller")
    public boolean addseller(MultipartFile file, Seller seller) {
        if (addOrUpimg(file, seller)) {
            if (seller.getId() != null && (!"".equals(seller.getId()))) {
                return sellerInter.addseller(seller);
            }
        }
        return false;
    }

    /**
     * 返回城市的商家数量
     * @param disticts 城市/地区，
     * @return
     */
    @GetMapping("districtshopcount")
    public List<District> districtshopcount(String[] disticts) {
        List<District> districts = sellerInter.selectdistrcitShopCount(disticts);
        return districts;
    }

    /**
     * 所有推荐商家的id
     * @return
     */
    @GetMapping("allrecomsellerid")
    public List<String> allrecomsellerid(){
        List<Recomseller> allRecomseller = sellerInter.getAllRecomseller();
        List<String> ids=new ArrayList<>();
        for (Recomseller recomseller : allRecomseller) {
            ids.add(recomseller.getId());
        }
        return ids;
    }

    /**
     * 所有推荐商家的图片
     * @return
     */
    @GetMapping("allrecomsellerimg")
    public List<String> allrecomseller(){
        List<Recomseller> allRecomseller = sellerInter.getAllRecomseller();
        List<String> comments=new ArrayList<>();
        for (Recomseller recomseller : allRecomseller) {
            comments.add(recomseller.getComments());
        }
        return comments;
    }

    /**
     * 用于在添加或修改图片时将图片的保存在服务器中，把商家的照片名更新一下
     * @param file 新图片
     * @param seller 商家信息
     * @return
     */
    private boolean addOrUpimg(MultipartFile file, Seller seller) {
        if (file != null) {
            String fileName = file.getOriginalFilename();
            if (fileName != null && fileName.length() > 0) {
                File dir = new File(path, fileName);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                try {
                    file.transferTo(dir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                seller.setComments(fileName);
                return true;
            }
            return false;
        }
        return false;
    }
}
