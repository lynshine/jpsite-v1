package com.mty.jpsite.domain;
import java.util.Date;

/* 
* 
* gen by beetlsql 2018-10-29
*/
public class RoleMenu  {
	
	//主键ID
	private Integer id ;
	//菜单ID
	private Integer menuId ;
	//角色ID
	private Integer roleId ;
	//创建时间
	private Date createAt ;
	//修改时间
	private Date updateAt ;
	
	public RoleMenu() {
	}
	
	/**主键ID
	*@return 
	*/
	public Integer getId(){
		return  id;
	}
	/**主键ID
	*@param  id
	*/
	public void setId(Integer id ){
		this.id = id;
	}
	
	/**菜单ID
	*@return 
	*/
	public Integer getMenuId(){
		return  menuId;
	}
	/**菜单ID
	*@param  menuId
	*/
	public void setMenuId(Integer menuId ){
		this.menuId = menuId;
	}
	
	/**角色ID
	*@return 
	*/
	public Integer getRoleId(){
		return  roleId;
	}
	/**角色ID
	*@param  roleId
	*/
	public void setRoleId(Integer roleId ){
		this.roleId = roleId;
	}
	
	/**创建时间
	*@return 
	*/
	public Date getCreateAt(){
		return  createAt;
	}
	/**创建时间
	*@param  createAt
	*/
	public void setCreateAt(Date createAt ){
		this.createAt = createAt;
	}
	
	/**修改时间
	*@return 
	*/
	public Date getUpdateAt(){
		return  updateAt;
	}
	/**修改时间
	*@param  updateAt
	*/
	public void setUpdateAt(Date updateAt ){
		this.updateAt = updateAt;
	}
	

}
