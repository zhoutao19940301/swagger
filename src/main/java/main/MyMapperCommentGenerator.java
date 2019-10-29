package main;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;
import tk.mybatis.mapper.generator.MapperCommentGenerator;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Properties;

/**
 * @Descriprion: 自定义generator代码注释生成类
 * @Author: zhoutao
 * @Date: 2019/10/29
 **/
public class MyMapperCommentGenerator extends MapperCommentGenerator {

    private boolean addRemarkComments = false;
    private static final String EXAMPLE_SUFFIX = "Example";
    private String beginningDelimiter = "";
    private String endingDelimiter = "";
    private static final String API_MODEL_PROPERTY_FULL_CLASS_NAME ="io.swagger.annotations.ApiModelProperty";

    /**
     * 设置用户配置的参数
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
        this.addRemarkComments= StringUtility.isTrue(properties.getProperty("addRemarkComments"));
    }

    /**
     * 给字段添加注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String remarks=introspectedColumn.getRemarks();
        field.addJavaDocLine("@ApiModelProperty(value = \"" + remarks + "\" )");
        if (field.isTransient()) {
            field.addAnnotation("@Transient");
        }
        Iterator var7 = introspectedTable.getPrimaryKeyColumns().iterator();

        while(var7.hasNext()) {
            IntrospectedColumn column = (IntrospectedColumn)var7.next();
            if (introspectedColumn == column) {
                field.addAnnotation("@Id");
                break;
            }
        }

        String column = introspectedColumn.getActualColumnName();
        if (StringUtility.stringContainsSpace(column) || introspectedTable.getTableConfiguration().isAllColumnDelimitingEnabled()) {
            column = introspectedColumn.getContext().getBeginningDelimiter() + column + introspectedColumn.getContext().getEndingDelimiter();
        }

        if (!column.equals(introspectedColumn.getJavaProperty())) {
            field.addAnnotation("@Column(name = \"" + this.getDelimiterName(column) + "\")");
        } else if (StringUtility.stringHasValue(this.beginningDelimiter) || StringUtility.stringHasValue(this.endingDelimiter)) {
            field.addAnnotation("@Column(name = \"" + this.getDelimiterName(column) + "\")");
        }

        if (introspectedColumn.isIdentity()) {
            if (introspectedTable.getTableConfiguration().getGeneratedKey().getRuntimeSqlStatement().equals("JDBC")) {
                field.addAnnotation("@GeneratedValue(generator = \"JDBC\")");
            } else {
                field.addAnnotation("@GeneratedValue(strategy = GenerationType.IDENTITY)");
            }
        } else if (introspectedColumn.isSequenceColumn()) {
            String tableName = introspectedTable.getFullyQualifiedTableNameAtRuntime();
            String sql = MessageFormat.format(introspectedTable.getTableConfiguration().getGeneratedKey().getRuntimeSqlStatement(), tableName, tableName.toUpperCase());
            field.addAnnotation("@GeneratedValue(strategy = GenerationType.IDENTITY, generator = \"" + sql + "\")");
        }
    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        super.addJavaFileComment(compilationUnit);
        if(compilationUnit.isJavaInterface()&&compilationUnit.
                getType().getFullyQualifiedName().contains(EXAMPLE_SUFFIX)){
            compilationUnit.addImportedType(new FullyQualifiedJavaType(API_MODEL_PROPERTY_FULL_CLASS_NAME));
        }
    }

    /**
     * 类上加swagger注释
     */
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String remarks=introspectedTable.getRemarks();
        topLevelClass.addJavaDocLine("@ApiModel( description = \"" + remarks + "\")");
    }

    /**
     * 去掉get方法注释
     */
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    /**
     * 去掉set方法注释
     */
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

}
