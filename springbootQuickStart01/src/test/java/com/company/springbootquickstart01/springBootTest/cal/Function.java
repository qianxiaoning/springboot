package com.company.springbootquickstart01.springBootTest.cal;

import java.math.BigDecimal;
import java.util.Map;

public interface Function {

    String getCode();

    BigDecimal calc(Map<String, Object> params);

}
