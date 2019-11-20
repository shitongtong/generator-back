package cn.stt.generator.service.impl;

import cn.stt.generator.dao.ConnParam;
import cn.stt.generator.dto.ConnDto;
import cn.stt.generator.entity.Column;
import cn.stt.generator.entity.PrimaryKey;
import cn.stt.generator.entity.Table;
import cn.stt.generator.model.ColumnModel;
import cn.stt.generator.model.GenerateModel;
import cn.stt.generator.model.TableModel;
import cn.stt.generator.service.DatabaseService;
import cn.stt.generator.service.GenerateService;
import cn.stt.generator.util.DataTypeUtil;
import cn.stt.generator.util.StringUtil;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static cn.stt.generator.util.StringUtil.lineToHump;


/**
 * @ClassName GenerateServiceImpl
 * @Description TODO
 * @Author shitt7
 * @Date 2019/11/19 18:19
 * @Version 1.0
 */
@Service
public class GenerateServiceImpl implements GenerateService {

    public static final String TABLE = "table";

    public static final String TEMPLATE_ENTITY = "/entity.btl";
    public static final String TEMPLATE_MAPPER = "/mapper.btl";
    public static final String TEMPLATE_SQLMAP = "/sqlMap.btl";
    public static final String TEMPLATE_SERVICE = "/service.btl";
    public static final String TEMPLATE_SERVICE_IMPL = "/serviceImpl.btl";
    public static final String TEMPLATE_CONTROLLER = "/controller.btl";
    public static final String TEMPLATE_VIEW = "/view.btl";

    public static final String PACKAGE_ENTITY = "entity";
    public static final String PACKAGE_MAPPER = "mapper";
    public static final String PACKAGE_SQLMAP = "sqlmap";
    public static final String PACKAGE_SERVICE = "service";
    public static final String PACKAGE_SERVICE_IMPL = "service.impl";
    public static final String PACKAGE_CONTROLLER = "controller";
    public static final String PACKAGE_VIEW = "view";

    public static final String SQL_MAP_SUFFIX = "Mapper.xml";
    public static final String MODEL_SUFFIX = ".java";
    public static final String MAPPER_SUFFIX = "Mapper.java";
    public static final String SERVICE_SUFFIX = "Service.java";
    public static final String SERVICE_IMPL_SUFFIX = "ServiceImpl.java";
    public static final String CONTROLLER_SUFFIX = "Controller.java";
    public static final String VIEW_SUFFIX = ".vue";

    @Autowired
    private DatabaseService databaseService;

    @Override
    public boolean testConnection(ConnDto connDto) {
        return databaseService.canConnect(connDto);
    }

    @Override
    public List<Table> getTables(ConnDto connDto) {
        return databaseService.getTables(connDto);
    }

    @Override
    public GenerateModel getGenerateModel(GenerateModel generateModel) {
        List<TableModel> tableModels = generateModel.getTableModels();
        for (TableModel tableModel : tableModels) {
            ConnParam connParam = generateModel.getConnParam();
            String tableName = tableModel.getName();
            // 设置表对应的实体名
            tableModel.setClassName(StringUtil.capitalize(lineToHump(tableName)));
            // 设置表对应的实例名
            tableModel.setObjectName(StringUtil.uncapitalize(tableModel.getClassName()));
            // 加载表字段
            tableModel.setColumns(getColumns(tableModel, connParam, tableName));
        }
        return generateModel;
    }

