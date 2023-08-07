# 文献管理
+ 通过登录上传自己的文献
+ 查看自己的文献库
+ 记录每篇文献看的次数
+ 查看文献时恢复到上次文献的历史记录



## 遇到的问题
+ 自己定义了webMvcConfigure后，需要自己配置静态资源的目录，需要看下原理
+ multipart上传文件的大小是有限制的，需要自己配置更大的大小
+ mybatis-plus 需要指定mapper路径 @MapperScan
+ security结合JDBC的验证，默认返回html，需要配置返回json
+ mybatis需要配置主键的id
+ test要用spring的test单元，junit的不行
+ 