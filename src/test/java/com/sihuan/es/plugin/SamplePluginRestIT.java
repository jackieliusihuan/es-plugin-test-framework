package com.sihuan.es.plugin;

import com.google.common.io.CharStreams;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.test.rest.ESRestTestCase;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 与 {@link SamplePluginIT}相似，只是client()默认提供rest client进行集成测试
 */
public class SamplePluginRestIT extends ESRestTestCase {

    /**
     * 简单的打印目前使用的所有插件，可以在结果里看到如下结果：
     * integTest-0 es-sample-plugin 7.10.2.0
     * integTest-1 es-sample-plugin 7.10.2.0
     * integTest-2 es-sample-plugin 7.10.2.0
     */
    public void testPlugin() throws IOException {
        System.out.println("start SamplePluginRestIT.testPlugin()");
        final Request request = new Request("GET", "/_cat/plugins");
        Response response = client().performRequest(request);
        System.out.println(CharStreams.toString(new InputStreamReader(response.getEntity().getContent())));
    }
}
