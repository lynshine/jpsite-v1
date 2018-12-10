package com.mty.jpsite.server.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jiangpeng on 2018/11/29.
 */
public class MysqlGenerator {

    /**
     * 包名
     */
    private static final String PACKAGE_NAME = "com.mty.jpsite.server";
    /**
     * 模块名称
     */
    private static final String MODULE_NAME = "";
    /**
     * 代码生成者
     */
    private static final String AUTHOR = "jiangpeng";

    /**
     * JDBC相关配置
     */
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/jp_admin?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String SOURCE_PATH = File.separator + "src" + File.separator + "main" + File.separator + "java";
    private static final String XML_PATH = File.separator + "src" + File.separator + "main" + File.separator + "resources";
    private static final String OUT_PATH = System.getProperty("user.dir");

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
        start("hi_user_vip");
        start("hi_course_schedule");
        start("hi_reservation_log");
        start("hi_course");
    }

    public static void start(String tableName) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<TableFill>();
        /*如 每张表都有一个创建时间、修改时间
        而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
        修改时，修改时间会修改，
        虽然像Mysql数据库有自动更新几只，但像ORACLE的数据库就没有了，
        使用公共字段填充功能，就可以实现，自动按场景更新了。
        如下是配置*/
        TableFill createField = new TableFill("gmt_create_at", FieldFill.INSERT);
        TableFill updateField = new TableFill("gmt_update_at", FieldFill.INSERT_UPDATE);
        tableFillList.add(createField);
        tableFillList.add(updateField);

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig().setOutputDir(OUT_PATH + SOURCE_PATH)// 输出目录
                .setFileOverride(true)// 是否覆盖文件 默认值：false
                .setOpen(false)  //是否打开输出目录  默认值：true
                .setSwagger2(true)  //开启 swagger2 模式
                .setActiveRecord(true)// 开启 activeRecord 模式
                .setEnableCache(true)// 是否在xml中添加二级缓存配置  默认值：`false
                .setBaseResultMap(true)// XML ResultMap
                .setBaseColumnList(true)// XML columList
                .setAuthor(AUTHOR)
                .setEntityName("%s")
                .setMapperName("%sDao")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
                .setXmlName("%s-mapper");

        // 数据源配置
        DataSourceConfig dataSource = new DataSourceConfig().setDbType(DbType.MYSQL)
                .setDriverName(DRIVER)
                .setUsername(USER_NAME)
                .setPassword(PASSWORD)
                .setUrl(URL);


        String subTableName = tableName;
        // 包配置
        if (tableName != null && !"".equals(tableName)) {
            subTableName = "." + tableName;
        }
        PackageConfig packageConfig = new PackageConfig()
                .setParent(PACKAGE_NAME)//  父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
                .setModuleName(MODULE_NAME) //父包模块名
                .setEntity("entity" + camel(subTableName))
                .setService("service" + camel(subTableName))
                .setServiceImpl("serviceImpl" + camel(subTableName))
                .setController("controller" + camel(subTableName))
                .setXml("xml" + camel(subTableName))
                .setMapper("dao" + camel(subTableName));

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        // 调整 xml 生成目录演示
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return OUT_PATH + XML_PATH + "/mappers/" + camel(tableInfo.getName()) + "/" + tableInfo.getEntityName() + "-Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setTableFillList(tableFillList)   //表填充字段
                .setEntityColumnConstant(true) //【实体】是否生成字段常量（默认 false）
                .setEntityBuilderModel(true) //【实体】是否为构建者模型（默认 false）
                .setEntityLombokModel(true)  //【实体】是否为lombok模型（默认 false）
                .setEntityBooleanColumnRemoveIsPrefix(true) //Boolean类型字段是否移除is前缀（默认 false）
                .setRestControllerStyle(true); //生成 @RestController 控制器
        if (tableName != null && !"".equals(tableName)) {
            strategy.setInclude(tableName);  //需要包含的表名，允许正则表达式（与exclude二选一配置）
        }

        mpg.setGlobalConfig(globalConfig).setDataSource(dataSource)
                .setStrategy(strategy)
                .setPackageInfo(packageConfig)
                .setCfg(cfg)
//                .setTemplateEngine(new FreemarkerTemplateEngine())  // 切换为 freemarker 模板引擎, 默认velocity 模板引擎
                .setTemplate(new TemplateConfig().setXml(null)); // 关闭默认 xml 生成，调整生成至根目录

        // 执行生成
        mpg.execute();
    }

    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static StringBuffer camel(String str) {
        //利用正则删除下划线，把下划线后一位改成大写
        Pattern pattern = Pattern.compile("_(\\w)");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if (matcher.find()) {
            sb = new StringBuffer();
            //将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
            //正则之前的字符和被替换的字符
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            //把之后的也添加到StringBuffer对象里
            matcher.appendTail(sb);
        } else {
            return sb;
        }
        return camel(sb.toString());
    }
}