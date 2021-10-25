package com.sihuan.es.plugin;

import org.elasticsearch.test.ESTestCase;
import org.junit.Assert;

//单元测试sample
public class SampleUtilTests extends ESTestCase {

    public void testIsEven() {
        Assert.assertTrue(SampleUtil.isEven(2));
    }

}
