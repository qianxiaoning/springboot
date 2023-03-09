package com.company.springbootquickstart01.springBootTest.cal;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 版权：(C) 版权所有 2000-2021 上海天好电子商务股份有限公司苏州分公司
 * <简述>
 * <详细描述> Function
 *
 * @author qianxiaoning
 * @version V1.0
 * @see
 * @since
 */
public interface Function {

    String getCode();

    BigDecimal calc(Map<String, Object> params);

}
