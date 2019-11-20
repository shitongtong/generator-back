package cn.stt.generator.service;

import cn.stt.generator.dao.ConnParam;
import cn.stt.generator.dto.ConnDto;
import cn.stt.generator.entity.Column;
import cn.stt.generator.entity.PrimaryKey;
import cn.stt.generator.entity.Table;

import java.util.List;

/**
 * @ClassName DatabaseService
 * @Description 数据库元信息查询服务
 * @Author shitt7
 * @Date 2019/11/19 16:58
 * @Version 1.0
 */
public interface DatabaseService {
    /**
     * 测试数据库是否可以连接
     *
     * @param connDto
     * @return
     */
    boolean canConnect(ConnDto connDto);

    /**
     * 查询表集合
     *
     * @param connDto
     * @return
     */
    List<Table> getTables(ConnDto connDto);

    /**
     * 查询表的字段集
     *
     * @param connParam 连接参数
     * @param tableName
     * @return
     */
    List<Column> getColumns(ConnParam connParam, String tableName);

    /**
     * 查询主键集
     *
     * @param connParam 连接参数
     * @param tableName
     * @return
     */
    List<PrimaryKey> getPrimaryKeys(ConnParam connParam, String tableName);
}
