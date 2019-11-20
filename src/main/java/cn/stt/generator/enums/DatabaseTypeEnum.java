package cn.stt.generator.enums;

import cn.stt.generator.converter.CommonMetaDataConverter;
import cn.stt.generator.converter.MetaDataConverter;
import cn.stt.generator.converter.MySQL5MetaDataConverter;
import cn.stt.generator.converter.OracleMetaDataConverter;

/**
 * @ClassName DatabaseTypeEnum
 * @Description 数据库查询语句文件
 * @Author shitt7
 * @Date 2019/11/20 8:50
 * @Version 1.0
 */
public enum DatabaseTypeEnum {
    /**
     *
     */
    Oracle {
        @Override
        public String getFileName() {
            return "Oracle.xml";
        }

        @Override
        public MetaDataConverter getConverter() {
            return OracleMetaDataConverter.getInstance();
        }

    },

    MySql5 {
        @Override
        public String getFileName() {
            return "MySQL5.xml";
        }

        @Override
        public MetaDataConverter getConverter() {
            return MySQL5MetaDataConverter.getInstance();
        }
    },

    MSSQLServer {
        @Override
        public String getFileName() {
            return "MSSQL.xml";
        }
    };

    private static final String FOLDER = DatabaseTypeEnum.class.getPackage().getName().replace('.', '/');

    abstract public String getFileName();

    public MetaDataConverter getConverter() {
        return CommonMetaDataConverter.getInstance();
    }
}
