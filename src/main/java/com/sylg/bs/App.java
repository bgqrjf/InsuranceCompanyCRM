package com.sylg.bs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * 
 * ClassName: App 
 * @Description: 项目启动入口
 * @author QSNP242
 * @date 2018年4月8日
 */
@SpringBootApplication //程序启动注解
@EnableAutoConfiguration //自动选择web还是普通项目模式
@EnableTransactionManagement//自动管理事务
public class App   
{  
    public static void main( String[] args )  
    {  
        SpringApplication.run(App.class); 
    }  

}  