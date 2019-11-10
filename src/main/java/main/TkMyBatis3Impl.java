package main;

import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;
import org.mybatis.generator.internal.util.StringUtility;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import tk.mybatis.mapper.util.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Descriprion: TODO
 * @Author: zhoutao
 * @Date: 2019/11/10
 **/
public class TkMyBatis3Impl extends IntrospectedTableMyBatis3Impl {

    /**
     * 自定义mapper后缀
     */
    @Override
    protected void calculateJavaClientAttributes() {
        if (this.context.getJavaClientGeneratorConfiguration() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.calculateJavaClientImplementationPackage());
            sb.append('.');
            sb.append(this.fullyQualifiedTable.getDomainObjectName());
            sb.append("DAOImpl");
            this.setDAOImplementationType(sb.toString());
            sb.setLength(0);
            sb.append(this.calculateJavaClientInterfacePackage());
            sb.append('.');
            sb.append(this.fullyQualifiedTable.getDomainObjectName());
            sb.append("DAO");
            this.setDAOInterfaceType(sb.toString());
            sb.setLength(0);
            sb.append(this.calculateJavaClientInterfacePackage());
            sb.append('.');
            String mapperSuffix = PropertiesUtil.getProperties("mapperSuffix");
            if (StringUtility.stringHasValue(this.tableConfiguration.getMapperName())) {
                sb.append(this.tableConfiguration.getMapperName());
            } else if(StringUtil.isNotEmpty(mapperSuffix)){
                sb.append(this.fullyQualifiedTable.getDomainObjectName());
                sb.append(mapperSuffix);
            } else {
                sb.append(this.fullyQualifiedTable.getDomainObjectName());
                sb.append("Mapper");
            }

            this.setMyBatis3JavaMapperType(sb.toString());
            sb.setLength(0);
            sb.append(this.calculateJavaClientInterfacePackage());
            sb.append('.');
            if (StringUtility.stringHasValue(this.tableConfiguration.getSqlProviderName())) {
                sb.append(this.tableConfiguration.getSqlProviderName());
            } else {
                sb.append(this.fullyQualifiedTable.getDomainObjectName());
                sb.append("SqlProvider");
            }

            this.setMyBatis3SqlProviderType(sb.toString());
        }
    }

    @Override
    protected String calculateMyBatis3XmlMapperFileName() {
        StringBuilder sb = new StringBuilder();
        String xmlSuffix = PropertiesUtil.getProperties("xmlSuffix");
        if (StringUtility.stringHasValue(this.tableConfiguration.getMapperName())) {
            String mapperName = this.tableConfiguration.getMapperName();
            int ind = mapperName.lastIndexOf(46);
            if (ind == -1) {
                sb.append(mapperName);
            } else {
                sb.append(mapperName.substring(ind + 1));
            }

            sb.append(".xml");
        } else if(StringUtil.isNotEmpty(xmlSuffix)){
            sb.append(this.fullyQualifiedTable.getDomainObjectName());
            sb.append(xmlSuffix);
            sb.append(".xml");
        } else {
            sb.append(this.fullyQualifiedTable.getDomainObjectName());
            sb.append("Mapper.xml");
        }

        return sb.toString();
    }
}
