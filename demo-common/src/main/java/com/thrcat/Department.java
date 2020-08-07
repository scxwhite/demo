package com.thrcat;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * desc:
 *
 * @author scx
 * @create 2020/08/05
 */
@TableName("department")
public class Department {

    private Integer id;
    /**
     * 部门名称
     */
    private String name;

    /**
     * 管理员用户名
     */
    private String username;
    /**
     * 管理员密码名
     */
    private String password;

    /**
     * 管理员邮箱
     */
    private String email;

    /**
     * 是否有效
     */
    @TableLogic
    private Integer isValid;

    /**
     * 管理员电话
     */
    private String phone;

    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
