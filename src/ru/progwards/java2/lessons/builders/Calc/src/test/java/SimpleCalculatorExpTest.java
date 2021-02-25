
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class SimpleCalculatorExpTest {
    public static SimpleCalculator testCalc;

    @BeforeClass
    public static void init() {
        testCalc = new SimpleCalculator();
    }

    @Rule
    public ExpectedException exp = ExpectedException.none();

    @Test
    public void tSum() {
        exp.expect(ArithmeticException.class);
        exp.expectMessage("Overflow int in sum()");
        testCalc.sum(Integer.MAX_VALUE, 1);
    }

    @Test
    public void tDiff() {
        exp.expect(ArithmeticException.class);
        exp.expectMessage("Overflow int in diff()");
        testCalc.diff(Integer.MIN_VALUE, 1);
    }

    @Test
    public void tMult() {
        exp.expect(ArithmeticException.class);
        exp.expectMessage("Overflow int in mult()");
        testCalc.mult(Integer.MAX_VALUE, 2);
    }

    @Test
    public void tDiv0() {
        exp.expect(ArithmeticException.class);
        exp.expectMessage(" /0 ! ");
        testCalc.div(Integer.MAX_VALUE, 0);
    }

    @Test
    public void tDiv() {
        exp.expect(ArithmeticException.class);
        exp.expectMessage("Overflow int in div()");
        testCalc.div(Integer.MIN_VALUE, -1);
    }
}
