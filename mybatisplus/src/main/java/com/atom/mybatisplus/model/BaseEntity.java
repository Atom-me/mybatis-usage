package com.atom.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Atom
 */
@Data
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
}
