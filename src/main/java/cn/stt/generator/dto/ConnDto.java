package cn.stt.generator.dto;

import lombok.Data;

/**
 * @ClassName ConnDto
 * @Description TODO
 * @Author shitt7
 * @Date 2019/11/19 16:58
 * @Version 1.0
 */
@Data
public class ConnDto {
    /**
     * 数据库类型
     */
    private String dbType;
    /**
     * 数据库地址
     */
    private String host;
    /**
     * 数据库端口
     */
    private int port;
    /**
     * 数据库实例名
     */
    private String dbName;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
}
