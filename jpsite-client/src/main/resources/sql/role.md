sample
===
* 注释

	select #use("cols")# from role ${tableName}.md where  #use("condition")#

cols
===
	${tableName}.md.id,${tableName}.md.name,${tableName}.md.create_at,${tableName}.md.update_at

updateSample
===
	
	${tableName}.md.id=#id#,${tableName}.md.name=#name#,${tableName}.md.create_at=#createAt#,${tableName}.md.update_at=#updateAt#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and ${tableName}.md.id=#id#
	@}
	@if(!isEmpty(name)){
	 and ${tableName}.md.name=#name#
	@}
	@if(!isEmpty(createAt)){
	 and ${tableName}.md.create_at=#createAt#
	@}
	@if(!isEmpty(updateAt)){
	 and ${tableName}.md.update_at=#updateAt#
	@}
	
	