package cn.stt.generator.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Table
 * @Description 数据表
 * @Author shitt7
 * @Date 2019/11/20 10:24
 * @Version 1.0
 */
@Data
public class Table {
    /**
     * 名称
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
    private List<Column> columns = new ArrayList<>();
}
