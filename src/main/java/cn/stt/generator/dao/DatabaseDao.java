package cn.stt.generator.dao;

import cn.stt.generator.entity.Column;
import cn.stt.generator.entity.ForeignKey;
import cn.stt.generator.entity.Index;
import cn.stt.generator.entity.PrimaryKey;
import cn.stt.generator.entity.Table;
import cn.stt.generator.entity.Trigger;
import cn.stt.generator.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DatabaseDao
 * @Description 数据库元信息查询接口
 * @Author shitt7
 * @Date 2019/11/20 8:51
 * @Version 1.0
 */
public interface DatabaseDao {
    /**
     * 通用查询方法
     *
     * @param sql    要查询的sql语句
     * @param params 查询条件数组
     * @return
     * @throws DaoException
     */
    List<Map<String, String>> query(String sql, String[] params) throws DaoException;

    /**
     * 查询表集合
     *
     * @return
     * @throws DaoException
     */
    List<Table> getTables() throws DaoException;

    /**
     * 查询表的字段集
     *
     * @param tableName
     * @return
     * @throws DaoException
     */
    List<Column> getColumns(String tableName) throws DaoException;

    /**
     * 查询主键集
     *
     * @param tableName
     * @return
     * @throws DaoException
     */
    List<PrimaryKey> getPrimaryKeys(String tableName) throws DaoException;

    /**
     * 查询外键集
     *
     * @param tableName
     * @return
     * @throws DaoException
     */
    List<ForeignKey> getForeignKeys(String tableName) throws DaoException;

    /**
     * 查询索引集
     *
     * @return
     * @throws DaoException
     */
    List<Index> getIndexes(String tableName) throws DaoException;

    /**
     * 查询触发器集
     *
     * @param tableName
     * @return
     * @throws DaoException
     */
    List<Trigger> getTriggers(String tableName) throws DaoException;

    /**
     * 打开数据库连接
     */
    Connection openConnection() throws DaoException;

    /**
     * 关闭数据库连接
     */
    void closeConnection() throws DaoException;
}
