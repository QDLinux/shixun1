package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

/*
    启动类
 */
//方式一：可以自定义启动类路径
//@EnableAutoConfiguration//开启自动配置
//@ComponentScan//用于扫描，默认扫描当前包和子包，也可以自定义路径
//方式二：简单，但是不能自定义启动类路径，只能放在父级包下，才能自动扫描
@SpringBootApplication
@ServletComponentScan  //开启servlet相关注解
@MapperScan("org.example.mapper")
@EnableScheduling//开启定时器
public class App
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }
}
