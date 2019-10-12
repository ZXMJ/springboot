#springboot-common:工具类和错误码定义
#springboot-mybatis-plus:entity,dao,service,entity属性约束配置
entity,dao,service,controller自动生成

entity中属性校验未实现自动生成(因mybatis-plus,不支持读取是否必填,取值范围,所以默认所有字符串均不能为空,长度范围限制一样,正则校验一样可自行修改;其他类型只限制了不能为空)

controller中常用的增删改查实现自动生成

#springboot-netty:使用netty实现Tcp Client和Tcp Server,未解决编码解码;粘包,拆包

#springboot-web:controller,config,aspect,exception

##未实现:①不可修改的参数怎么处理②参数防篡改③未修改的值是否可不传
