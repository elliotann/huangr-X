package com.easysoft.member.backend.model;

import com.easysoft.core.model.Resource;
import com.easysoft.framework.db.NotDbField;

import javax.persistence.*;
import java.util.List;


/**
 * @author andy
 * @version 1.0
 */
@Entity
@Table(name="t_menu")
public class Menu {
    private Integer id;

    private Integer deleteflag = 0;

    private String productId;


    public Integer getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }


    @NotDbField
    @Transient
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
	private Integer pid;
	private String title;
	private String url;
	private String target;
	private Integer sorder;
	private Integer menutype;
	private String datatype;
	private String appid;
	

	private List<Menu> children;
	private boolean hasChildren ;
    private String ico;

	@NotDbField
    @Transient
	public boolean getHasChildren() {
		hasChildren = this.children==null|| this.children.isEmpty() ?false:true;
		return hasChildren;
	}

	
	public  static final int MENU_TYPE_SYS = 1;
	public static final int MENU_TYPE_APP = 2;
	public static final int MENU_TYPE_EXT = 3;
	
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	private Integer selected;
	
	public Integer getSelected() {
		return selected;
	}
	public void setSelected(Integer selected) {
		this.selected = selected;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Integer getSorder() {
		return sorder;
	}
	public void setSorder(Integer sorder) {
		this.sorder = sorder;
	}
	public Integer getMenutype() {
		return menutype;
	}
	public void setMenutype(Integer menutype) {
		this.menutype = menutype;
	}
    @Transient
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }
}