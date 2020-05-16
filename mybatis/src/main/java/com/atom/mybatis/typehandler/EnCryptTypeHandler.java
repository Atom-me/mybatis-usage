package com.atom.mybatis.typehandler;

import com.atom.mybatis.utils.AesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 加密/解密Handler
 *
 * @author Atom
 */
@Slf4j
public class EnCryptTypeHandler extends BaseTypeHandler<String> {

    /**
     * 加密
     * 如果加密失败，抛出sql异常，阻止持久化操作
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        try {
            if (StringUtils.isNotEmpty(parameter)) {
                String encrypt = AesUtil.encryptBase64UseDefaultPWD(parameter);
                ps.setString(i, encrypt);
            } else {
                ps.setString(i, parameter);
            }
        } catch (Exception e) {
            //加密出现异常
            log.error("encrypt fail .:{}", e);
            throw new SQLException(e);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return getResult(value);
    }

    private String getResult(String value) {
        try {
            if (StringUtils.isNotEmpty(value)) {
                return AesUtil.decryptBase64UseDefaultPWD(value);
            }
        } catch (Exception e) {
            log.error("decrypt fail . :{}", e);
            return value;
        }
        return value;
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return getResult(value);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return getResult(value);
    }

}