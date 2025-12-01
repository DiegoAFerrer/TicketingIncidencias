import java.util.ArrayList;
import java.util.Iterator;

public class Test{
    public static void main(String[] args) {
        ArrayList<String> cats = new ArrayList<>();
        cats.add("freckle");
        cats.add("rocky");
        cats.add("ivy");
        cats.add("mitzi");
        cats.add("zib");
        cats.add("sy");
        cats.add("viktor");

        Iterator<String> it = cats.iterator();
        while (it.hasNext()) {
            String string = it.next();
            if(string.equals("freckle")){
                System.out.println(string + "<3");
            } else{
                System.err.println(string);
            }
        }
    }
}