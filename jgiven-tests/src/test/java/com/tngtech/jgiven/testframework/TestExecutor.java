package com.tngtech.jgiven.testframework;

public abstract class TestExecutor {

    public static TestExecutor getExecutor( TestFramework framework ) {
        switch( framework ) {
            case JUnit:
                return new JUnitExecutor();
            case TestNG:
                return new TestNgExecutor();
            default:
                throw new IllegalArgumentException( "Unknown framework: " + framework );
        }
    }

    public abstract TestExecutionResult execute( Class<?> testClass, String testMethod );

    public abstract TestExecutionResult execute( Class<?> testClass );
}
