
/**
 * 登录页切换标签
 */
$(".change-tab").click(function(){
	$(this).parent().parent().children().removeClass("active");
	$(this).parent().addClass("active");
	$(".loginClass").hide();
	var div = $(this).attr("target_div");
	$("#"+div).show();
});

/**
 *	登录事件
 */

$("#login_submit").click(function(){
	var username = $("#login_name").val();
	var password = $("#login_password").val();
	if(username == null || username == ""){
		loginAlertTag(0,"Username not allow empty...");
		return ;
	}
	
	if(password == null || password == ""){
		loginAlertTag(0,"Password not allow empty...");
		return ;
	}
	
	var url = "/login.do";
	var param={username:username,password:password};
	$.post(url,param,function(data){
		
		if(data == -1){
			loginAlertTag(0,"Username or Password is emputy...");
		}else if(data == -2){
			loginAlertTag(0,"Account freezed...");
		}else if(data == 1){
			
			var url = $("#forwardUrl").val();
			if (url != null && url != "") {
				window.location.href = url;
			}else {
				window.location.href="/test.do";
			}
			
		}
	});
	
});

$("#reg_submit").click(function(){
	var username = $("#reg_name").val();
	var password = $("#reg_password").val();
	var password2 = $("#reg_password_again").val();
	var nickname = $("#nick_name").val();
	if(username == null || username == ""){
		loginAlertTag(1,"Username not allow empty...");
		return ;
	}
	
	if(password == null || password == ""){
		loginAlertTag(1,"Password not allow empty...");
		return ;
	}
	
	if(password2 == null || password2 == ""){
		loginAlertTag(1,"Password Again not allow empty...");
		return ;
	}
	
	if (password != password2) {
		loginAlertTag(1,"Password twice difference...");
		return ;
	}
	
	var url = "/register.do";
	var param={reg_username:username,reg_password:password,reg_password2:password2,nick_name:nickname};
	jQuery.post(url,param,function(data){
		
		if(data == -1){
			loginAlertTag(1,"Username or Password is emputy...");
		}else if(data == -2){
			loginAlertTag(1,"Password twice difference...");
		}else if(data == -3){
			loginAlertTag(1,"Username exixt...");
		}else if(data == -4){
			loginAlertTag(1,"Register error...");
		}else if(data == 1){
			window.location.href="/test.do";
		}
	});
	
});

$("#logout_btn").click(function(){
	var url = "/logout.do";
	jQuery.post(url,null,function(data){
		window.location.href="/test.do";
	});
});

function loginAlertTag(type,content,sys){
	var tagId = "login_tag";
	if(type == 1){
		tagId = "reg_tag";
	}
	$("#"+tagId).html(content);
	
	if(sys != 999){
		setTimeout('loginAlertTag('+type+',"",999)', 3000);
	}
}

function refush(){
	var href = window.location.href;
	href = href.replace("#", "");
	window.location.href =href;
}