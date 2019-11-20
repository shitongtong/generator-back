package cn.stt.generator.converter;

import cn.stt.generator.entity.Column;
import cn.stt.generator.entity.ForeignKey;
import cn.stt.generator.entity.Index;
import cn.stt.generator.entity.PrimaryKey;
import cn.stt.generator.entity.Table;
import cn.stt.generator.entity.Trigger;

import java.util.Map;

/**
 * @ClassName MetaDataConverter
 * @Description 元数据转换器接口
 * @Author shitt7
 * @Date 2019/11/20 9:03
 * @Version 1.0
 */
public interface MetaDataConverter {
    /**
     * 将map转换为Table
     *
     * @param map
     * @return
     */
    Table convertMap2Table(Map<String, String> map);

    /**
     * 将map转换为Column
     *
     * @param map
     * @return
     */
    Column convertMap2Column(Map<String, String> map);

    /**
     * 将map转换为PrimaryKey
     *
     * @param map
     * @return
     */
    PrimaryKey convertMap2PrimaryKey(Map<String, String> map);

    /**
     * 将map转换为ForeignKey
     *
     * @param map
     * @return
     */
    ForeignKey convertMap2ForeignKey(Map<String, String> map);

    /**
     * 将map转换为Index
     *
     * @param map
     * @return
     */
    Index convertMap2Index(Map<String, String> map);

    /**
     * 将map转换为Triger
     *
     * @param map
     * @return
     */
    Trigger convertMap2Trigger(Map<String, String> map);
}