    @Override
    public boolean generateModels(GenerateModel generateModel) throws Exception {
        String outPutFolderPath = generateModel.getOutPutFolderPath();
        if(outPutFolderPath == null) {
            outPutFolderPath = System.getProperty("user.dir") + "/src/main/java";
            generateModel.setOutPutFolderPath(outPutFolderPath);
        }
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("templates");
        Configuration configuration = Configuration.defaultConfiguration();
        GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, configuration);
        for(TableModel tableModel:generateModel.getTableModels()) {
            // 设置各类代码包名
            tableModel.setModelPackageName(getPakcageName(generateModel.getBasePackage(), PACKAGE_ENTITY));
            tableModel.setDaoPackageName(getPakcageName(generateModel.getBasePackage(), PACKAGE_MAPPER));
            tableModel.setSqlMapPackageName(getPakcageName(generateModel.getBasePackage(), PACKAGE_SQLMAP));
            tableModel.setServicePackageName(getPakcageName(generateModel.getBasePackage(), PACKAGE_SERVICE));
            tableModel.setServiceImplPackageName(getPakcageName(generateModel.getBasePackage(), PACKAGE_SERVICE_IMPL));
            tableModel.setControllerPackageName(getPakcageName(generateModel.getBasePackage(), PACKAGE_CONTROLLER));
            tableModel.setViewPackageName(getPakcageName(generateModel.getBasePackage(), PACKAGE_VIEW));
            // generate model
            generateModel(groupTemplate, tableModel, TEMPLATE_ENTITY, generateModel.getOutPutFolderPath());
            // generate mapper
            generateModel(groupTemplate, tableModel, TEMPLATE_MAPPER, generateModel.getOutPutFolderPath());
            // generate sqlmap.xml
            generateModel(groupTemplate, tableModel, TEMPLATE_SQLMAP, generateModel.getOutPutFolderPath());
            // generate service
            generateModel(groupTemplate, tableModel, TEMPLATE_SERVICE, generateModel.getOutPutFolderPath());
            // generate serviceImpl
            generateModel(groupTemplate, tableModel, TEMPLATE_SERVICE_IMPL, generateModel.getOutPutFolderPath());
            // generate controller
            generateModel(groupTemplate, tableModel, TEMPLATE_CONTROLLER, generateModel.getOutPutFolderPath());
            // generate view
            generateModel(groupTemplate, tableModel, TEMPLATE_VIEW, generateModel.getOutPutFolderPath());
        }
        return true;
    }

    private String getPakcageName(String basePackage, String subPackage) {
        return basePackage + "." + subPackage;
    }

    private void generateModel(GroupTemplate groupTemplate, TableModel tableModel, String templatePath, String outPutFolderPath) throws Exception {
        Template template = groupTemplate.getTemplate(templatePath);
        template.binding(TABLE, tableModel);
        FileOutputStream os = new FileOutputStream(getOutputFile(tableModel, outPutFolderPath, templatePath));
        template.renderTo(os);
        os.close();
    }

    private String getOutputFile(TableModel tableModel, String outPutFolderPath, String templatePath) {
        String packageName = tableModel.getModelPackageName();
        String suffix = MODEL_SUFFIX;
        if(TEMPLATE_MAPPER.equals(templatePath)) {
            packageName = tableModel.getDaoPackageName();
            suffix = MAPPER_SUFFIX;
        } else if(TEMPLATE_SQLMAP.equals(templatePath)) {
            packageName = tableModel.getSqlMapPackageName();
            suffix = SQL_MAP_SUFFIX;
        } else if(TEMPLATE_SERVICE.equals(templatePath)) {
            packageName = tableModel.getServicePackageName();
            suffix = SERVICE_SUFFIX;
        } else if(TEMPLATE_SERVICE_IMPL.equals(templatePath)) {
            packageName = tableModel.getServiceImplPackageName();
            suffix = SERVICE_IMPL_SUFFIX;
        } else if(TEMPLATE_CONTROLLER.equals(templatePath)) {
            packageName = tableModel.getControllerPackageName();
            suffix = CONTROLLER_SUFFIX;
        } else if(TEMPLATE_VIEW.equals(templatePath)) {
            packageName = tableModel.getViewPackageName();
            suffix = VIEW_SUFFIX;
        }
        outPutFolderPath = outPutFolderPath + "/" + packageName.replaceAll("\\.", "/");
        File outPutFolder = new File(outPutFolderPath);
        if(!outPutFolder.exists()) {
            outPutFolder.mkdirs();
        }
        String filePath = outPutFolderPath + File.separator + tableModel.getClassName() + suffix;
        File file = new File(filePath);
        if(file.exists()) {
            file.delete();
        }
        return filePath;
    }

    private List<ColumnModel> getColumns(TableModel tableModel, ConnParam connParam, String tableName) {
        List<ColumnModel> columnModels = new ArrayList<>();
        List<Column> columns = databaseService.getColumns(connParam, tableName);
        List<PrimaryKey> primaryKeys = databaseService.getPrimaryKeys(connParam, tableName);
        for (Column column : columns) {
            ColumnModel columnModel = new ColumnModel();
            BeanUtils.copyProperties(column, columnModel);
            // 设置字段对应的对象属性名
            String fieldName = lineToHump(column.getName());
            columnModel.setFieldName(fieldName);
            // 设置属性设置和获取方法
            String setter = "set" + StringUtil.capitalize(fieldName);
            columnModel.setSetter(setter);
            String getter = "get" + StringUtil.capitalize(fieldName);
            columnModel.setGetter(getter);
            // 设置JAVA数据类型
            String javaType = DataTypeUtil.getJavaType(columnModel.getDataType());
            columnModel.setJavaType(javaType);
            String jdbcType = DataTypeUtil.getJdbcType(columnModel.getDataType());
            columnModel.setJdbcType(jdbcType);
            // 设置属性是否为主键
            for (PrimaryKey primaryKey : primaryKeys) {
                if (column.getName().equalsIgnoreCase(primaryKey.getCloumn())) {
                    columnModel.setPrimaryKey(true);
                    tableModel.setPrimaryKey(columnModel);
                    break;
                }
            }
            columnModels.add(columnModel);
        }
        return columnModels;
    }
}
