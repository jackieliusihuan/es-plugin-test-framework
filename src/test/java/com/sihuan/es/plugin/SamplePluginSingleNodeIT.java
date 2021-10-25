package com.sihuan.es.plugin;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.test.ESSingleNodeTestCase;

import java.io.IOException;

import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;

/**
 * 单节点集成测试；
 * 相比于 {@link SamplePluginIT} 更简单，但是能测试的功能也更受局限，譬如无法调用Rest Client进行测试
 */
public class SamplePluginSingleNodeIT extends ESSingleNodeTestCase {

    /**
     * 一个简单的创建 and 查询， 会打印出：
     * {"foo":"bar"}
     */
    public void testPlugin() throws IOException {
        System.out.println("start SamplePluginSingleNodeIT.testPlugin()");

        client().prepareIndex("test", "doc", "1").setSource("foo", "bar")
                .setRefreshPolicy(IMMEDIATE).get();

        GetResponse response = client().prepareGet("test", "doc", "1").get();
        System.out.println(response.getSourceAsString());
    }
}
