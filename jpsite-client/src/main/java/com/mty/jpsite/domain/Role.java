package com.mty.jpsite.domain;
import java.util.Date;

/* 
* 
* gen by beetlsql 2018-10-29
*/
public class Role  {
	
	//主键ID
	private Integer id ;
	//角色名
	private String name ;
	//创建时间
	private Date createAt ;
	//修改时间
	private Date updateAt ;
	
	public Role() {
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
	
	/**角色名
	*@return 
	*/
	public String getName(){
		return  name;
	}
	/**角色名
	*@param  name
	*/
	public void setName(String name ){
		this.name = name;
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
