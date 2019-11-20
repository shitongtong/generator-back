package cn.stt.generator.controller;

import cn.stt.common.response.Response;
import cn.stt.common.util.TransferUtil;
import cn.stt.generator.dto.ConnDto;
import cn.stt.generator.entity.Table;
import cn.stt.generator.model.GenerateModel;
import cn.stt.generator.request.ConnRequest;
import cn.stt.generator.request.GenerateRequest;
import cn.stt.generator.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName GenerateController
 * @Description TODO
 * @Author shitt7
 * @Date 2019/11/19 16:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/")
public class GenerateController {

    @Autowired
    private GenerateService generatorService;

    /**
     * 测试连接
     *
     * @param request
     * @return
     */
    @PostMapping("/testConnection")
    public ResponseEntity<Response> testConnection(@RequestBody ConnRequest request) {
        ConnDto connDto = TransferUtil.transfer(request, ConnDto.class);
        boolean success = generatorService.testConnection(connDto);
        if (success) {
            return ResponseEntity.ok(Response.success());
        }
        return ResponseEntity.ok(Response.errorCustom("连接失败,请检查数据库及连接"));
    }

    /**
     * 获取数据库表集合
     *
     * @param request
     * @return
     */
    @PostMapping("/getTables")
    public ResponseEntity<Response> getTables(@RequestBody ConnRequest request) {
        ConnDto connDto = TransferUtil.transfer(request, ConnDto.class);
        List<Table> tableList = generatorService.getTables(connDto);
        return ResponseEntity.ok(Response.success(tableList));
    }

    /**
     * 获取代码生成数据模型
     *
     * @param request
     * @return
     */
    @PostMapping("/getGenerateModel")
    public ResponseEntity<Response> getGenerateModel(@RequestBody GenerateRequest request) {
        GenerateModel generateModel = TransferUtil.transfer(request, GenerateModel.class);
        GenerateModel model = generatorService.getGenerateModel(generateModel);
        return ResponseEntity.ok(Response.success(model));
    }


    /**
     * 生成代码文件
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/generateModels")
    public ResponseEntity<Response> generateModels(@RequestBody GenerateRequest request) throws Exception {
        GenerateModel generateModel = TransferUtil.transfer(request, GenerateModel.class);
        generatorService.generateModels(generateModel);
        return ResponseEntity.ok(Response.success());
    }
}
