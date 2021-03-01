package com.company.springbootquickstart01.libs.mybatisPlus;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;

// 执行 main 方法生成代码
public class MybatisPlusCodeGenerator {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/springboot_quickstart01?serverTimezone=GMT%2B8&useUnicode=true&useSSL=false&characterEncoding=utf8";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_ACCOUNT = "root";
    private static final String DB_PASSWORD = "root";
    private static final String PACKAGE_NAME = "com.company.springbootquickstart01.codes";
    private static String authorName = "Shon Qian";
    //输入需要生成的表名，多张逗号隔开
    private static String tableNames = "file";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(authorName);
        //生成后不打开资源管理器
        gc.setOpen(false);
        //开启 swagger2 模式，添加 Swagger2 注解
        gc.setSwagger2(true);
        //去掉service接口的I首字母
        gc.setServiceName("%sService");
        //指定生成的主键的ID类型
        gc.setIdType(IdType.ASSIGN_ID);
        //xml生成BaseResultMap和BaseColumnList
//        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DB_URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DB_DRIVER);
        dsc.setUsername(DB_ACCOUNT);
        dsc.setPassword(DB_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //不生成父包模块名
        pc.setModuleName(null);
        //pc.setModuleName(scanner("模块名"));
        //父包名
        pc.setParent(PACKAGE_NAME);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        //指定controller模板
        templateConfig.setController("velocityTemplates/controller.java");
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        //数据库表配置 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //自定义继承的Entity类全称，带包名
//        strategy.setSuperEntityClass("com.company.springbootquickstart01.codes.common.entity.BasePojo");
        //是否为lombok模型
        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        //需要包含的表名
        strategy.setInclude(tableNames.split(","));
        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //乐观锁属性名称
        strategy.setVersionFieldName("version");
        //逻辑删除属性名称
        strategy.setLogicDeleteFieldName("del_flag");
        //是否为链式模型
        strategy.setChainModel(true);
        //表前缀
        strategy.setTablePrefix(pc.getModuleName() + "_");//生成实体时去掉表前缀
        //数据库表配置
        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }
}
