package vip.floatationdevice.dancegrade.exception;

public class DataNotFoundException extends RuntimeException
{
    public DataNotFoundException()
    {
        super("The resource you requested was not found on the server");
    }

    public DataNotFoundException(String message)
    {
        super(message);
    }
}
