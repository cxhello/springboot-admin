package com.cxhello.admin.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cxhello
 * @create 2020/1/4 19:30
 */
@Data
@NoArgsConstructor
public class ZtreeView {

    private Long id;

    private Long pId;

    private String name;

    private boolean open;

    private boolean checked = false;

    public ZtreeView(Long id, Long pId, String name, boolean open) {
        super();
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.open = open;
    }
}
