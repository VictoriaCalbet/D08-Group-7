<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<display:table name="users" id="row" requestURI="${requestURI}" pagesize="5">

	<spring:message code="user.profile" var="profileHeader" />	
	<display:column title="${profileHeader}">	
		<a href="user/profile.do?userId=${row.id}">
		 	<spring:message code="user.profile" />
		</a>
	</display:column>

	<spring:message code="user.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />
	
	<spring:message code="user.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader}" sortable="true" />
	
	<spring:message code="user.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}"/>
	
	<spring:message code="user.phone" var="phoneHeader" />
	<display:column property="phone" title="${phoneHeader}"/>
	
	<spring:message code="user.address" var="addressHeader" />
	<display:column property="address" title="${addressHeader}"/>
	
	<spring:message code="user.birthDate" var="birthDateHeader" />
	<spring:message code="user.birthDate.pattern" var="datePattern"/>
	<display:column title="${birthDateHeader}" >
		<fmt:formatDate value="${row.birthDate}" pattern="${datePattern}"/>
	</display:column>
	
</display:table>
