import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class SimpleCalculatorTest {
    public static SimpleCalculator testCalc;

    @BeforeClass
    public static void beforeClass() {
        testCalc = new SimpleCalculator();
    }

    @RunWith(Parameterized.class)
    public static class tSum {

        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int rez;

        @Parameterized.Parameters(name = "Test sun() #{index}: ({0}) + ({1}) = {2}")
        public static Iterable<Object[]> paramsForSum() {
            return Arrays.asList(new Object[][]{
                    {Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
                    {0, Integer.MAX_VALUE, Integer.MAX_VALUE},
                    {5, 0, 5},
                    {0, 5, 5}
            });
        }


        @Test
        public void sum() {
            assertEquals((int) rez, testCalc.sum(val1, val2));

        }


    }

    @RunWith(Parameterized.class)
    public static class tDiff {

        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int rez;

        @Parameterized.Parameters(name = "Test diff() #{index}: ({0}) - ({1}) = {2}")
        public static Iterable<Object[]> paramsForSum() {
            return Arrays.asList(new Object[][]{
                    {Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
                    {0, Integer.MAX_VALUE, Integer.MAX_VALUE * -1},
                    {5, 0, 5},
                    {0, 5, -5}
            });
        }


        @Test
        public void diff() {
            assertEquals((int) rez, testCalc.diff(val1, val2));

        }


    }

    @RunWith(Parameterized.class)
    public static class tMul {

        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int rez;

        @Parameterized.Parameters(name = "Test diff() #{index}: ({0}) - ({1}) = {2}")
        public static Iterable<Object[]> paramsForSum() {
            return Arrays.asList(new Object[][]{
                    {Integer.MAX_VALUE, 1, Integer.MAX_VALUE},
                    {1, Integer.MIN_VALUE, Integer.MIN_VALUE},
                    {5, 0, 0},
                    {0, 5, 0},
                    {7, 5, 35},
                    {3, 4, 12}
            });
        }


        @Test
        public void mult() {
            assertEquals((int) rez, testCalc.mult(val1, val2));

        }


    }

    @RunWith(Parameterized.class)
    public static class tDiv {

        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int rez;

        @Parameterized.Parameters(name = "Тест {index}: ({0}) + ({1}) = {2}")
        public static Iterable<Object[]> paramsForSum() {
            return Arrays.asList(new Object[][]{
                    {Integer.MAX_VALUE, 1, Integer.MAX_VALUE},
                    {1, Integer.MAX_VALUE, 0},
                    {5, 2, 2},
                    {6, 2, 3},
                    {7, 7, 1},
            });
        }


        @Test
        public void div() {
            assertEquals((int) rez, testCalc.div(val1, val2));

        }


    }
}