package com.qxn.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysResult {
    private Integer code;//200成功 403前端请求错误
    private String message = "ok";//服务器返回消息
    private Object data;//服务器返回数据
    public static SysResult success() {
        return new SysResult(200,null,null);
    }
    public static SysResult success(Object object) {
        return new SysResult(200,null,object);
    }
    public static SysResult success(String msg,Object object) {
        return new SysResult(200,msg,object);
    }
    public static SysResult fail() {
        return new SysResult(403,null,null);
    }

    public static SysResult fail(String msg) {
        return new SysResult(403,msg,null);
    }
}
