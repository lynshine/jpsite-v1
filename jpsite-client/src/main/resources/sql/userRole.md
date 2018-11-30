sample
===
* 注释

	select #use("cols")# from user_role ${tableName}.md where  #use("condition")#

cols
===
	${tableName}.md.id,${tableName}.md.user_id,${tableName}.md.role_id,${tableName}.md.create_at,${tableName}.md.update_at

updateSample
===
	
	${tableName}.md.id=#id#,${tableName}.md.user_id=#userId#,${tableName}.md.role_id=#roleId#,${tableName}.md.create_at=#createAt#,${tableName}.md.update_at=#updateAt#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and ${tableName}.md.id=#id#
	@}
	@if(!isEmpty(userId)){
	 and ${tableName}.md.user_id=#userId#
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
	
	