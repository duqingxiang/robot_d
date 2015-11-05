
var rowIndex = 1;

$(".field_add").click(function(){
	
	var field = {};
	
	var name = $("#field_name").val();
	if (name == null || name == "") {
		alert("名称不能为空");
		return;
	}
	var type = $("#field_type").val();
	if (type == -1) {
		alert("请选择类型");
		return;
	}
	var length = $("#field_length").val();
	var decimals = $("#field_decimals").val();
	var default_value = $("#field_default").val();
	var comment = $("#field_comment").val();
	
	var key = $("#field_key").is(':checked');
	var allow_null = $("#field_allow_null").is(':checked');
	var auto_increment = $("#field_auto_increment").is(':checked');
	var unsigned = $("#field_unsigned").is(':checked');
	var zerofill = $("#field_zerofill").is(':checked');
	
	var innerRowIndex = $("#rowIndex").val();
	
	field.rowIndex = rowIndex;
	field.name=name;
	field.type=type;
	field.typeStr=$("#field_type").find("option:selected").text();
	field.length=length;
	field.decimals=decimals;
	field.default_value=default_value;
	field.comment=comment;
	field.key=key;
	field.allow_null=allow_null;
	field.auto_increment=auto_increment;
	field.unsigned=unsigned;
	field.zerofill=zerofill;
	
	
	//alert(JSON.stringify(field));
	var html = add_field_html(field);
	if (innerRowIndex != null && innerRowIndex != ""){
		$("#field_row_"+innerRowIndex).after(html);
		$("#field_row_"+innerRowIndex).remove();
	} else {
		$("#field_list").append(html);
		
	}
	cleanForm();
	rowIndex++;
});

function add_field_html(field){
	
	var html = "<tr id=\"field_row_"+field.rowIndex+"\">";
	html+="<td>"+field.name+"</td>";
	html += "<td>"+field.typeStr+"</td>";
	html += "<td>"+field.length+"</td>";
	html += "<td>"+field.decimals+"</td>";
	html += "<td>"+field.allow_null+"</td>";
	html += "<td>"+field.default_value+"</td>";
	html += "<td>"+field.auto_increment+"</td>";
	html += "<td>"+field.unsigned+"</td>";
	html += "<td>"+field.zerofill+"</td>";
	html += "<td>"+field.key+"</td>";
	html += "<td>"+field.comment+"</td>";
	html += "<td>";
	html += "<a class=\"field_move_up\"><em class=\"glyphicon glyphicon-arrow-up\"></em></a>&nbsp;&nbsp;";
	html += "<a class=\"field_move_down\"><em class=\"glyphicon glyphicon-arrow-down\"></em></a>&nbsp;&nbsp;";
	html += "<a class=\"field_edit\"><em class=\"glyphicon glyphicon-pencil\"></em></a>&nbsp;&nbsp;";
	html += "<a class=\"field_remove\"><em class=\"glyphicon glyphicon-remove\"></em></a>";
	html += "</td>";
	html += "<input type=\"hidden\" class=\"field_json_value\" value=\""+encodeURI(JSON.stringify(field))+"\"/>";
	html += "</tr>";
	return html;
}

function cleanForm(){
	$("#field_name").val("");
	$("#field_type").val(-1);
	$("#field_length").val("");
	$("#field_decimals").val("");
	$("#field_default").val("");
	$("#field_comment").val("");
	
	$("#field_key").prop("checked",false);
	$("#field_allow_null").prop("checked",false);
	$("#field_auto_increment").prop("checked",false);
	$("#field_unsigned").prop("checked",false);
	$("#field_zerofill").prop("checked",false);
	$("#rowIndex").val("");
}
$(document).on("click",".field_move_up",function(){
	var tr = $(this).parent().parent();
	var prev = $(tr).prev();
	if (prev.length == 0) {
		alert("已是第一条数据，不能继续上移！");
		return;
	}
	$(prev).before(tr);
});

$(document).on("click",".field_move_down",function(){
	var tr = $(this).parent().parent();
	var next = $(tr).next();
	if (next.length == 0) {
		alert("已是最后一条数据，不能继续下移！");
		return;
	}
	$(next).after(tr);
});

$(document).on("click",".field_remove",function(){
	$(this).parent().parent().remove();
});

$(document).on("click",".field_edit",function(){
	var field =eval('(' + decodeURI($(this).parent().parent().find(".field_json_value").val()) + ')');
	if (field != null) {
		
		$("#field_name").val(field.name);
		$("#field_type").val(field.type);
		$("#field_length").val(field.length);
		$("#field_decimals").val(field.decimals);
		$("#field_default").val(field.default_value);
		$("#field_comment").val(field.comment);
		
		if (field.key){
			$("#field_key").prop("checked",true);
		}
		if (field.allow_null){
			$("#field_allow_null").prop("checked","checked");
		}
		if (field.auto_increment) {
			$("#field_auto_increment").prop("checked",true);
		}
		if (field.unsigned) {
			$("#field_unsigned").prop("checked",true);
		}
		if (field.zerofill) {
			$("#field_zerofill").prop("checked",true);
		}
		$("#rowIndex").val(field.rowIndex);
	}
});

$(".create_table").click(function(){
	var table_name = $("#table_name").val();
	if (table_name == null || table_name == "") {
		alert("表名为空！~");
		return;
	}
	
	var arr = $("#field_list").find(".field_json_value");
	var array = new Array();
	$(arr).each(function(){
		array.push(decodeURI($(this).val()));
	});
	
	
	var str = array.join(",");
	var url = "/tool/createTableSql.do";
	var param = {table_name:table_name,array:JSON.stringify(array)};
	$.post(url,param,function(data){
		var res = eval('(' + data + ')');
		if (res == null){
			alert("系统异常！");
			return;
		}
		if (res.code == 1) {
			$("#show_sql").removeClass("alert alert-danger").addClass("alert alert-success").html(res.sql);
			$("#show_sql").show();
		} else if (res.code == -1 ||res.code == -2) {
			showErrorTigs("失败：有字段类型错误，请仔细检查！~");
		} else if(res.code == -3) {
			showErrorTigs("失败：有字段未命名，或命名为空，请仔细检查！~");
		} else if(res.code == -4) {
			showErrorTigs("失败：有且只有一个自增列！~");
		} else if(res.code == -51) {
			showErrorTigs("失败：表名为空！~");
		} else if(res.code == -52) {
			showErrorTigs("失败：无字段信息！~");
		}
	});
});

function showErrorTigs(content){
	$("#show_sql").removeClass("alert alert-success").addClass("alert alert-danger");
	$("#show_sql").html(content);
	$("#show_sql").show();
}
