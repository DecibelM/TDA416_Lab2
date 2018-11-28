import static java.lang.System.out;
import static org.junit.Assert.*;

public class SLCWithGetTest {

    SLCWithGet<String> slc = new SLCWithGet<>();

    @org.junit.Test
    public void get() {
        slc.add("hej");
        slc.add("java");
        slc.add("java");
        out.println(slc.get("java"));
        out.println(slc.get("element"));

    }

    @org.junit.Test
    public void add() {
        slc.add("hej");
        slc.add("java");
        slc.add("okej");
        slc.add("element");
        slc.add ("java");
        out.print(slc.head.element);


    }
}