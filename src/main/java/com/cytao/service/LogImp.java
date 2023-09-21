package com.cytao.service;/*
    @时间 2023/9/19
    @用户 Litao
    
*/

import com.cytao.entity.Log;
import com.cytao.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogImp implements LogInter{

    @Autowired
    private LogMapper logMapper;

    @Override
    public void serviceLog(Log log) {
        logMapper.insert(log);
    }
}
