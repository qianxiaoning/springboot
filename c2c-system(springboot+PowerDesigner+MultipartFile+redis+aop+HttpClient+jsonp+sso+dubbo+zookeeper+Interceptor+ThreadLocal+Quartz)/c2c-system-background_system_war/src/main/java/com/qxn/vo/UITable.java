package com.qxn.vo;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class UITable {
    //数据转化json时，调用属性的get方法
    //getTotal() get去掉 首字母小写 生成key
    //value：利用get方法获取值

    //json转对象，调用对象的set方法
    private Integer total;
    private List<?> rows;
}
