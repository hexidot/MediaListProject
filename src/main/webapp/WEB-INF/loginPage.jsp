<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="/css/login-style.css">
    <title>Login</title>
</head>
<body>
    <header>
        <h1>Welcome! Please log in or register!</h1> 
    </header>
    <main>
        <form:form action="/register" method="post" modelAttribute="newUser">
            <h2>Register</h2>

            <div class="form-row">
                <form:label path="userName" for="userName">User Name:</form:label>
                <form:input path="userName" id="userName"/>
            </div>
            <form:errors path="userName"/>

            <div class="form-row">
                <form:label path="email" for="email">Email:</form:label>
                <form:input path="email" id="email"/>
            </div>
            <form:errors path="email"/>

            <div class="form-row">
                <form:label path="password" for="password">Password:</form:label>
                <form:password path="password" id="password"/>
            </div>
            <form:errors path="password"/>

            <div class="form-row">
                <form:label path="confirm" for="confirm">Confirm Password:</form:label>
                <form:password path="confirm" id="confirm"/>
            </div>
            <form:errors path="confirm"/>

            <button type="submit">Submit</button>
        </form:form>
        <form:form action="/login" method="post" modelAttribute="newLogin">
            <h2>Log in</h2>
                
            <form:errors path="password"/>
            <form:errors path="email"/>

            <div class="form-row">
                <form:label path="email" for="email">Email:</form:label>
                <form:input path="email" id="email"/>
            </div>

            <div class="form-row">
                <form:label path="password" for="password">Password:</form:label>
                <form:password path="password" id="password"/>
            </div>
            
            <button type="submit">Submit</button>
        </form:form>
    </main>
</body>
</html>