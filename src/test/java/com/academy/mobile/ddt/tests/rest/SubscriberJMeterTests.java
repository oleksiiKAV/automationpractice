package com.academy.mobile.ddt.tests.rest;

import com.academy.automation.framework.util.PropertyManager;
import org.apache.jmeter.JMeter;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class SubscriberJMeterTests {
    private static final Logger LOG = LogManager.getLogger(SubscriberJMeterTests.class);

    private StandardJMeterEngine jmeter;
    private SampleResult sampleResult;

    @BeforeClass
    public void setUp() {
        // JMeter Engine
        jmeter = new StandardJMeterEngine();

        // Initialize Properties, logging, locale, etc.
        String jMeterHome = PropertyManager.from("common").getProperty("jmeter.home");
        JMeterUtils.loadJMeterProperties( jMeterHome + "/bin/jmeter.properties");
        JMeterUtils.setJMeterHome(jMeterHome);
        JMeterUtils.initLocale();

        // configure proxy
        HTTPSamplerProxy httpSamplerProxy = new HTTPSamplerProxy();
        httpSamplerProxy.setDomain("localhost");
        httpSamplerProxy.setPort(8081);
        httpSamplerProxy.setPath("/rest/json/subscribers");
        httpSamplerProxy.setMethod("GET");
        httpSamplerProxy.setName("Open example.com");
        httpSamplerProxy.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
        httpSamplerProxy.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
        sampleResult = httpSamplerProxy.sample();

        // Load existing .jmx Test Plan
        try {
            File fJmeter = new File(PropertyManager.from("mobile").getProperty("jmeter.tests"));
            SaveService.loadProperties();
            HashTree testPlanTree = SaveService.loadTree(fJmeter);

            // Remove disabled test elements
            JMeter.convertSubTree(testPlanTree);
            jmeter.configure(testPlanTree);
        } catch (IOException e) {
            LOG.error("JMeter tests init error. Details: {}", e.getMessage());
        }
    }

    @Test
    public void testAddSubscriber() {
        LOG.info("Start JMeter Tests: 'addSubscriber'");

        // Run JMeter Test
        jmeter.run();

        Arrays.asList(sampleResult.getAssertionResults())
                .forEach(el->Assert.assertFalse(el.isError()));

        jmeter.exit();
    }
}
