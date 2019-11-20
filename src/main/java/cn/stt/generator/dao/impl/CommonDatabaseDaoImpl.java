package cn.stt.generator.dao.impl;

import cn.stt.generator.dao.ConnParam;
import cn.stt.generator.enums.DatabaseTypeEnum;
import cn.stt.generator.exception.DaoException;
import cn.stt.generator.util.Dom4jUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CommonDatabaseDaoImpl
 * @Description 通用数据库元信息查询类
 * @Author shitt7
 * @Date 2019/11/20 8:58
 * @Version 1.0
 */
@Slf4j
public class CommonDatabaseDaoImpl extends AbstractDatabaseDaoImpl {

    private static final String ELEMENT_DRIVER = DRIVER;
    private static final String ELEMENT_URL = URL;
    private static final String ELEMENT_SELECT = "select";
    private static final String ATTRIBUTE_NAME = "name";

    private String driver;
    private String url;
    private Map<String, String> selectMap = new HashMap<>();

    private DatabaseTypeEnum dbType;

    /**
     * @param connParam
     */
    public CommonDatabaseDaoImpl(ConnParam connParam, DatabaseTypeEnum dbType) {
        super(connParam);
        setDbType(dbType);
        loadSqlXml(dbType);
    }

    /**
     * @param dbType the dbType to set
     */
    public void setDbType(DatabaseTypeEnum dbType) {
        this.dbType = dbType;
        setConverter(dbType.getConverter());
    }

    @Override
    protected String getDriver() throws DaoException {
        return driver;
    }

    @Override
    protected String getUrl(String host, int port, String dbName) throws DaoException {
        return String.format(url, host, port, dbName);
    }

    @Override
    protected String getQuerySql(String sqlKey) throws DaoException {
        if (selectMap.containsKey(sqlKey)) {
            return selectMap.get(sqlKey);
        }
        throw new DaoException(DaoException.QUERY_EXCEPTION, "获取sql查询出错，数据库枚举类型为：" + dbType + "，查询语句为：" + sqlKey, null);
    }

    @SuppressWarnings("unchecked")
    private void loadSqlXml(DatabaseTypeEnum dbType) {
        InputStream inputStream = null;
        try {
            ClassPathResource resource = new ClassPathResource(dbType.getFileName());
            inputStream = resource.getInputStream();
        } catch (IOException e) {
            log.error("", e);
        }
        Document doc = Dom4jUtil.getDocument(inputStream);
        if (doc != null) {
            Element root = doc.getRootElement();
            driver = root.elementText(ELEMENT_DRIVER);
            url = root.elementText(ELEMENT_URL);
            for (Element selectElem : (List<Element>) root.elements(ELEMENT_SELECT)) {
                selectMap.put(selectElem.attributeValue(ATTRIBUTE_NAME), selectElem.getTextTrim());
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("", e);
            }
        }
    }
}
