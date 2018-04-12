<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试</title>
<script type="text/javascript" src="/jq/jquery-3.3.1.min.js"></script>
<script>
$(function(){
	   alert('${agentCode}')
	   alert('${orderUrl}')
	   window.location.href="https://"+'${orderUrl}'+"?agentCode="+'${agentCode}'
})
	/*  function submit1(){ */

	/* 方法一 测试可以把checkbox值传到list 
	console.info($('#form1').serializeArray()) 对象形式 
	 console.info($('#form1').serialize()) 
	$.ajax({
	method:'post',
	url:'/mvc/m1',
	data:$('#form1').serialize(), */
	/* data:$('#form1').serializeArray(), 对象形式发送到后台*/
	/* 	success:function(){
			alert('ok')
		}
	 })
	} */
	//方法二 测试传到两个对象 两个对象有相同名字的属性 成功
	/*  function submit2(){
	 console.info($('#form1').serializeArray()) 
	 $.ajax({
	 method:'post',
	 url:'/mvc/m1',
	
	 data:$('#form1').serializeArray(), 
	 success:function(){
	 alert('ok')
	 }
	 })//方法三 测试传给对象属性的对象
	 } */
	 
	 //去掉serializeArray()的name和value
	 $.fn.serializeObject = function() {  
		 var o = {};    
		         var a = this.serializeArray();    
		         $.each(a, function() {    
		             if (o[this.name]) {    
		                 if (!o[this.name].push) {    
		                     o[this.name] = [ o[this.name] ];    
		                 }    
		                 o[this.name].push(this.value || '');    
		             } else {    
		                 o[this.name] = this.value || '';    
		             }    
		         });    
		         return o;    
		     };  
	 function submit() {
		 console.info($('#form1').serializeArray())
		 console.info($('#form1').serializeObject())
		 $.ajax({
            method : 'post',
            url : '/mvc/m1',
            dateType:'json',
            data : $('#form1').serializeArray(),
            success : function(data) {
                console.info(data)
            }
        })
	}
	 function test(){
		 $.ajax({
			 url:'/mvc/'+2222,
			 method:'post',
			 data:{
				 orderUrl:'25'
			 }
		 })
	 }
</script>
</head>
<body>
	<form id="form1">
		用户名: <input type="text" name="name"/> <br> 
		年    龄: <input type="text" name="age"/><br>
		
		<!-- 新增部分 -->
		用户名2: <input type="text" name="user2.name1"/> <br> 
		年    龄2: <input type="text" name="user2.age1"/><br>
		<!--UserBean和UserBean2 实体类都有相同name的属性  -->
	          兴    趣: <input type="checkbox" name="sport" value="1">足球
	           <input type="checkbox" name="sport" value="2">篮球<br>
	           <a onclick="test()">提交</a>
	</form>
</body>
</html>