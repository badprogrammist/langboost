<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<layout:default title="Вход">

    <form:form role="form" method="post" action="signIn" modelAttribute="credentials">

        <label for="emailInput">Почта</label>

        <form:input id="emailInput" path="login" type="email" placeholder="Введите почту"
                    required="true"/>

        <label for="inputPassword" >Пароль</label>
        <form:password id="inputPassword" placeholder="Введите пароль" required="true"
                       path="password"/>

        <label><input type="checkbox"/>Запомнить меня</label>
        <button type="submit">Войти</button>
    </form:form>

    Еще не зарегистрированы? <a href="registration">Зарегистрироваться</a>


</layout:default> 