package main;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import tk.mybatis.mapper.generator.MapperPlugin;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Properties;

/**
 * @Descriprion: 自定义generator代码生成器
 * @Author: zhoutao
 * @Date: 2019/10/29
 **/
public class MyMapperPlugin extends MapperPlugin {

    private CommentGeneratorConfiguration commentCfg;

    //注释生成器类
    private String commentGeneratorType;

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        //设置自定义的注释生成器
        commentGeneratorType =  this.properties.getProperty("commentGeneratorType");
        if (!StringUtil.isEmpty(commentGeneratorType)) {
            commentCfg = new CommentGeneratorConfiguration();
            commentCfg.setConfigurationType(commentGeneratorType);
            context.setCommentGeneratorConfiguration(commentCfg);
        }
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }
}
