package ba.unsa.etf.rpr.Exceptions;


public class StatementException extends Exception{
    public StatementException(String problem, Exception reason){
        super(problem, reason);
    }
    public StatementException(String problem){
        super(problem);
    }
}
