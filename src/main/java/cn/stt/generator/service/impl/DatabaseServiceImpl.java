package cn.stt.generator.service.impl;

import cn.stt.common.util.TransferUtil;
import cn.stt.generator.dao.ConnParam;
import cn.stt.generator.dao.DatabaseDao;
import cn.stt.generator.dao.DatabaseDaoFactory;
import cn.stt.generator.dto.ConnDto;
import cn.stt.generator.entity.Column;
import cn.stt.generator.entity.PrimaryKey;
import cn.stt.generator.entity.Table;
import cn.stt.generator.exception.DaoException;
import cn.stt.generator.service.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DatabaseServiceImpl
 * @Description 数据库元信息查询服务实现类
 * @Author shitt7
 * @Date 2019/11/19 18:20
 * @Version 1.0
 */
@Service
public class DatabaseServiceImpl implements DatabaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseServiceImpl.class);

    @Override
    public boolean canConnect(ConnDto connDto) {
        ConnParam connParam = TransferUtil.transfer(connDto, ConnParam.class);
        DatabaseDao dao = DatabaseDaoFactory.getDao(connParam);
        if (dao == null) {
            return false;
        }
        try {
            dao.openConnection();
            LOGGER.info("数据库连接成功!");
            return true;
        } catch (Exception e) {
            LOGGER.error("数据库连接失败", e);
        } finally {
            try {
                dao.closeConnection();
            } catch (DaoException e) {
                LOGGER.error("", e);
            }
        }
        return false;
    }

    @Override
    public List<Table> getTables(ConnDto connDto) {
        List<Table> tables = new ArrayList<>();
        if (connDto == null) {
            return tables;
        }
        try {
            ConnParam connParam = TransferUtil.transfer(connDto, ConnParam.class);
            DatabaseDao dao = DatabaseDaoFactory.getDao(connParam);
            long start = System.currentTimeMillis();
            dao.openConnection();
            tables = dao.getTables();
            dao.closeConnection();
            long end = System.currentTimeMillis();
            LOGGER.info("反向获取数据库表信息耗时{}ms", end - start);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return tables;
    }

    @Override
    public List<Column> getColumns(ConnParam connParam, String tableName) {
        List<Column> columns = new ArrayList<>();
        if (connParam == null) {
            return columns;
        }
        try {
            DatabaseDao dao = DatabaseDaoFactory.getDao(connParam);
            dao.openConnection();
            columns = dao.getColumns(tableName);
            dao.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columns;
    }

    @Override
    public List<PrimaryKey> getPrimaryKeys(ConnParam connParam, String tableName) {
        List<PrimaryKey> primaryKeys = new ArrayList<>();
        if (connParam == null) {
            return primaryKeys;
        }
        try {
            DatabaseDao dao = DatabaseDaoFactory.getDao(connParam);
            dao.openConnection();
            primaryKeys = dao.getPrimaryKeys(tableName);
            dao.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return primaryKeys;
    }
}
