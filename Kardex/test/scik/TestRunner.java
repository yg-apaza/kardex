package scik;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import scik.modelo.AlmacenTest;

public class TestRunner
{
    public static void main(String[] args)
    {
        Result result = JUnitCore.runClasses(AlmacenTest.class);
        for (Failure failure : result.getFailures())
            System.out.println(failure.toString()); 
        System.out.println(result.wasSuccessful()); 
    }
}
