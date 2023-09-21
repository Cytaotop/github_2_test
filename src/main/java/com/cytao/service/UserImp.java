package com.cytao.service;/*
    @时间 2023/9/11
    @用户 Litao
    
*/

import com.cytao.entity.User;
import com.cytao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserImp implements UserInter {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(String username, String password) {
        int selectlogin = userMapper.selectlogin(username, password);
        if(selectlogin>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean newUser(User user) {
        if(!("".equals(user.getName())&&"".equals(user.getPwd()))){
            user.setDelFlg("0");
            userMapper.insertSelective(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean selectUserCheckName(String username) {
        int i = userMapper.selectUserCheckName(username);
        if(i>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean upUserPasswd(String username, String password, String newpassword) {
        int i = userMapper.upUserPwd(username, password, newpassword);
        if(i>0){
            return true;
        }else {
            return false;
        }
    }


}
