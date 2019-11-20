package cn.stt.generator.entity;

import lombok.Data;

/**
 * @ClassName PrimaryKey
 * @Description 主键
 * @Author shitt7
 * @Date 2019/11/20 10:23
 * @Version 1.0
 */
@Data
public class PrimaryKey {
    /**
     * 名称
     */
    private String name;
    /**
     * 所属表名称
     */
    private String tableName;
    /**
     * 字段
     */
    private String cloumn;
}
