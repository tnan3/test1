<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id = "user" class = "com.nan.tianyu.model.UserBean"  scope = "request">
    <jsp:setProperty name = "user" property = "firstName" />
    <jsp:setProperty name = "user" property = "lastName" />
    <jsp:setProperty name = "user" property = "email" />
    <jsp:setProperty name = "user" property = "message" />
</jsp:useBean>


<form action = "/helloServlet" method = "post">
  
    <label>Message:</label><h3><%=user.getMessage() %></h3><br>
    <label>Email:</label><input type = "text" name = "email" value="<%=user.getEmail() %>"><br>
    <label>Firstname:</label><input type="text" name = "firstName" value="<%=user.getFirstName() %>"><br>
    <label>Lastname:</label><input type = "text" name = "lastName" value="<%=user.getLastName() %>"><br>
    <label>Password:</label><input type = "password" name = "password1" value="<%=user.getPassowrd() %>"><br>
    <label>Comform Password:</label><input type = "password" name = "password2" value="<%=user.getPassowrd() %>"><br>
    <input type = "submit" name="Register" value = "Register">
                
</form>
      

</body>
</html>