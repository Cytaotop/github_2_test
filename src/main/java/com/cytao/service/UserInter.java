package com.cytao.service;/*
    @时间 2023/9/11
    @用户 Litao
    
*/

import com.cytao.entity.User;

public interface UserInter {
    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    boolean login(String username,String password);

    /**
     * 注册
     * @param user
     * @return
     */
    boolean newUser(User user);

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    boolean selectUserCheckName(String username);

    /**
     * 更新密码
     *
     * @return
     */
    boolean upUserPasswd(String username,String password,String newpassword);
}
