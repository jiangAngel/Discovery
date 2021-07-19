package com.nepxion.discovery.plugin.strategy.sentinel.prometheus.monitor.metric;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Tank
 * @version 1.0
 */

import io.micrometer.core.instrument.Metrics;

import org.springframework.core.env.Environment;

import com.alibaba.csp.sentinel.metric.extension.MetricExtension;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.nepxion.discovery.common.entity.SentinelMetricType;
import com.nepxion.discovery.plugin.framework.context.PluginContextAware;
import com.nepxion.discovery.plugin.strategy.sentinel.prometheus.monitor.constant.SentinelPrometheusMetricConstant;

public class SentinelPrometheusMetricExtension implements MetricExtension {
    @Override
    public void addPass(String resource, int n, Object... args) {
        Environment environment = PluginContextAware.getStaticEnvironment();
        Boolean passQpsEnabled = environment.getProperty(SentinelPrometheusMetricConstant.SPRING_APPLICATION_STRATEGY_SENTINEL_PROMETHEUS_PASS_QPS_ENABLED, Boolean.class, Boolean.TRUE);
        if (passQpsEnabled) {
            Metrics.counter(SentinelMetricType.PASS_QPS.toString(), SentinelPrometheusMetricConstant.RESOURCE, resource).increment(n);
        }
    }

    @Override
    public void addBlock(String resource, int n, String origin, BlockException blockException, Object... args) {
        Environment environment = PluginContextAware.getStaticEnvironment();
        Boolean blockQpsEnabled = environment.getProperty(SentinelPrometheusMetricConstant.SPRING_APPLICATION_STRATEGY_SENTINEL_PROMETHEUS_BLOCK_QPS_ENABLED, Boolean.class, Boolean.TRUE);
        if (blockQpsEnabled) {
            Metrics.counter(SentinelMetricType.BLOCK_QPS.toString(), SentinelPrometheusMetricConstant.RESOURCE, resource).increment(n);
        }
    }

    @Override
    public void addSuccess(String resource, int n, Object... args) {
        Environment environment = PluginContextAware.getStaticEnvironment();
        Boolean successQpsEnabled = environment.getProperty(SentinelPrometheusMetricConstant.SPRING_APPLICATION_STRATEGY_SENTINEL_PROMETHEUS_SUCCESS_QPS_ENABLED, Boolean.class, Boolean.TRUE);
        if (successQpsEnabled) {
            Metrics.counter(SentinelMetricType.SUCCESS_QPS.toString(), SentinelPrometheusMetricConstant.RESOURCE, resource).increment(n);
        }
    }

    @Override
    public void addException(String resource, int n, Throwable throwable) {
        Environment environment = PluginContextAware.getStaticEnvironment();
        Boolean exceptionQpsEnabled = environment.getProperty(SentinelPrometheusMetricConstant.SPRING_APPLICATION_STRATEGY_SENTINEL_PROMETHEUS_EXCEPTION_QPS_ENABLED, Boolean.class, Boolean.TRUE);
        if (exceptionQpsEnabled) {
            Metrics.counter(SentinelMetricType.EXCEPTION_QPS.toString(), SentinelPrometheusMetricConstant.RESOURCE, resource).increment(n);
        }
    }

    @Override
    public void addRt(String resource, long rt, Object... args) {

    }

    @Override
    public void increaseThreadNum(String resource, Object... args) {

    }

    @Override
    public void decreaseThreadNum(String resource, Object... args) {

    }
}