同步和异步调用

同步：顺序执行，每一任务项等待上级任务完成才开始，当发生异常时线程被阻塞
异步：并发执行，每一任务项接收到指令即执行，并不期待执行结果；
            当期待执行结果时，需要正常结束时，需要通过回调实现
            
            


使用 HSQL 或 MySQL

1/创建一个标准的 Maven 项目
2/编辑 POM.xml 文件
3/添加启动类：Prj1Application.java
4/添加 REST 风格控制器类：IndexController.java
5/添加测试类：Prj1ApplicationTests.java
6/访问 http://localhost:8080/index 或执行测试方法        
        
        