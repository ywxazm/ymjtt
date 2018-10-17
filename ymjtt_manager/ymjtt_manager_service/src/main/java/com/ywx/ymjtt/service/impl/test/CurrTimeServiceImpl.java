package com.ywx.ymjtt.service.impl.test;

import com.ywx.ymjtt.dao.test.CurrTimeMapper;
import com.ywx.ymjtt.service.test.CurrTimeService;
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
