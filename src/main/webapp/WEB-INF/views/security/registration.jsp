<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>


<layout:default title="Регистрация">


    <form role="form" method="post" enctype="multipart/form-data" action="signUp">

        <common:image-uploader styleClass="img-thumbnail" fileName="icon"/>

        <label for="role">Я</label>

        <select id="role" name="role">
            <c:forEach var="roleItem" items="${roles}">
                <option value="${roleItem.name()}">${roleItem.title}</option>
            </c:forEach>
        </select>

        <label for="name">Имя</label>

        <input id="name" type="text" placeholder="Введите имя" name="name" required="true"/>

        <label for="lastname">Фамилия</label>

        <input id="lastname" type="text" placeholder="Введите фамилию" name="lastname"
               required="true"/>

        <label for="patronymic">Отчество</label>

        <input id="patronymic" type="text" placeholder="Введите отчество" name="patronymic"
               required="true"/>

        <label for="email">Почта</label>

        <input id="email" name="email" type="email" placeholder="Введите почту" required="true"/>

        <label for="password">Пароль</label>

        <input id="password" type="password" name="password" placeholder="Введите пароль"
               required="true"/>

        <button type="submit">Зарегистрироваться</button>

    </form>

    <a href="login">Войти в систему</a>


</layout:default>