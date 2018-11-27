import static java.lang.System.out;
import static org.junit.Assert.*;

public class SLCWithGetTest {

    SLCWithGet<String> slc = new SLCWithGet<>();

    @org.junit.Test
    public void get() {

    }

    @org.junit.Test
    public void add() {
        slc.add("hej");
        slc.add("java");
        slc.add("okej");
        slc.add("element");
        out.print(slc.head.element);


    }
}