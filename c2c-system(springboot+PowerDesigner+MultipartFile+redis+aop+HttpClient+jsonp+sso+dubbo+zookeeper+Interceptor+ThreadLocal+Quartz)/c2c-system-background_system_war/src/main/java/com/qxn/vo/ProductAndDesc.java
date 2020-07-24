package com.qxn.vo;

import com.qxn.pojo.Product;
import com.qxn.pojo.ProductDesc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductAndDesc {
    private Product product;
    private ProductDesc productDesc;
}
