package util;

import javax.swing.*;

public class BoxIndexOutOfBoundExecption extends RuntimeException{
    public BoxIndexOutOfBoundExecption(){}
    public BoxIndexOutOfBoundExecption(String msg){
        super(msg);
    }
}
