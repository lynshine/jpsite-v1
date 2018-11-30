sample
===
	select #use("cols")# from menu  where  #use("condition")#

cols
===
	id,name,url,create_at,update_at

updateSample
===
	
	update menu set id=#id#,name=#name#,url=#url#,create_at=#createAt#,update_at=#updateAt# where  #use("condition")#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(url)){
	 and url=#url#
	@}
	@if(!isEmpty(createAt)){
	 and create_at=#createAt#
	@}
	@if(!isEmpty(updateAt)){
	 and update_at=#updateAt#
	@}