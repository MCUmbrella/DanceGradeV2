package vip.floatationdevice.dancegrade.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vip.floatationdevice.dancegrade.component.CommonUtil;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class DoubleArrayTypeHandler extends BaseTypeHandler<double[]>
{
    @Autowired
    CommonUtil util;

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, double[] parameter, JdbcType jdbcType) throws SQLException
    {
        String value = Arrays.stream(parameter).mapToObj(Double::toString).collect(Collectors.joining(","));
        ps.setString(i, value);
    }

    @Override
    public double[] getNullableResult(ResultSet rs, String columnName) throws SQLException
    {
        String value = rs.getString(columnName);
        return util.str2DoubleArray(value);
    }

    @Override
    public double[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException
    {
        String value = rs.getString(columnIndex);
        return util.str2DoubleArray(value);
    }

    @Override
    public double[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException
    {
        String value = cs.getString(columnIndex);
        return util.str2DoubleArray(value);
    }
}
