<%@page import="com.sms.model.UserInfo"%>
<%@page import="com.sms.utils.Settings"%>
<%@page import="com.sms.model.School"%>
<%@ taglib uri="/WEB-INF/tags/sms.tld" prefix="sms"%>
<!DOCTYPE html>
<html>
	<head>
		<%
		String title = "Sign Up";
		String formAction = Settings.APP_CONTEXT + "/login/onSaveProfile.htm";
		boolean isEdit = false;
		School schoolInfo = new School();
		UserInfo userInfo = new UserInfo();
		if((request.getAttribute("mode") != null) && request.getAttribute("mode").equals("EDIT_PROFILE")){
			title = "Edit Profile";
			formAction = Settings.APP_CONTEXT + "/admin/onUpdateProfile.htm";
			schoolInfo = (School) request.getAttribute("SCHOOL_INFO");
			userInfo = (UserInfo) request.getAttribute("USER_INFO");
			isEdit = true;
		}
		%>
		<sms:Title pageTitle="<%=title%>"/>

		<script src="<%=Settings.APP_CONTEXT%>/js/jquery.min.js"></script>
		<script type="text/javascript">		
		var msg = '<%=request.getAttribute("msg")%>';
		
		function setMessage(message, type){
			document.getElementById("msgDiv").innerHTML = message;
			document.getElementById("msgDiv").style.color = (type == 'Error') ? 'red' : 'green';
		}
		
		function validateForm(){
			if(isBlank(document.getElementById("adminEmailId").value)){
				setMessage('Please fill valid Email', 'Error');
				return false;
			}
			
			if(isBlank(document.getElementById("password").value)){
				setMessage('Please fill valid Password', 'Error');
				return false;
			}
			
			if(isBlank(document.getElementById("repeat").value)){
				setMessage('Please confirm Password', 'Error');
				return false;
			}
			
			if(document.getElementById("password").value != document.getElementById("repeat").value){
				document.getElementById("repeat").value = '';
				setMessage('Password mismatch', 'Error');
				return false;
			}

			if(isBlank(document.getElementById("schoolName").value)){
				setMessage('Please fill School Name', 'Error');
				return false;
			}
			
			if(isBlank(document.getElementById("address").value)){
				setMessage('Please fill Address', 'Error');
				return false;
			}
			
			if(isBlank(document.getElementById("city").value)){
				setMessage('Please fill City', 'Error');
				return false;
			}
			
			if(isBlank(document.getElementById("state").value)){
				setMessage('Please fill State', 'Error');
				return false;
			}
			
			if(isBlank(document.getElementById("country").value)){
				setMessage('Please fill Country', 'Error');
				return false;
			}
			
			if(isBlank(document.getElementById("pincode").value)){
				setMessage('Please fill Pin Code', 'Error');
				return false;
			}
			
			if(isBlank(document.getElementById("phoneNumber").value)){
				setMessage('Please fill Official Phone Number', 'Error');
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
			
		<style type="text/css">
			textArea, input{
				width:300px;
				resize:none;
			}
		</style>
	</head>
	<body>
		<sms:HeaderFooter pageTitle="<%=title%>" isUserNameAndLogoutReq="<%=isEdit%>" isBackButtonReq="true" backUrl="/"/>
		<div class="contentDiv">
			<br/>
			<h4 id="msgDiv"></h4>
			<script type="text/javascript">
				if(msg != '' && msg != 'null'){
					setMessage(msg, '<%=request.getAttribute("msgType")%>');
				}
			</script>
			<fieldset style="width:450px; height:auto;">
				<legend><b><%=title%></b></legend>
				<form id="form1" action="<%=formAction%>" method="post">
					<input type="hidden" name="mode" value="<%=request.getAttribute("mode")%>"/>
					<table>
						<tr>
							<td align="right">Admin Email Id:</td>
							<td><input type="text" name="adminEmailId" id="adminEmailId" <%if(isEdit){%> value="<%=userInfo.getUsername()%>" readonly="readonly"<%}%>/></td>
						</tr>
						<%if(!isEdit){ %>
						<tr>
							<td align="right">Password:</td>
							<td><input type="password" name="password" id="password"/></td>
						</tr>
						<tr>
							<td align="right">Confirm Password:</td>
							<td><input type="password" name="repeat" id="repeat"/></td>
						</tr>
						<%}%>
						<tr>
							<td align="right" valign="top">School Name:</td>
							<td><input type="text" name="schoolName" id="schoolName" <%if(isEdit){%> value="<%=schoolInfo.getSchoolName()%>"<%}%>/></td>
						</tr>
						<tr>
							<td align="right" valign="top">Address:</td>
							<td><textarea name="address" id="address"><%if(isEdit){out.write(schoolInfo.getAddress());}%></textarea></td>
						</tr>
						<tr>
							<td align="right" valign="top">City:</td>
							<td><input type="text" name="city" id="city" <%if(isEdit){%> value="<%=schoolInfo.getCity()%>"<%}%>/></td>
						</tr>
						<tr>
							<td align="right" valign="top">State:</td>
							<td><input type="text" name="state" id="state" <%if(isEdit){%> value="<%=schoolInfo.getState()%>"<%}%>/></td>
						</tr>
						<tr>
							<td align="right" valign="top">Country:</td>
							<td><input type="text" name="country" id="country" <%if(isEdit){%> value="<%=schoolInfo.getCountry()%>"<%}%>/></td>
						</tr>
						<tr>
							<td align="right" valign="top">Pin Code:</td>
							<td><input type="text" name="pincode" id="pincode" <%if(isEdit){%> value="<%=schoolInfo.getPincode()%>"<%}%>/></td>
						</tr>
						<tr>
							<td align="right" valign="top">Official Phone No:</td>
							<td><input type="text" name="phoneNumber" id="phoneNumber" <%if(isEdit){%> value="<%=schoolInfo.getPhoneNumber()%>"<%}%>/></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="Submit" onclick="return validateForm()" style="width:70px"/>&nbsp;&nbsp;<input type="reset" value="Reset" style="width:65px"/></td>
							<td></td>
						</tr>
					</table>
				</form>
			</fieldset>
		</div>
	</body>
</html>