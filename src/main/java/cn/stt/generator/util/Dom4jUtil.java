package cn.stt.generator.util;

import cn.stt.generator.constant.CommonConstant;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Namespace;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Dom4jUtil
 * @Description DOM4J解析框架相关操作方法
 * @Author shitt7
 * @Date 2019/11/20 10:46
 * @Version 1.0
 */
public class Dom4jUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(Dom4jUtil.class);
    private static String nameSpace = null;

    private Dom4jUtil() {
        /* 禁止外部实例化 */
    }

    /**
     * 根据文件获取对应的Document对象
     *
     * @param file
     * @return
     */
    public static Document getDocument(File file) {
        return getDocument(file.getAbsolutePath());
    }

    /**
     * 根据文件路径获取对应的Document对象
     *
     * @param filePath
     * @return
     */
    public static Document getDocument(String filePath) {
        if (!new File(filePath).exists()) {
            LOGGER.info("{} is not exist!", filePath);
            return null;
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            LOGGER.error("", e);
        }
        return getDocument(fis);
    }

    /**
     * 根据输入流读取对应的Document对象
     *
     * @param input
     * @return
     */
    public static Document getDocument(InputStream input) {
        Document document = null;
        if (input != null) {
            try {
                SAXReader saxReader = new SAXReader();
                saxReader.setEncoding(CommonConstant.ENCODING_UTF8);
                DocumentFactory df = new DocumentFactory();
                Map<String, String> map = new HashMap<>();
                df.setXPathNamespaceURIs(map);
                saxReader.setDocumentFactory(df);
                document = saxReader.read(input);
                if (document.getRootElement().getNamespace() != Namespace.NO_NAMESPACE) {
                    nameSpace = "nameSpace";
                    map.put(nameSpace, document.getRootElement().getNamespace().getURI());
                }
            } catch (Exception e) {
                LOGGER.error("", e);
            } finally {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.error("", e);
                }
            }
        }
        return document;
    }


    /**
     * 将文档内容持久化到文件
     *
     * @param document
     * @param filePath
     */
    public static void writeDocument(Document document, String filePath) {
        writeDocument(document, new File(filePath));
    }

    /**
     * 将文档内容持久化到文件
     *
     * @param document
     * @param file
     */
    public static void writeDocument(Document document, File file) {
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding(CommonConstant.ENCODING_UTF8);
            FileOutputStream fos = new FileOutputStream(file);
            XMLWriter output = new XMLWriter(fos, format);
            output.write(document);
            output.close();
        } catch (IOException e) {
            LOGGER.error("", e);
        }
    }

    /**
     * 根据XPath获取查询结果列表集合
     *
     * @param file
     * @param xpath
     * @return List
     */
    @SuppressWarnings("rawtypes")
    public static List selectNodes(String file, String xpath) {
        return selectNodes(new File(file), xpath);
    }

    /**
     * 根据XPath获取查询结果列表集合
     *
     * @param file
     * @param xpath
     * @return List
     */
    @SuppressWarnings("rawtypes")
    public static List selectNodes(File file, String xpath) {
        List nodeList = new ArrayList();
        if (file.exists()) {
            Document document = getDocument(file);
            xpath = translateXPath(xpath);
            if (document != null) {
                nodeList = document.selectNodes(xpath);
            }
        }
        return nodeList;
    }

    /**
     * 转换成带有命名空间前缀的路径
     **/
    public static String translateXPath(String xPath) {
        String newXPath = "";
        if (!StringUtil.isEmpty(nameSpace)) {
            int i = xPath.lastIndexOf("/");
            while (xPath.length() > 1) {
                newXPath = "/" + nameSpace + ":" + xPath.substring(i + 1, xPath.length()) + newXPath;
                xPath = xPath.substring(0, i);
                i = xPath.lastIndexOf("/");
            }
        } else {
            newXPath = xPath;
        }
        return newXPath;
    }
}
