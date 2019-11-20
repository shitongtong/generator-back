package cn.stt.generator.dao.impl;

import cn.stt.generator.dao.ConnParam;
import cn.stt.generator.enums.DatabaseTypeEnum;
import cn.stt.generator.exception.DaoException;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MySql5DatabaseDaoImpl
 * @Description MySQL数据库元信息查询
 * @Author shitt7
 * @Date 2019/11/20 9:00
 * @Version 1.0
 */
public class MySql5DatabaseDaoImpl extends CommonDatabaseDaoImpl {

    public MySql5DatabaseDaoImpl(ConnParam connParam, DatabaseTypeEnum dbType) {
        super(connParam, dbType);
    }

    @Override
    protected String getQuerySql(String sqlKey) throws DaoException {
        return super.getQuerySql(sqlKey);
    }

    @Override
    public List<Map<String, String>> query(String sql, String[] params) throws DaoException {
        String[] realParams;
        if (params == null) {
            realParams = new String[]{connParam.getDbName()};
        } else {
            realParams = new String[params.length + 1];
            realParams[0] = connParam.getDbName();
            for (int i = 0; i < params.length; i++) {
                realParams[i + 1] = params[i];
            }
        }
        return super.query(sql, realParams);
    }
}
