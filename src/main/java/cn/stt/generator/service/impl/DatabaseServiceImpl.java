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
    @Override
    public boolean canConnect(ConnDto connDto) {
        ConnParam connParam = TransferUtil.transfer(connDto, ConnParam.class);
        DatabaseDao dao = DatabaseDaoFactory.getDAO(connParam);
        if (dao == null) {
            return false;
        }
        try {
            dao.openConnection();
            System.out.println("数据库连接成功!");
            return true;
        } catch (Exception e) {
            System.out.println("数据库连接失败,请检查端口号、用户名或密码 !");
        } finally {
            try {
                dao.closeConnection();
            } catch (DaoException e) {

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
            DatabaseDao dao = DatabaseDaoFactory.getDAO(connParam);
            long start = System.currentTimeMillis();
            dao.openConnection();
            tables = dao.getTables();
            dao.closeConnection();
            long end = System.currentTimeMillis();
            System.out.println("反向获取数据库表信息耗时：" + (end - start) + "毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tables;
    }

    @Override
    public List<Column> getColumns(ConnParam connParam, String tableName) {
        List<Column> columns = new ArrayList<Column>();
        if (connParam == null) {
            return columns;
        }
        try {
            DatabaseDao dao = DatabaseDaoFactory.getDAO(connParam);
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
        List<PrimaryKey> primaryKeys = new ArrayList<PrimaryKey>();
        if (connParam == null) {
            return primaryKeys;
        }
        try {
            DatabaseDao dao = DatabaseDaoFactory.getDAO(connParam);
            dao.openConnection();
            primaryKeys = dao.getPrimaryKeys(tableName);
            dao.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return primaryKeys;
    }
}
