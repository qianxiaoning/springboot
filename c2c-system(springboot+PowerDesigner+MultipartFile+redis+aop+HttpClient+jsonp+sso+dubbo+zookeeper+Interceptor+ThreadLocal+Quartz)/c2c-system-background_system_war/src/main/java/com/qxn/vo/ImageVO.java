package com.qxn.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ImageVO {
    private Integer error = 0;//0 没错，1 有错
    private String url;//图片的虚拟路径
    private Integer width;
    private Integer height;//宽高
    //失败方法
    public static ImageVO fail() {
        return new ImageVO(1,null,null,null);
    }
}
