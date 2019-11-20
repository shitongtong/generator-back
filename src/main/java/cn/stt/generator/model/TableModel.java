package cn.stt.generator.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TableModel
 * @Description 代码生成模型-表
 * @Author shitt7
 * @Date 2019/11/20 13:43
 * @Version 1.0
 */
@Data
public class TableModel {
    /**
     * Model包名
     */
    private String modelPackageName;
    /**
     * Dao包名
     */
    private String daoPackageName;
    /**
     * SqlMap xml包名
     */
    private String sqlMapPackageName;
    /**
     * Servcie包名
     */
    private String servicePackageName;
    /**
     * ServcieImpl包名
     */
    private String serviceImplPackageName;
    /**
     * Controller包名
     */
    private String controllerPackageName;
    /**
     * View目录名
     */
    private String viewPackageName;
    /**
     * 类名
     */
    private String className;
    /**
     * 实例名
     */
    private String objectName;
    /**
     * 主键列
     */
    private ColumnModel primaryKey;
    /**
     * 表名
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 表空间
     */
    private String tablespace;
    /**
     * 索引字段列表
     */
    private List<ColumnModel> columns = new ArrayList<>();

    public void addColumn(ColumnModel clolumn) {
        this.columns.add(clolumn);
    }

    public ColumnModel getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ColumnModel primaryKey) {
        this.primaryKey = primaryKey;
    }
}
