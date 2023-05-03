package vip.floatationdevice.dancegrade.data.http;

public class CommonMappedResult<T>
{
    private final int code;
    private final String message;
    private final T data;

    public CommonMappedResult(int code, String message, T data)
    {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonMappedResult(int code, String message)
    {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public int getCode()
    {
        return code;
    }

    public String getMessage()
    {
        return message;
    }

    public T getData()
    {
        return data;
    }
}
