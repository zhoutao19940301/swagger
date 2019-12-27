package ${package};

import com.talkweb.edu.common.mapper.BaseService;
import ${package?substring(0,package?last_index_of('.'))}.dao.${tableClass.shortClassName}Dao;
import ${tableClass.fullClassName};
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

<#assign dateTime = .now>
/**
* @description ${tableClass.shortClassName}业务层
* @author  bigdata
* @since  ${dateTime?string["yyyy-MM-dd HH:mm:ss"]}
*/
@Service
@Slf4j
public class ${tableClass.shortClassName}Service extends BaseService<${tableClass.shortClassName}> {

    @Resource
    private ${tableClass.shortClassName}Dao ${tableClass.variableName}Dao;

}