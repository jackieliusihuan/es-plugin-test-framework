package com.sihuan.es.plugin;

import com.carrotsearch.randomizedtesting.annotations.Name;
import com.carrotsearch.randomizedtesting.annotations.ParametersFactory;
import com.carrotsearch.randomizedtesting.annotations.TimeoutSuite;
import org.apache.lucene.util.TimeUnits;
import org.elasticsearch.test.rest.yaml.ClientYamlTestCandidate;
import org.elasticsearch.test.rest.yaml.ESClientYamlSuiteTestCase;

/**
 * ES把Rest接口都进行了单独的抽象，所以测试case可以在一个yaml文件里进行描述，这个集成测试会把
 * /rest-api-spec/test的测试执行一遍
 */
@TimeoutSuite(millis = 40 * TimeUnits.MINUTE)
public class RestApiYamlIT extends ESClientYamlSuiteTestCase {
    public RestApiYamlIT(@Name("yaml") ClientYamlTestCandidate testCandidate) {
        super(testCandidate);
    }

    @ParametersFactory
    public static Iterable<Object[]> parameters() throws Exception {
        return createParameters();
    }
}