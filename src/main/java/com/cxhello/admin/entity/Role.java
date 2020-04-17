package com.cxhello.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author cxhello
 * @create 2019/11/11 20:40
 */
@Data
@Table(name = "tb_role")
public class Role {

    /**
     * 角色id
     */
    @Id
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色key
     */
    private String roleKey;

    /**
     * 角色状态,0：正常；1：删除
     */
    private Integer status;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;

    /**
     * 角色对应的资源
     */
    private List<Resource> resourceList;
}
