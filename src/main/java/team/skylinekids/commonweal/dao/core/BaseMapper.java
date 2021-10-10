package team.skylinekids.commonweal.dao.core;

import java.util.List;

/**
 * dao层接口
 *
 * @param <T> 对应要处理的类型
 * @author MysticalDream
 */
public interface BaseMapper<T> {
    /**
     * 插入一条记录
     *
     * @param entity 实体对象
     * @return 返回影响的行数
     */
    Integer insert(T entity);


    /**
     * 根据entity条件,和其主键更新记录
     *
     * @param entity 实体对象
     * @return
     */
    Integer update(T entity);

    /**
     * 根据主键删除记录
     *
     * @param key 主键key
     * @return 返回影响行数
     */
    Integer deleteByPrimaryKey(Object key);

    /**
     * 根据entity条件，删除记录
     *
     * @param entity 实体对象
     * @return 返回影响的行数
     */
    Integer delete(T entity);


    /**
     * 根据entity条件,查询一条记录
     * 非空值作为where条件,默认用 “=“
     * 有多条记录时只取查询到的第一个记录
     *
     * @param entity 实体
     * @return
     */
    T selectOne(T entity);

    /**
     * 根据entity条件,查询全部记录
     *
     * @param entity
     * @return
     */
    List<T> selectList(T entity);

    /**
     * 查询全部数据
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 通过主键字段查询
     *
     * @param key
     * @return
     */
    T selectByPrimaryKey(Object key);

    /**
     * 根据entity条件，查询总记录数
     *
     * @param entity
     * @return
     */
    Integer selectCount(T entity);

    /**
     * 查询总记录数
     *
     * @return
     */
    Integer selectAllCount();
}
