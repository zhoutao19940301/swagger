package mapper;

import model.template;

public interface templateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(template record);

    int insertSelective(template record);

    template selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(template record);

    int updateByPrimaryKey(template record);
}