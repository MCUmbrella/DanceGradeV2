package vip.floatationdevice.dancegrade.exception;

public class UnexpectedAffectedRowsException extends RuntimeException
{
    public UnexpectedAffectedRowsException()
    {
        super("The number of affected data didn't meet the expectation");
    }

    public UnexpectedAffectedRowsException(String message)
    {
        super(message);
    }
}
