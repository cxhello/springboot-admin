package com.cxhello.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author CaiXiaoHui
 * @create 2019/11/12 20:30
 */
@Data
@Table(name = "tb_resource")
public class Resource {

    /**
     * 资源id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源唯一标识
     */
    private String sourceKey;

    /**
     * 资源类型,0:目录;1:菜单;2:按钮
     */
    private Integer type;

    /**
     * 资源url
     */
    private String sourceUrl;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否隐藏
     *
     * 0显示 1隐藏
     */
    private Integer isHide;

    /**
     * 描述
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

    private Integer parentId;

    private Resource parentResource;
}
