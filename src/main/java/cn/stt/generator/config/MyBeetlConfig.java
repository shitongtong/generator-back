package cn.stt.generator.config;

import com.ibeetl.starter.BeetlTemplateCustomize;
import org.beetl.core.GroupTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyBeetlConfig
 * @Description TODO
 * @Author shitt7
 * @Date 2019/11/18 8:52
 * @Version 1.0
 */
@Configuration
public class MyBeetlConfig {

    @Bean
    public BeetlTemplateCustomize beetlTemplateCustomize() {
        return new BeetlTemplateCustomize() {
            @Override
            public void customize(GroupTemplate groupTemplate) {

            }
        };
    }
}
