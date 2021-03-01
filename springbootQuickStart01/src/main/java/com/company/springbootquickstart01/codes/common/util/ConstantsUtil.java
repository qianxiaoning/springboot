package com.company.springbootquickstart01.codes.common.util;

//常量接口
public interface ConstantsUtil {
    String UUID_COOKIE_NAME = "user_uuid";
    String COOKIE_DOMAIN = "localhost";
    String LOCAL_DIR_PATH = "D:/qxn/file/testFileFolder/";
    String URL_DIR_PATH = "http://image.c2c-system.com/";

    //用静态枚举分模块
    //性别
    enum sex {
        MAN("1", "男"),
        FEMAN("2", "女");

        private sex(String value, String name) {
            this.value = value;
            this.name = name;
        }

        private final String value;
        private final String name;

        public String getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }
}
