package com.tngtech.jgiven.testframework;

import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.notification.RunListener;

import com.tngtech.jgiven.impl.Config;
import com.tngtech.jgiven.junit.ScenarioTest;
import com.tngtech.jgiven.report.model.ReportModel;

public class JUnitExecutor extends TestExecutor {

    public JUnitExecutor() {}

    private JUnitExecutionResult execute( RequestSupplier requestSupplier ) {
        JUnitCore junitCore = new JUnitCore();
        JUnitExecutionResult result = new JUnitExecutionResult();
        TestRunListener runListener = new TestRunListener();
        junitCore.addListener( runListener );
        Config.config().setReportEnabled( false );
        result.result = junitCore.run( requestSupplier.supply() );
        Config.config().setReportEnabled( true );
        result.reportModel = runListener.reportModel;
        return result;
    }

    interface RequestSupplier {
        Request supply();
    }

    @Override
    public TestExecutionResult execute( final Class<?> testClass, final String testMethod ) {
        return execute( new RequestSupplier() {
            @Override
            public Request supply() {
                return Request.method( testClass, testMethod );
            }
        } );
    }

    @Override
    public TestExecutionResult execute( final Class<?> testClass ) {
        return execute( new RequestSupplier() {
            @Override
            public Request supply() {
                return Request.aClass( testClass );
            }
        } );
    }

    static class TestRunListener extends RunListener {
        ReportModel reportModel;

        @Override
        public void testIgnored( Description description ) throws Exception {
            reportModel = ScenarioTest.writerRule.getTestCaseModel();
        }

        @Override
        public void testFinished( Description description ) throws Exception {
            reportModel = ScenarioTest.writerRule.getTestCaseModel();
        }

    }
}
