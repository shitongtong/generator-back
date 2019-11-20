package cn.stt.generator.entity;

import lombok.Data;

/**
 * @ClassName Procedure
 * @Description 存储过程
 * @Author shitt7
 * @Date 2019/11/20 10:23
 * @Version 1.0
 */
@Data
public class Procedure {
    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 内容定义
     */
    private String definition;
}
