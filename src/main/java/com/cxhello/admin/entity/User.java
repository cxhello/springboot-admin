package com.cxhello.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author CaiXiaoHui
 * @create 2019/11/10 12:38
 */
@Data
@Table(name = "tb_user")
public class User {

    /**
     * 用户id
     */
    @Id
    private Integer id;

    /**
     * 账户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 性别 0 女 1 男
     */
    private Integer sex;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 住址
     */
    private String address;

    /**
     * 逻辑删除状态 0 未删除 1 删除
     */
    private Integer deleteStatus;

    /**
     * 是否锁定
     *
     * 0 未锁定 1 锁定
     */
    private Integer locked;

    /**
     * 用户描述
     */
    private String description;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 用户对应的角色
     */
    private List<Role> roleList;
}
