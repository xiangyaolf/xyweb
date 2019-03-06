package com.zhst.utils;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * sql 字符串工具类
 */
public class SqlUtil {

    /**
     * 根据List 拼装 in 子句
     * @param paramList
     * @return
     */
    public static String getInSubStringByList(List<String> paramList) {
        StringBuilder sql = new StringBuilder();
        sql.append("(");
        if (CollectionUtils.isEmpty(paramList)) {
            sql.append("'')");
            return sql.toString();
        } else {
            for (String param : paramList) {
                if (paramList.indexOf(param) > 0) {
                    sql.append(",");
                }
                sql.append("'").append(param).append("'");
            }
        }
        sql.append(")");
        return sql.toString();
    }
}
