package com.wisest.generate.dao;

import com.wisest.generate.model.SysUser;
import org.apache.ibatis.jdbc.SQL;

public class SysUserSqlProvider {

    public String insertSelective(SysUser record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sys_menu");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getParentId() != null) {
            sql.VALUES("parent_id", "#{parentId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SysUser record) {
        SQL sql = new SQL();
        sql.UPDATE("sys_menu");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getParentId() != null) {
            sql.SET("parent_id = #{parentId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}