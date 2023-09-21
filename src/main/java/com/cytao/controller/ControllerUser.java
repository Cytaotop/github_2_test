package com.cytao.controller;/*
    @时间 2023/9/11
    @用户 Litao
    
*/

import com.cytao.entity.User;
import com.cytao.service.UserInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.stream.Stream;

@RestController
@RequestMapping("user")
public class ControllerUser {
    @Autowired
    private UserInter userInter;

    @GetMapping("login")
    public String login(String username, String password,HttpServletRequest request){
        boolean login = userInter.login(username, password);
        HttpSession session=request.getSession(true);
        if (login) {
            session.setAttribute("loginUsername",username);
            return "success";
        }else {
            return "false";
        }
    }
    @PostMapping("newuser")
    public boolean newUser(User user){
        if(user.getName()!=null&&user.getPwd()!=null){
            boolean b = userInter.newUser(user);
            return true;
        }
        return false;
    }

    @GetMapping("checkuser")
    public String checkuser(String username){
        boolean b = userInter.selectUserCheckName(username);
        if(b){
            return "success";
        }else {
            return "error";
        }
    }

    @PostMapping("upuserpwd")
    public boolean upUserPwd(String username,String password,String newpassword){
        boolean b = userInter.upUserPasswd(username,password,newpassword);
        return b;
    }

}
