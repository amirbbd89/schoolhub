<!DOCTYPE html>
<%@page import="com.sms.utils.Settings"%>
<html>
<head>
<title>Login Page</title>
<body onload="document.getElementById('frm').submit();">
	<form id="frm" action="<%=Settings.APP_CONTEXT%>/login.jsp" method="post">
		<input type="hidden" name="msgType" id="msgType" value="<%=request.getAttribute("msgType")%>"/>
		<input type="hidden" name="msg" id="msg" value="<%=request.getAttribute("msg")%>"/>
		<input type="submit" value="Continue to Login Page.......">
	</form>
</body>
</html>