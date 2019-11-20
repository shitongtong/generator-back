package cn.stt.generator.service;

import cn.stt.generator.dto.ConnDto;
import cn.stt.generator.entity.Table;
import cn.stt.generator.model.GenerateModel;

import java.util.List;

/**
 * @ClassName GenerateService
 * @Description TODO
 * @Author shitt7
 * @Date 2019/11/19 16:45
 * @Version 1.0
 */
public interface GenerateService {

    /**
     * 测试是否可以连接
     *
     * @param connDto
     * @return
     */
    boolean testConnection(ConnDto connDto);

    /**
     * 反向查找数据表
     *
     * @param connDto
     * @return
     */
    List<Table> getTables(ConnDto connDto);

    /**
     * 获取代码生成数据模型
     *
     * @param generateModel
     * @return
     */
    GenerateModel getGenerateModel(GenerateModel generateModel);

    /**
     * 生成代码文件
     *
     * @param generateModel
     * @return
     * @throws Exception
     */
    boolean generateModels(GenerateModel generateModel) throws Exception;
}
