package com.atom.mybatis.typehandler;

import com.xxl.tool.emoji.EmojiTool;
import com.xxl.tool.emoji.encode.EmojiEncode;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * convert emoji use alias
 *
 * @author Atom
 */
public class EmojiTypeHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, EmojiTool.encodeUnicode(parameter, EmojiEncode.ALIASES));
    }

    /**
     * @param rs
     * @param columnName Column name, when configuration <code>useColumnLabel</code> is <code>false</code>
     */
    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (StringUtils.isNotBlank(rs.getString(columnName))) {
            return EmojiTool.decodeToUnicode(rs.getString(columnName), EmojiEncode.ALIASES);
        }
        return null;
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (StringUtils.isNotBlank(rs.getString(columnIndex))) {
            return EmojiTool.decodeToUnicode(rs.getString(columnIndex), EmojiEncode.ALIASES);
        }
        return null;
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (StringUtils.isNotBlank(cs.getString(columnIndex))) {
            return EmojiTool.decodeToUnicode(cs.getString(columnIndex), EmojiEncode.ALIASES);
        }
        return null;
    }
}
