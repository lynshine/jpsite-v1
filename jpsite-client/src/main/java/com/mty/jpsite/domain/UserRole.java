package com.mty.jpsite.domain;
import java.util.Date;

/* 
* 
* gen by beetlsql 2018-10-29
*/
public class UserRole  {
	
	//主键ID
	private Integer id ;
	//角色ID
	private Integer roleId ;
	//用户ID
	private Integer userId ;
	//创建时间
	private Date createAt ;
	//修改时间
	private Date updateAt ;
	
	public UserRole() {
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
	
	/**用户ID
	*@return 
	*/
	public Integer getUserId(){
		return  userId;
	}
	/**用户ID
	*@param  userId
	*/
	public void setUserId(Integer userId ){
		this.userId = userId;
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
