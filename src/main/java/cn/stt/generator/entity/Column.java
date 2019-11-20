package cn.stt.generator.entity;

import lombok.Data;

/**
 * @ClassName Column
 * @Description 表字段
 * @Author shitt7
 * @Date 2019/11/20 10:21
 * @Version 1.0
 */
@Data
public class Column {
    /**
     * 名称
     */
    private String name;
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 是否可空
     */
    private boolean isNullable;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 长度
     */
    private String length;
    /**
     * 小数位
     */
    private String precision;
    /**
     * 描述
     */
    private String description;
}
