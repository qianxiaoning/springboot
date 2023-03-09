package com.company.springbootquickstart01.springBootTest.cal;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 版权：(C) 版权所有 2000-2021 上海天好电子商务股份有限公司苏州分公司
 * <简述>
 * <详细描述> Add
 *
 * @author qianxiaoning
 * @version V1.0
 * @see
 * @since
 */
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
