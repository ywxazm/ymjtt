package com.ymjtt.test.service.impl;

import com.ymjtt.test.mapper.CurrTimeMapper;
import com.ymjtt.test.service.CurrTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrTimeServiceImpl implements CurrTimeService {

    @Autowired
    private CurrTimeMapper currTimeMapper;

    @Override
    public String getCurrTime() {
        return currTimeMapper.getCurrTime();
    }
}
