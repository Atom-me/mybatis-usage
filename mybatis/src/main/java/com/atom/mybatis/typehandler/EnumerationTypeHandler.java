package com.atom.mybatis.typehandler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 枚举handler
 *
 * @param <E> 继承 {@link IEnumeration}
 * @author atom
 */
@Slf4j
public class EnumerationTypeHandler<E extends IEnumeration> extends BaseTypeHandler<E> {

    private final Class<E> type;

    public EnumerationTypeHandler(Class<E> type) {
        if (!IEnumeration.class.isAssignableFrom(type)) {
            throw new IllegalArgumentException(type.getSimpleName() + " type argument does not implement IEnumeration");
        }
        this.type = type;
    }


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.value());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Integer value = (Integer) rs.getObject(columnName);
        return getValue(value);

    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer value = (Integer) rs.getObject(columnIndex);
        return getValue(value);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer value = (Integer) cs.getObject(columnIndex);
        return getValue(value);
    }

    private E getValue(Integer value) {
        if (value == null) {
            return null;
        }
        return this.forCode(type, value);
    }

    private E forCode(Class<E> type, Integer value) {
        E[] enumConstants = type.getEnumConstants();
        for (E e : enumConstants) {
            if (e.value() == value) {
                return e;
            }
        }
        return null;
    }
}