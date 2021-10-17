package team.skylinekids.commonweal.dao.core;

import java.util.List;

/**
 * dao层接口
 *
 * @param <T> 对应要处理的类型
 * @author MysticalDream
 */
public interface GenericBaseDao<T> {
    /**
     * 插入一条记录
     *
     * @param entity 实体对象
     * @return 返回影响的行数
     */
    Integer insert(T entity) throws Exception;


    /**
     * 根据entity条件,和其主键更新记录
     *
     * @param entity 实体对象
     * @return
     */
    Integer update(T entity) throws Exception;

    /**
     * 根据主键删除记录
     *
     * @param key 主键key
     * @return 返回影响行数
     */
    Integer deleteByPrimaryKey(Object key) throws Exception;

    /**
     * 根据entity条件，删除记录
     *
     * @param entity 实体对象
     * @return 返回影响的行数
     */
    Integer delete(T entity) throws Exception;


    /**
     * 根据entity条件,查询一条记录
     * 非空值作为where条件,默认用 “=“
     * 有多条记录时只取查询到的第一个记录
     *
     * @param entity 实体
     * @return
     */
    T selectOne(T entity) throws Exception;

    /**
     * 根据entity条件,查询
     *
     * @param entity
     * @return
     */
    List<T> selectList(T entity) throws Exception;

    /**
     * 根据条件查询记录
     *
     * @param conditionSql
     * @param value
     * @return
     * @throws Exception
     */
    List<T> selectListByConditionString(String conditionSql, List<?> value) throws Exception;

    /**
     * 查询全部数据
     *
     * @return
     */
    List<T> selectAll() throws Exception;

    /**
     * 通过主键字段查询
     *
     * @param key
     * @return
     */
    T selectByPrimaryKey(Object key) throws Exception;

    /**
     * 根据entity条件，查询总记录数
     *
     * @param entity
     * @return
     */
    Integer selectCount(T entity) throws Exception;

    /**
     * 根据条件查询记录数
     *
     * @param conditionSql 预编译条件查询语句
     * @param value
     * @return
     * @throws Exception
     */
    Integer selectCountByCondition(String conditionSql, List<?> value) throws Exception;

    /**
     * 查询总记录数
     *
     * @return
     */
    Integer selectAllCount() throws Exception;
}
