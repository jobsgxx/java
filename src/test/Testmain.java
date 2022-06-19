package test;
import util.*;

public class Testmain {
    public static void main(String[] args) {
        ArrayBox testArray = new ArrayBox();
        testArray.add(10);
        testArray.remove(1);
        testArray.get(0);
        testArray.size();

        LinkedBox linkedArray = new LinkedBox();
        linkedArray.add(10);
        linkedArray.remove(1);
        linkedArray.get(0);
        linkedArray.size();
    }
}
