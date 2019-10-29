package mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Descriprion: mapper通用类
 * @Author: zhoutao
 * @Date: 2019/10/29
 **/
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
