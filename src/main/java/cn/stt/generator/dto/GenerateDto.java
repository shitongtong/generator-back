package cn.stt.generator.dto;

import cn.stt.generator.dao.ConnParam;
import lombok.Data;

import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Generatedto
 * @Description TODO
 * @Author shitt7
 * @Date 2019/11/20 11:08
 * @Version 1.0
 */
@Data
public class GenerateDto {
    private String outPutFolderPath;
    private String basePackage;
    private ConnParam connParam;
    private List<TableModel> tableModels = new ArrayList<>();
}
