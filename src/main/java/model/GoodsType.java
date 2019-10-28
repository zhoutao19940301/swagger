package model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@ApiModel( description = "商品分类表")
@Table(name = "goods_type")
public class GoodsType implements Serializable {
    @ApiModelProperty(value = "商品分类id" )
    @Id
    @Column(name = "id")
    private Integer id;

    @ApiModelProperty(value = "分类名称" )
    @Column(name = "cat_name")
    private String catName;

    @ApiModelProperty(value = "分类描述" )
    @Column(name = "cat_desc")
    private String catDesc;

    @ApiModelProperty(value = "分类的关键字,可能是为了搜索" )
    @Column(name = "keywords")
    private String keywords;

    @ApiModelProperty(value = "该分类的父类ID,取值于该表的id字段" )
    @Column(name = "parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "该分类在页面显示的顺序,数字越大顺序越靠后,同数字,id在前的先显示" )
    @Column(name = "sort_order")
    private Integer sortOrder;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDesc() {
        return catDesc;
    }

    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", catName=").append(catName);
        sb.append(", catDesc=").append(catDesc);
        sb.append(", keywords=").append(keywords);
        sb.append(", parentId=").append(parentId);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}