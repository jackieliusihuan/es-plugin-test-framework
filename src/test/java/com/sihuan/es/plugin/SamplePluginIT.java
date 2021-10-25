package com.sihuan.es.plugin;

import com.google.common.io.CharStreams;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.test.ESIntegTestCase;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 基本的集成测试类，可以再build.gradle里面指定integTest cluster的集群大小，并且调用 transport client 或者
 * rest client进行测试
 */
public class SamplePluginIT extends ESIntegTestCase {

    /**
     * 简单的打印目前使用的所有插件，可以在结果里看到如下结果：
     * integTest-0 es-sample-plugin 7.10.2.0
     * integTest-1 es-sample-plugin 7.10.2.0
     * integTest-2 es-sample-plugin 7.10.2.0
     */
    public void testPlugin() throws IOException {
        System.out.println("start SamplePluginIT.testPlugin()");
        final Request request = new Request("GET", "/_cat/plugins");
        Response response = getRestClient().performRequest(request);
        System.out.println(CharStreams.toString(new InputStreamReader(response.getEntity().getContent())));
    }
}
