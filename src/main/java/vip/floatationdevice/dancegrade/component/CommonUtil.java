package vip.floatationdevice.dancegrade.component;

import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

@Component
public class CommonUtil
{
    public double[] str2DoubleArray(String s)
    {
        if(s == null || s.isBlank())
            return new double[0];
        String[] ss = s.split(",");
        double[] a = new double[ss.length];
        for(int i = 0; i < ss.length; i++)
            a[i] = Double.parseDouble(ss[i]);
        return a;
    }

    public int[] str2IntArray(String s)
    {
        if(s == null || s.isBlank())
            return new int[0];
        String[] ss = s.split(",");
        int[] a = new int[ss.length];
        for(int i = 0; i < ss.length; i++)
            a[i] = Integer.parseInt(ss[i]);
        return a;
    }

    public String getStackTraceAsString(Throwable t)
    {
        StringWriter esw = new StringWriter();
        t.printStackTrace(new PrintWriter(esw));
        return esw.toString();
    }
}
