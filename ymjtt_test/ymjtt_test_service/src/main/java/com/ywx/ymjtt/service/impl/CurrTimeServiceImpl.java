package com.ywx.ymjtt.service.impl;

import com.ywx.ymjtt.mapper.CurrTimeMapper;
import com.ywx.ymjtt.service.CurrTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrTimeServiceImpl implements CurrTimeService {

    @Autowired
    private CurrTimeMapper currTimeMapper;

//    @Override
    public String queryCurrTime() {
        return currTimeMapper.queryCurrTime();
    }
}
