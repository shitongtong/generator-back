package cn.stt.generator.model;

import cn.stt.generator.dao.ConnParam;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GenerateModel
 * @Description 代码生成数据模型
 * @Author shitt7
 * @Date 2019/11/20 13:46
 * @Version 1.0
 */
@Data
public class GenerateModel {
    private String outPutFolderPath;
    private String basePackage = "cn.stt.generate";
    private ConnParam connParam;
    private List<TableModel> tableModels = new ArrayList<>();
}
