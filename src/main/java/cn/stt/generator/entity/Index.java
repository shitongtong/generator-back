package cn.stt.generator.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Index
 * @Description 索引
 * @Author shitt7
 * @Date 2019/11/20 10:22
 * @Version 1.0
 */
@Data
public class Index {
    /**
     * 名称
     */
    private String name;
    /**
     * 所属类型
     */
    private String indexType;
    /**
     * 所属表名称
     */
    private String tableName;
    /**
     * 是否唯一索引
     */
    private boolean unique;
    /**
     * 字段列表
     */
    private List<String> cloumns = new ArrayList<>();

    public void addCloumn(String cloumn) {
        this.cloumns.add(cloumn);
    }
}
