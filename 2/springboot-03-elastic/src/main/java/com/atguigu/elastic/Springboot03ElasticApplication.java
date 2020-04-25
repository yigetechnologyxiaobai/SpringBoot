package com.atguigu.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot默认支持两种技术来和ES交互
 * 1、Jest（默认不生效）
 *      需要导入jest的工具包（io.searchbox.client.JestClient）
 * 2、SpringData ElasticSearch[需要合适的版本]
 *      1)、Client  节点信息clusterNodes，clusterName
 *      2)、ElasticsearchTemplate 操作es
 *      3)、编写一个ElasticsearchRepository的子接口来操作ES
 *      如果版本不匹配：
 *          1）、升级SpringBoot版本
 *          2）、安装对应的ES
 *
 *   两种用法：https://github.com/spring-projects/spring-data-elasticsearch/
 *   1)、编写一个 ElasticsearchRepository
 *
 */
@SpringBootApplication
public class Springboot03ElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot03ElasticApplication.class, args);
    }

}
