package com.talkweb.edu.index.entity.dw;

import java.io.Serializable;
import javax.persistence.*;

@ApiModel( description = "字典数据")
@Data
@ToString
@Table(name = "sys_zdsj")
public class SysZdsj implements Serializable {
    @ApiModelProperty(value = "字典数据ID" )
    @Id
    @Column(name = "ZDSJ_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zdsjId;

    @ApiModelProperty(value = "字典类型ID" )
    @Column(name = "ZDLX_ID")
    private Long zdlxId;

    @ApiModelProperty(value = "字典数据名称" )
    @Column(name = "ZDSJMC")
    private String zdsjmc;

    @ApiModelProperty(value = "字典数据值" )
    @Column(name = "ZDSJZ")
    private String zdsjz;

    private static final long serialVersionUID = 1L;
}