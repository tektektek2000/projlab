package Utils;

public class AssertException extends Exception{
    String Message;

    public AssertException(String msg){
        Message = "Assert: " + msg;
    }


    @Override
    public String getMessage(){
        return Message;
    }
}
