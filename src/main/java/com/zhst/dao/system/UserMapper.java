package com.zhst.dao.system;

import com.zhst.model.system.SysMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    /**
     * 查找用户
     * @param userName
     * @return
     */
    @Select({"select username,pwd from sys_user where username=#{username}"})
    Map<String, String> queryUserByUsername(@Param("username") String userName);

    /**
     * 验证用户名密码是否正确
     * @param username
     * @param pwd
     * @return
     */
    @Select({"select count(1) from sys_user where username=#{username} and pwd=#{pwd}"})
    int validUserByUsernameAndPwd(@Param("username") String username, @Param("pwd") String pwd);

    /**
     * 加载用户权限
     * @param username
     * @return
     */
    @Select({"select c.role_code from sys_user a inner join sys_user_role b on a.id=b.user_id \n" +
            "inner join sys_role c on b.role_id=c.id where a.username=#{username};"})
    List<String> loadAuthorityByUsername(@Param("username") String username);

    /**
     * 加载用户的菜单
     * @param username
     * @return
     */
    @Select({"select d.*  from sys_user a inner join sys_user_role b on a.id=b.user_id \n" +
            "inner join sys_role_menu c on b.role_id=c.role_id \n" +
            "inner join sys_menu d on c.menu_id=d.id \n" +
            "where a.username=#{username}"})
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="name",property="name"),
            @Result(column="parent_id",property="parentId"),
            @Result(column="parent_name",property="parentName"),
            @Result(column="icon",property="icon"),
            @Result(column="url",property="url")
    })
    List<SysMenu> loadMenueByUsername(@Param("username") String username);

}
