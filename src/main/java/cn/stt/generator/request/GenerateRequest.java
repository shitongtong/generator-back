package cn.stt.generator.request;

import cn.stt.generator.dao.ConnParam;
import lombok.Data;

import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GenerateRequest
 * @Description TODO
 * @Author shitt7
 * @Date 2019/11/20 11:01
 * @Version 1.0
 */
@Data
public class GenerateRequest {
    private String outPutFolderPath;
    private String basePackage = "cn.stt.generate";
    private ConnParam connParam;
    private List<TableModel> tableModels = new ArrayList<>();
}
