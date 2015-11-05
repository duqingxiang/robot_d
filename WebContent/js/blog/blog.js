
$("#publisBlog").click(function(){
	$("#editPanel").show();
	
});
$("#blogView").click(function(){
	$("#content").html("");
	$("#content").html(UE.getEditor('editor').getContent());
});
$("#blogClose").click(function(){
	$("#editPanel").hide();
});
$("#blogView").click(function(){
	var title = $("#blogTitle").val();
	var shortContent = $("#blogShort").val();
	var content = UE.getEditor('editor').getContent();
	
	var titleCookie = "robot_blog_title="+encodeURI(title);
	var shorCookie = "robot_blog_short="+encodeURI(shortContent);
	var contentCookie = "robot_blog_content="+encodeURI(content);
	
	document.cookie=titleCookie;
	document.cookie=shorCookie;
	document.cookie=contentCookie;
	window.open("/blog/toView.do");
});
$("#blogSave").click(function(){
	
	var title = $("#blogTitle").val();
	var theme = $("#blogTheme").val();
	var type = $("#blogType").val();
	var shortContent = $("#blogShort").val();
	if (title == null || title == "" || theme == null || type == null || shortContent == null || shortContent == "") {
		alert("参数为空，请仔细检查哦~~~");
		return ;
	}
	var editor = UE.getEditor('editor');
	if ( !editor.hasContents() ) {
		alert("还未写正文哦！~");
		return ;
	}
	var content = editor.getContent();
	
	var param = {title:title,theme_id:theme,type:type,shortContent:shortContent,content:content};
	var url = "/blog/publishBlog.do";
	jQuery.post(url,param,function(data){
		if (data == 0) {
			refush();
		} else if(data == -1) {
			alert("有参数未填写完整！~");
		} else if(data == -2) {
			alert("未登录~！");
		} else if(data == -3) {
			alert("您暂时不是作家哦！~");
		} else if(data == -4){
			alert("非私有主题不能发布私有文章哦,或私有主题不能发布公开文章哦！~");
		}else if(data == -5) {
			alert("系统异常！~");
		}
		
	});
	
});

$("#theme_add").click(function(){
	
	var title = $("#theme_title").val();
	var type = $("#theme_type").val();
	var style = $("#theme_style").val();
	
	var param = {title:title,type:type,style:style};
	var url = "/blog/addTheme.do";
	jQuery.post(url,param,function(data){
		if (data == 0) {
			refush();
		} else if(data == -1) {
			alert("有参数未填写完整！~");
		} else if(data == -2) {
			alert("未登录~！");
		} else if(data == -3) {
			alert("您暂时不是作家哦！或公开模式的主题只有管理员能够添加哦~");
		} else if(data == -4){
			alert("非私有主题不能发布私有文章哦！~");
		}else if(data == -5) {
			alert("系统异常！~");
		}
		
	});
	
});
function deleteTheme(id){
	
	if (confirm("您确定要删除该主题么？删除主题会将该主题下所有文章一并删除，您确定么？")){
		var param = {id:id};
		var url = "/blog/deleteTheme.do";
		jQuery.post(url,param,function(data){
			if (data == 0) {
				refush();
			} else if(data == -1) {
				alert("有参数未填写完整！~");
			} else if(data == -2 || data == -4) {
				alert("未登录~！");
			} else if(data == -3) {
				alert("您暂时不是作家或管理员哦！~");
			} else if(data == -5){
				alert("该文章暂不存在，或您非文章作者...");
			}else if(data == -6) {
				alert("系统异常！~");
			}
			
		});
	};
	
	
	
}
function deleteBlog(id){
	
	var param = {id:id};
	var url = "/blog/deleteBlog.do";
	jQuery.post(url,param,function(data){
		if (data == 0) {
			refush();
		} else if(data == -1) {
			alert("有参数未填写完整！~");
		} else if(data == -2) {
			alert("未登录~！");
		} else if(data == -3) {
			alert("您暂时不是作家或管理员哦！~");
		} else if(data == -4){
			alert("该文章暂不存在，或您非文章作者...");
		}else if(data == -5) {
			alert("系统异常！~");
		}
		
	});
	
}

/****--------------------修改文章--------------------------***/
$("#updateBtn").click(function(){
	var content =$("#contentTemp").val();
	UE.getEditor('editor').execCommand('insertHtml',content );
	$("#editPanel").show();
});
$("#updateBlogView").click(function(){
	var title = $("#updateBlogTitle").val();
	var short = $("#updateBlogShort").val();
	var editor = UE.getEditor('editor');
	var content = editor.getContent();
	
	$("#viewTitle").html(title);
	$("#viewShort").html(short);
	$("#viewContent").html(content);
	
});
$("#updateBlogSave").click(function(){
	var editor = UE.getEditor('editor');

	var id = $("#updateBlogId").val();
	var title = $("#updateBlogTitle").val();
	var short = $("#updateBlogShort").val();
	var content = editor.getContent();
	var theme = $("#updateBlogTheme").val();
	var type = $("#updateBlogType").val();
	
	var param = {id:id,title:title,theme_id:theme,type:type,shortContent:short,content:content};
	var url = "/blog/updateBlog.do";
	jQuery.post(url,param,function(data){
		if (data == 0) {
			refush();
		} else if(data == -1) {
			alert("有参数未填写完整！~");
		} else if(data == -2) {
			alert("未登录~！");
		} else if(data == -3) {
			alert("您暂时不是作家哦！~");
		} else if(data == -4){
			alert("非私有主题不能发布私有文章哦,或私有主题不能发布公开文章哦！~");
		} else if(data == -4){
			alert("该文章不存在，或非原作者修改！~");
		}else if(data == -6) {
			alert("系统异常！~");
		}
		
	});
	
});

/**-------------------评论-------------------*/
function showInput(id){
	$("#commont_input").show();
	$("#commont_blog_repty_id").val(id);
	
}
function closeInput(){
	$("#commont_input").hide();
	$("#commont_blog_repty_id").val("");
	
}
function deleteCommont(id){
	var url = "/blog/deleteCommont.do";
	var param = {id:id};
	$.post(url,param,function(data){
		if (data == 0) {
			refush();
		} else if(data == -1) {
			alert("有参数未填写完整！~");
		} else if(data == -2) {
			alert("未登录~！");
		} else if (data == -3) {
			alert("该评论不存在哦！~");
		} else if (data == -4) {
			alert("您无权限删除此评论哦！~");
		}else {
			alert("系统异常！~");
		}
	});
}
$("#commontSave").click(function(){
	var editor = UE.getEditor('editor');
	if ( !editor.hasContents() ) {
		alert("还未写评论哦！~");
		return ;
	}
	var repty_user_id = $("#commont_blog_repty_id").val();
	var content = editor.getContent();
	var blog_id =  $("#commont_blog_id").val();
	var url = "/blog/saveCommont.do";
	var param = {repty_user_id:repty_user_id,blog_id:blog_id,content:content};
	$.post(url,param,function(data){
		if (data == 0) {
			refush();
		} else if(data == -1) {
			alert("有参数未填写完整！~");
		} else if(data == -2) {
			alert("未登录~！");
		} else{
			alert("系统异常！~");
		}
	});
});
