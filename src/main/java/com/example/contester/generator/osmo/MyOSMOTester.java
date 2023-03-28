package com.example.contester.generator.osmo;

import osmo.tester.OSMOTester;
import osmo.tester.generator.MainGenerator;
import osmo.tester.generator.testsuite.TestSuite;

public class MyOSMOTester extends OSMOTester {
    @Override
    public MainGenerator initGenerator(long seed) {
        super.initGenerator(seed);
        TestSuite suite = new TestSuite();
        if (getConfig().isDataTraceRequested()) suite.enableParameterTracking();
        return new MyMainGenerator(seed, suite, getConfig());
    }

}