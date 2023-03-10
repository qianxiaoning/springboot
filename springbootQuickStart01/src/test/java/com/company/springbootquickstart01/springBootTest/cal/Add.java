package com.company.springbootquickstart01.springBootTest.cal;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class Add implements Function{


    @Override
    public String getCode() {
        return "add";
    }

    @Override
    public BigDecimal calc(Map<String, Object> params) {

        return null;
    }
}
