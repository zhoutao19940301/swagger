package com.talkweb.edu.index.entity.dw;

import java.io.Serializable;
import javax.persistence.*;

@ApiModel( description = "字典类型")
@Data
@ToString
@Table(name = "sys_zdlx")
public class SysZdlx implements Serializable {
    @ApiModelProperty(value = "字典类型ID" )
    @Id
    @Column(name = "ZDLX_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zdlxId;

    @ApiModelProperty(value = "字典类型名称" )
    @Column(name = "ZDLXMC")
    private String zdlxmc;

    @ApiModelProperty(value = "字典类型代码" )
    @Column(name = "ZDLXDM")
    private String zdlxdm;

    private static final long serialVersionUID = 1L;
}