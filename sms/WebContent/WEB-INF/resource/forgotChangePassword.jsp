<%@page import="com.sms.utils.Settings"%>
<%@ taglib uri="/WEB-INF/tags/sms.tld" prefix="sms"%>
<!DOCTYPE html>
<html>
	<head>
	<%
		String title = "Forgot Password";
		boolean isChange = false;
		if((request.getAttribute("mode") != null) && request.getAttribute("mode").equals("CHANGE_PASSWORD")){
			title = "Change Password";
			isChange = true;
		}
	%>
		
		<sms:Title pageTitle="<%=title%>"/>
		<script type="text/javascript">
		var msg = '<%=request.getAttribute("msg")%>';
		
		function setMessage(message, type){
			document.getElementById("msgDiv").innerHTML = message;
			document.getElementById("msgDiv").style.color = (type == 'Error') ? 'red' : 'green';
		}
		
		function validateEmail(){
			if(isBlank(document.getElementById("email").value)){
				setMessage('Please fill valid Email Id', 'Error');
				return false;
			}
			
			return true;
		}
		
		function validatePassword(){
			if(isBlank(document.getElementById("password").value)){
				setMessage('Please fill valid Current Password', 'Error');
				return false;
			}
			
			if(isBlank(document.getElementById("newpassword").value)){
				setMessage('Please fill valid New Password', 'Error');
				return false;
			}
			
			if(isBlank(document.getElementById("repeat").value)){
				setMessage('Please confirm New Password', 'Error');
				return false;
			}
			
			if(document.getElementById("newpassword").value != document.getElementById("repeat").value){
				document.getElementById("repeat").value = '';
				setMessage('New Password mismatch', 'Error');
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
		<sms:HeaderFooter pageTitle="<%=title%>" isUserNameAndLogoutReq="<%=isChange%>" isBackButtonReq="true" backUrl="/"/>
		<div class="contentDiv">
			<br/>
			<h4 id="msgDiv"></h4>
			<script type="text/javascript">
				if(msg != '' && msg != 'null'){
					setMessage(msg, '<%=request.getAttribute("msgType")%>');
				}
			</script>
			<fieldset title="<%=title%>"  style="width:300px">
				<legend><b><%=title%></b></legend>
				<%
					if(isChange){
				%>
			<form action="<%=Settings.APP_CONTEXT%>/admin/onChangePassword.htm" method="post">
				<table style="width:100%">
					<tr align="center">
						<td align="right">UserName</td>
						<td align="left"><b><%=session.getAttribute("username")%></b></td>
					</tr>
					<tr align="center">
						<td align="right">Current Password</td>
						<td align="left"><input type="password" name="password" id="password"/></td>
					</tr>
					<tr align="center">
						<td align="right">New Password</td>
						<td align="left"><input type="password" name="newpassword" id="newpassword"/></td>
					</tr>
					<tr align="center">
						<td align="right">Confirm Password</td>
						<td align="left"><input type="password" name="repeat" id="repeat"/></td>
					</tr>
					<tr align="center">
						<td></td>
						<td align="left"><input type="submit" value="Change Password" onclick="return validatePassword()" style="width:144px"/></td>
					</tr>
				</table>
			</form>
		<%
			}else{
		%>
			<form action="<%=Settings.APP_CONTEXT%>/login/sendPassword.htm" method="post">
				<table style="width:100%">
					<tr align="center">
							<td align="right">Enter Your Email</td>
						<td align="left"><input type="email" name="email" id="email"/></td>
					</tr>
					<tr align="center">
						<td></td>
						<td align="left"><input type="submit" value="Send Password" onclick="return validateEmail()" style="width:144px"/></td>
					</tr>
				</table>
			</form>
		<%}%>
		</fieldset>
		<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="100%" id="SMSConfig">
                <param name="movie" value="<%=Settings.APP_CONTEXT%>/Flex/SMSConfig/SMSConfig.swf" />
                <param name="quality" value="high" />
                <param name="bgcolor" value="#ffffff" />
                <param name="allowScriptAccess" value="sameDomain" />
                <param name="allowFullScreen" value="true" />
                <param name="wmode" value="transparent"/>
                <param name="flashvars" value="appContext=<%=Settings.APP_CONTEXT%>&schoolId=66666&username=99999"/>
                <!--[if !IE]>-->
                <object type="application/x-shockwave-flash" data="<%=Settings.APP_CONTEXT%>/Flex/SMSConfig/SMSConfig.swf" width="100%" height="100%">
                    <param name="quality" value="high" />
                    <param name="bgcolor" value="#ffffff" />
                    <param name="allowScriptAccess" value="sameDomain" />
                    <param name="allowFullScreen" value="true" />
                    <param name="wmode" value="transparent"/>
                	<param name="flashvars" value="appContext=<%=Settings.APP_CONTEXT%>&schoolId=66666&username=99999"/>
                <!--<![endif]-->
                <!--[if gte IE 6]>-->
                    <p> 
                        Either scripts and active content are not permitted to run or Adobe Flash Player version
                        11.1.0 or greater is not installed.
                    </p>
                <!--<![endif]-->
                    <a href="http://www.adobe.com/go/getflashplayer">
                        <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash Player" />
                    </a>
                <!--[if !IE]>-->
                </object>
                <!--<![endif]-->
            </object>
		</div>
	</body>
</html>