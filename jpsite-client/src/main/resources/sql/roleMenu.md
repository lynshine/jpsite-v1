sample
===
* 注释

	select #use("cols")# from role_menu ${tableName}.md where  #use("condition")#

cols
===
	${tableName}.md.id,${tableName}.md.menu_id,${tableName}.md.role_id,${tableName}.md.create_at,${tableName}.md.update_at

updateSample
===
	
	${tableName}.md.id=#id#,${tableName}.md.menu_id=#menuId#,${tableName}.md.role_id=#roleId#,${tableName}.md.create_at=#createAt#,${tableName}.md.update_at=#updateAt#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and ${tableName}.md.id=#id#
	@}
	@if(!isEmpty(menuId)){
	 and ${tableName}.md.menu_id=#menuId#
	@}
	@if(!isEmpty(roleId)){
	 and ${tableName}.md.role_id=#roleId#
	@}
	@if(!isEmpty(createAt)){
	 and ${tableName}.md.create_at=#createAt#
	@}
	@if(!isEmpty(updateAt)){
	 and ${tableName}.md.update_at=#updateAt#
	@}
	
	