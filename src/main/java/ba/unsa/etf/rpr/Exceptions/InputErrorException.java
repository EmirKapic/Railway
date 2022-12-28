package ba.unsa.etf.rpr.Exceptions;

public class InputErrorException extends Exception{
    public InputErrorException(String problem, Exception reason){
        super(problem, reason);
    }
    public InputErrorException(String problem){
        super(problem);
    }
}
