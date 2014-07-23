<%@page import="com.sms.utils.Settings"%>
<%@ taglib uri="/WEB-INF/tags/sms.tld" prefix="sms"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<sms:Title pageTitle="${tab.menuLable}"/>
		<link rel="stylesheet" type="text/css" href="<%=Settings.APP_CONTEXT%>/css/style.css">
	</head>
	<body>
		<sms:SiteHeaderFooter schoolId="${schoolId}" tabId="${tabId}"/>
		<div class="contentDiv">
			<c:forEach var="contentBox" items="${contentBoxList}">
				<sms:Content contentBox="${contentBox}"></sms:Content>
			</c:forEach>
		</div>
	</body>
</html>