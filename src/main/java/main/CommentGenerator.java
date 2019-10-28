package main;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.List;
import java.util.Properties;

/**
 * @ Author : zhangshengqiang
 * @ Date   : 2019/5/23 17:36
 **/
public class CommentGenerator extends DefaultCommentGenerator {

    private boolean addRemarkComments = false;
    private static final String EXAMPLE_SUFFIX = "Example";
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
        if(addRemarkComments&&StringUtility.stringHasValue(remarks)){
            addFieldJavaDoc(field, remarks);
            //数据库中特殊字符需要转义
            if(remarks.contains("\"")){
                remarks=remarks.replace("\"","'");
            }
            //给model的字段添加swagger注解
            //field.addJavaDocLine("@ApiModelProperty(value = \"" + remarks + "\" ,example = \"\")");
            //给model的字段添加swagger注解
            field.addJavaDocLine("@ApiModelProperty(value = \"" + remarks + "\" )");
        }

        List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
        for (IntrospectedColumn col : primaryKeyColumns) {
            if (col.getActualColumnName().equals(introspectedColumn.getActualColumnName())) {
                field.addAnnotation("@Id");
            }
        }
        field.addAnnotation("@Column(name = \"" + introspectedColumn.getActualColumnName() + "\")");
//        super.addFieldComment(field, introspectedTable, introspectedColumn);
    }

    /**
     * 给model的字段添加注释 例://订单id
     */
    private void addFieldJavaDoc(Field field, String remarks) {
//        String[] remarkLines=remarks.split(System.getProperty("line.separator"));//换行
//        field.addJavaDocLine("//"+remarkLines[0]);

//        field.addJavaDocLine("/**");
//        String[] remarkLines=remarks.split(System.getProperty("line.separator"));//换行
//        field.addJavaDocLine(" * "+remarkLines[0]);
//        field.addJavaDocLine(" */");
    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        super.addJavaFileComment(compilationUnit);
        if(compilationUnit.isJavaInterface()&&compilationUnit.
                getType().getFullyQualifiedName().contains(EXAMPLE_SUFFIX)){
            compilationUnit.addImportedType(new FullyQualifiedJavaType(API_MODEL_PROPERTY_FULL_CLASS_NAME));
        }
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("javax.persistence.Column");
        topLevelClass.addImportedType("javax.persistence.Id");
        topLevelClass.addImportedType("javax.persistence.Table");
//        topLevelClass.addImportedType("org.apache.ibatis.type.Alias");
        String remarks=introspectedTable.getRemarks();
        if(addRemarkComments&&StringUtility.stringHasValue(remarks)){
            //数据库中特殊字符需要转义
            if(remarks.contains("\"")){
                remarks=remarks.replace("\"","'");
            }
            //给类添加swagger注解
            topLevelClass.addJavaDocLine("@ApiModel( description = \"" + remarks + "\")");
        }
        topLevelClass.addAnnotation("@Table(name = \"" + introspectedTable.getFullyQualifiedTable() + "\")");
    }

}
