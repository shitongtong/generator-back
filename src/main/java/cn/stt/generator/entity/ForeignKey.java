package cn.stt.generator.entity;

import lombok.Data;

/**
 * @ClassName ForeignKey
 * @Description 外键
 * @Author shitt7
 * @Date 2019/11/20 10:22
 * @Version 1.0
 */
@Data
public class ForeignKey {
    private String fkName;

    private String pkTableName;

    private String pkColumnName;

    private String fkTableName;

    private String fkColumnName;
}
