
import com.sm.TestFunc;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import static org.junit.Assert.assertEquals;


public class UnitApp {

    public Function<List<String>, List<String>> allToUpperCase;

    public static void main(String[] args) {

        TestFunc testObj = new TestFunc(true);
        testObj.eval();; //allToUpperCase.apply(Arrays.asList("java8", "streams"));
//        assertEquals(expected, result);

    }



}
