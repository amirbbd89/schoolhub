<%@page import="com.sms.utils.Settings"%>
<%@ taglib uri="/WEB-INF/tags/sms.tld" prefix="sms"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<sms:Title pageTitle="Welcome Page"/>
		<link rel="stylesheet" type="text/css" href="<%=Settings.APP_CONTEXT%>/css/ext-all.css">
		<script type="text/javascript" src="<%=Settings.APP_CONTEXT%>/js/bootstrap.js"></script>
		<script type="text/javascript" src="<%=Settings.APP_CONTEXT%>/js/ext-all.js"></script>
		
		<script type="text/javascript" src="<%=Settings.APP_CONTEXT%>/js/array-grid.js"></script>
		
		<link rel="stylesheet" type="text/css" href="<%=Settings.APP_CONTEXT%>/css/style.css">
		
		<style type="text/css">
        /* style rows on mouseover */
        .x-grid-row-over .x-grid-cell-inner {
            font-weight: bold;
        }
        /* shared styles for the ActionColumn icons */
        .x-action-col-cell img {
            height: 16px;
            width: 16px;
            cursor: pointer;
        }
        /* custom icon for the "buy" ActionColumn icon */
        .x-action-col-cell img.buy-col {
            background-image: url(../shared/icons/fam/accept.png);
        }
        /* custom icon for the "alert" ActionColumn icon */
        .x-action-col-cell img.alert-col {
            background-image: url(../shared/icons/fam/error.png);
        }

        .x-ie6 .x-action-col-cell img.buy-col {
            background-image: url(../shared/icons/fam/accept.gif);
        }
        .x-ie6.x-action-col-cell img.alert-col {
            background-image: url(../shared/icons/fam/error.gif);
        }

        .x-ie6 .x-action-col-cell img {
            position:relative;
            top:-1px;
        }
    </style>
    
    <script type="text/javascript">
		var msg = '<%=request.getAttribute("msg")%>';
		
		function setMessage(message, type){
			document.getElementById("msgDiv").innerHTML = message;
			document.getElementById("msgDiv").style.color = (type == 'Error') ? 'red' : 'green';
		}
	</script>
	</head>
	<body>
		<sms:HeaderFooter pageTitle="Welcome Page" isUserNameAndLogoutReq="true"/>
		<div class="contentDiv">
			<br/>
			<h4 id="msgDiv"></h4>
			<script type="text/javascript">
				if(msg != '' && msg != 'null'){
					setMessage(msg, '<%=request.getAttribute("msgType")%>');
				}
			</script>
			<c:set var="name" value="company"></c:set>
			<%-- <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="100%" id="SMSConfig">
                <param name="movie" value="<%=Settings.APP_CONTEXT%>/Flex/SMSConfig/SMSConfig.swf" />
                <param name="quality" value="high" />
                <param name="bgcolor" value="#ffffff" />
                <param name="allowScriptAccess" value="sameDomain" />
                <param name="allowFullScreen" value="true" />
                <param name="wmode" value="transparent"/>
                <param name="flashvars" value="appContext=<%=Settings.APP_CONTEXT%>&schoolId=${schoolId}&username=${username}"/>
                <!--[if !IE]>-->
                <object type="application/x-shockwave-flash" data="<%=Settings.APP_CONTEXT%>/Flex/SMSConfig/SMSConfig.swf" width="100%" height="100%">
                    <param name="quality" value="high" />
                    <param name="bgcolor" value="#ffffff" />
                    <param name="allowScriptAccess" value="sameDomain" />
                    <param name="allowFullScreen" value="true" />
                    <param name="wmode" value="transparent"/>
                	<param name="flashvars" value="appContext=<%=Settings.APP_CONTEXT%>&schoolId=${schoolId}&username=${username}"/>
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
            </object> --%>
            ${name}
            <div id="tabGrid"></div>
		</div>
	</body>
</html>