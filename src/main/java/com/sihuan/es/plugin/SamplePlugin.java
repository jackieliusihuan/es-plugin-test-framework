package com.sihuan.es.plugin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.support.ActionFilter;
import org.elasticsearch.action.support.ActionFilterChain;
import org.elasticsearch.plugins.ActionPlugin;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.tasks.Task;

import java.util.Collections;
import java.util.List;

/**
 * 这是一个样例es plugin，功能只是通过 ActionPlugin 给每个action处理流程增加一个过滤器（filter）, 并在日志中打印出action的名称
 */
public class SamplePlugin extends Plugin implements ActionPlugin {

    private static final Logger LOGGER = LogManager.getLogger(SamplePlugin.class);

    @Override
    public List<ActionFilter> getActionFilters() {

        return Collections.singletonList(new ActionFilter() {
            @Override
            public int order() {
                return 0;
            }

            @Override
            public <Request extends ActionRequest, Response extends ActionResponse> void apply(
                    Task task, String action, Request request, ActionListener<Response> listener,
                    ActionFilterChain<Request, Response> chain) {

                LOGGER.info("coming action : " + action);
                chain.proceed(task, action, request, listener);
            }
        });
    }
}
