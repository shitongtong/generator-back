package cn.stt.generator.entity;

import lombok.Data;

/**
 * @ClassName Trigger
 * @Description 触发器
 * @Author shitt7
 * @Date 2019/11/20 10:25
 * @Version 1.0
 */
@Data
public class Trigger {
    /**
     * 触发器名称
     */
    private String name;

    /**
     * 触发器类型
     */
    private String triggerType;

    /**
     * 触发器事件类型
     */
    private String eventType;

    /**
     * 触发器事件描述
     */
    private String description;

    /**
     * 触发器事件内容定义
     */
    private String definition;
}
