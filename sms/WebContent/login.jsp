<%@page import="com.sms.utils.Settings"%>
<%@page import="org.springframework.security.authentication.UsernamePasswordAuthenticationToken"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib uri="/WEB-INF/tags/sms.tld" prefix="sms"%>
<!DOCTYPE html>
<html>
	<head>
	<%
		if(SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken){
			if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
				response.sendRedirect(Settings.APP_CONTEXT+"/admin/onLoginSuccess.htm");
			}
		}
	%>
		<sms:Title pageTitle="Login Page"/>
		<link rel="stylesheet" type="text/css" href="<%=Settings.APP_CONTEXT%>/css/style.css">
				
		<script type="text/javascript">
			var msg='<%=request.getParameter("msg")%>';
				
			function setMessage(message, type){
				document.getElementById("msgDiv").innerHTML = message;
				document.getElementById("msgDiv").style.color = (type == 'Error') ? 'red' : 'green';
			}
			
			function validate(){
				if(isBlank(document.getElementById("j_username").value)){
					setMessage('Please enter UserName', 'Error');
					return false;
				}
								
				if(isBlank(document.getElementById("j_password").value)){
					setMessage('Please enter Password', 'Error');
					return false;
				}
				
				return true;
			}
			
			function isBlank(val){
				if((val == null) || (val == '') || (val =='null')){
					return true;
				}
				return false;
			}
		</script>
	</head>
	<body>
		<sms:HeaderFooter pageTitle="Login Page"/>
		<div class="contentDiv" id="contentDiv">
			<br/>
			<h4 id="msgDiv"></h4>
			<script type="text/javascript">
				if(msg != '' && msg != 'null'){
					setMessage(msg, '<%=request.getParameter("msgType")%>');
				}
			</script>
			<fieldset style="width:260px; height:170px">
				<legend><b>User Login</b></legend>
				<form action="<%=Settings.APP_CONTEXT%>/j_spring_security_check" method="post">
					<table style="width: 100%">
						<tr align="center">
							<td align="right">User Name</td>
							<td align="left"><input type="text" name="j_username" id="j_username" /></td>
						</tr>
						<tr align="center">
							<td align="right">Password</td>
							<td align="left"><input type="password" name="j_password" id="j_password" /></td>
						</tr>
						<tr align="center">
							<td></td>
							<td align="left"><input type="submit" value="Login" onclick="return validate()" style="width: 70px"/>&nbsp;&nbsp;<input type="reset" value="Reset" style="width: 65px"/></td>
						</tr>
						<tr align="center">
							<td colspan="2" align="right" ><h5><a href="<%=Settings.APP_CONTEXT%>/login/toSignUp.htm">Don't have an account? Sign Up</a><br/>
								<a href="<%=Settings.APP_CONTEXT%>/login/toForgotPassword.htm">Forgot Password</a></h5></td>
						</tr>
					</table>
				</form>
			</fieldset>
		</div>
	</body>
</html>