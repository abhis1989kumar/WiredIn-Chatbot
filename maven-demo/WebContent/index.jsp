<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.swing.JOptionPane"%> 
<!DOCTYPE html> 
<html>
 <head> 
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
 <title>JSP Page</title>
  </head> 
<%@ page import="deploy.SpeakingChatbot" %>
<%
new SpeakingChatbot();
%>
<body></body>
</html>