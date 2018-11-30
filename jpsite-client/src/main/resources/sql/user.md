sample
===
	select #use("cols")# from user  where  #use("condition")#

cols
===
	id,name,login_name,login_password,create_at,update_at

updateSample
===
	update user set id=#id#,name=#name#,login_name=#loginName#,login_password=#loginPassword#,update_at=#updateAt# 
	where #use("condition")#

condition
===
	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(loginName)){
	 and login_name=#loginName#
	@}
	@if(!isEmpty(loginPassword)){
	 and login_password=#loginPassword#
	@}
	@if(!isEmpty(createAt)){
	 and create_at=#createAt#
	@}
	@if(!isEmpty(updateAt)){
	 and update_at=#updateAt#
	@}