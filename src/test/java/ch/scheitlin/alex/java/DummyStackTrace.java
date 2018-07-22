package ch.scheitlin.alex.java;

public class DummyStackTrace {
    public static String getDummyStackTrace() {
        return "java.lang.AssertionError\n" +
                "\tat org.junit.Assert.fail(Assert.java:86)\n" +
                "\tat org.junit.Assert.assertTrue(Assert.java:41)\n" +
                "\tat org.junit.Assert.assertTrue(Assert.java:52)\n" +
                "\tat com.example.bank.BankAccountTest.getNumber(BankAccountTest.java:21)\n" +
                "\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                "\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" +
                "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
                "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" +
                "\tat org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)\n" +
                "\tat org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)\n" +
                "\tat org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)\n" +
                "\tat org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)\n" +
                "\tat org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)\n" +
                "\tat org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)\n" +
                "\tat org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)\n" +
                "\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n" +
                "\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n" +
                "\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n" +
                "\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n" +
                "\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n" +
                "\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n" +
                "\tat org.apache.maven.surefire.junit4.JUnit4TestSet.execute(JUnit4TestSet.java:53)\n" +
                "\tat org.apache.maven.surefire.junit4.JUnit4Provider.executeTestSet(JUnit4Provider.java:123)\n" +
                "\tat org.apache.maven.surefire.junit4.JUnit4Provider.invoke(JUnit4Provider.java:104)\n" +
                "\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                "\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" +
                "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
                "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" +
                "\tat org.apache.maven.surefire.util.ReflectionUtils.invokeMethodWithArray(ReflectionUtils.java:164)\n" +
                "\tat org.apache.maven.surefire.booter.ProviderFactory$ProviderProxy.invoke(ProviderFactory.java:110)\n" +
                "\tat org.apache.maven.surefire.booter.SurefireStarter.invokeProvider(SurefireStarter.java:175)\n" +
                "\tat org.apache.maven.surefire.booter.SurefireStarter.runSuitesInProcessWhenForked(SurefireStarter.java:107)\n" +
                "\tat org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:68)";
    }
}
