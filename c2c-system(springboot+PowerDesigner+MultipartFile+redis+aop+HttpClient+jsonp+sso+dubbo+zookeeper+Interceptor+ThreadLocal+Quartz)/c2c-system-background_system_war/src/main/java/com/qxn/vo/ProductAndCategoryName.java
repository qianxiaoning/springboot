package com.qxn.vo;

import com.qxn.pojo.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class ProductAndCategoryName extends Product{
    private String categoryName;
}
