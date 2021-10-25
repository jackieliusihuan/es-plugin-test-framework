# elasticsearch plugin 开发测试模板

## 运行环境
    - JDK >= 14,我目前使用的是15；
    - IDEA Ultimate 2020.3
    - gradle version: refer to gradle-wrapper.properties

不同的版本es开发存在差异，但是最近的版本中基本上都对以下测试方法有支持，只是可能在对应的gradle plugin中有不同的定义；遇到差异我们只要参考
对应org.elasticsearch.gradle:build-tools包的一些实现细节即可

## 版本管理
可以在semver版本管理方法上加一位来管理插件版本；例如：7.10.2是es的版本号，7.10.2.0则是此插件的第一次发版的版本号

## 单元测试

用于基本的工具类以及模块服务的测试；

集成基类ESTestCase，类及测试方法需要满足相应命名规则：类名称后缀为"XXXTests"， 方法名前缀为"testXXX"；否则会被忽略

下面命令会执行所有继承了ESTestCase的单元测试
```shell
./gradlew :test 
./gradlew :test --info
```
可以通过下面方法指定测试类或者方法
```shell
./gradlew :test --tests "--tests SampleUtilTests.testBcrypt"
./gradlew :test --tests "--tests SampleUtilTests"
```

## 集成测试

在ES的测试说明（TESTING.asciidoc）里有一些对如何测试的描述，下面是一些我觉得比较好用的几个方法

| sample测试类 | 基类 | desc |
|--|--|--|
| SamplePluginIT | ESIntegTestCase  |  基本的集成测试类，可以再build.gradle里面指定integTest cluster的集群大小，并且调用 transport client 或者 rest client进行测试 |
| SamplePluginRestIT | ESRestTestCase | client()默认提供rest client进行集成测试 |
| SamplePluginSingleNodeIT | ESSingleNodeTestCase | 更简单，启动一个单节点集群进行测试，不能使用rest client |

可以通过下面命令全部执行或者指定某些测试case执行
```shell
./gradlew :integTest
./gradlew :integTest --info
./gradlew :integTest --tests "SamplePluginRestIT.testPlugin"
```

## yaml 集成测试

ES在rest-api-spec包里标准化了ES的rest apis，同时es的测试框架也允许我们直接通过yaml文件定义要执行的测试case，具体的实行类是ESClientYamlSuiteTestCase，
也就是RestApiYamlIT的基类；测试集群大小及相关配置也可以在build.gradle里面声明

可以通过以下命令执行

```shell
./gradlew :yamlRestTest 
```

需要注意的是不同版本es对yamlRestTest放置的目录不同，之前有的版本需要放置在test.java目录下，并且把rest-api-spce.test目录放在测试目录的
resources目录下，执行task也可能也不是yamlRestTest，这个可以参考es源码里的一些case做相应调整

## 本地端到端手动测试

如何在本地方便的启动一个或者多个安装了插件的集群进行端到端测试？在build.gradle里面我们配置了两个安装了插件的测试集群：
```groovy
testClusters {
    firstCluster {
        plugin(project.tasks.bundlePlugin.archiveFile)
        numberOfNodes = 3
    }
    secondCluster {
        plugin(project.tasks.bundlePlugin.archiveFile)
        numberOfNodes = 3
    }
}
```
通过以下命令可以在本地启动起来
```shell
./gradlew :run
```

## 其他注意事项

    - 所有的日志都可以在build下的对应目录找到
