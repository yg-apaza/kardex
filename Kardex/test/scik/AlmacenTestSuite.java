package scik;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import scik.modelo.AlmInsTest;
import scik.modelo.AlmModTest;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
    AlmInsTest.class,
    AlmModTest.class
})

public class AlmacenTestSuite
{
}