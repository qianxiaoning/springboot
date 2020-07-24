package com.qxn.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UITree {
    private Long id;//节点id
    private String text;//节点名称
    private String state;//节点状态open/closed
}
