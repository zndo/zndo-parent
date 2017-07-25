使用 Spring Boot 来构建您的 Spring 应用，将带来不一样的开发体验。

首先容许我介绍一下 Spring Boot 的特性：
1、快速进入开发，简单易用
2、灵活智能的项目配置
3、快速部署
4、生产级别指标监控

这是第一个项目，默认您已经掌握 Maven 的使用方法和 Spring 框架的基础知识。
让我们开始吧！

1/创建一个标准的 Maven 项目
2/编辑 POM.xml 文件

    <!-- a.继承父项目 POM（可自定义） -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.4.RELEASE</version>
        <relativePath/>
    </parent>
    
    <!-- b.添加 web 和测试支持  -->
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
3/添加启动类：Prj1Application.java

package net.zndo.training.prj1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Prj1Application {

    public static void main(String[] args) {
        SpringApplication.run(Prj1Application.class, args);
    }
}

4/添加 REST 风格控制器类：IndexController.java

package net.zndo.training.prj1.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "你好，这是首页内容。";
    }
}

5/添加测试类：Prj1ApplicationTests.java

package net.zndo.training.prj1;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import net.zndo.training.prj1.web.IndexController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Prj1ApplicationTests {

    private MockMvc mvc;

    @Test
    public void contextLoads() {
    }

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
    }

    @Test
    public void mockIndexRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/index")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));

    }

}

在此，我们看到有使用 Java 的静态导入-import static(JDK1.5+) 和 MockMvc，
    1、静态导入
        导入的静态成员直接使用，而不需要使用完全限定名，适用于
        频繁使用的静态方法，如
    
        org.hamcrest.Matchers.equalTo("text");
        equalTo("text");
        
    2、MockMvc
           方便的对 Web 应用进行测试
        
6/访问 http://localhost:8080/index 或执行测试方法        
        
        