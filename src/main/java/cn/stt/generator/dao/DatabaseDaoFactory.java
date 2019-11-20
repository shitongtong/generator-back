package cn.stt.generator.dao;

import cn.stt.generator.constant.DBMSConstant;
import cn.stt.generator.dao.impl.CommonDatabaseDaoImpl;
import cn.stt.generator.dao.impl.MySql5DatabaseDaoImpl;
import cn.stt.generator.enums.DatabaseTypeEnum;

/**
 * @ClassName DatabaseDaoFactory
 * @Description 查询器生成工厂
 * @Author shitt7
 * @Date 2019/11/19 18:38
 * @Version 1.0
 */
public class DatabaseDaoFactory {

    public static DatabaseDao getDao(ConnParam connParam) {
        String upperCaseDbName = connParam.getDbType().toUpperCase();
        if (upperCaseDbName.contains(DBMSConstant.ORACLE)) {
            return new CommonDatabaseDaoImpl(connParam, DatabaseTypeEnum.Oracle);
        } else if (upperCaseDbName.contains(DBMSConstant.SQL_SERVER) || upperCaseDbName.contains(DBMSConstant.SQLSERVER)) {
            return new CommonDatabaseDaoImpl(connParam, DatabaseTypeEnum.MSSQLServer);
        } else if (upperCaseDbName.contains(DBMSConstant.MYSQL)) {
            return new MySql5DatabaseDaoImpl(connParam, DatabaseTypeEnum.MySql5);
//		} else if (upperCaseDbName.contains(DBMSConstants.SYBASE)) {
//
//		} else if (upperCaseDbName.contains(DBMSConstants.POSTGRE_SQL)) {
//
//		} else if (upperCaseDbName.contains(DBMSConstants.DB2)) {
//
//		} else if (upperCaseDbName.contains(DBMSConstants.HSQLDB)) {
//
//		} else if (upperCaseDbName.contains((DBMSConstants.FIREBIRD)) {
//
//		} else if (upperCaseDbName.contains(DBMSConstants.DERBY)) {
//
        } else {

        }
        return new MySql5DatabaseDaoImpl(connParam, DatabaseTypeEnum.MySql5);
    }
}
