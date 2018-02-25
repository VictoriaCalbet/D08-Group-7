<%--
 * edit.jsp
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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI}" modelAttribute="announcement">
	
	<!-- Hidden attributes -->
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="momentMade"/>
	
	<jstl:if test="${announcement.id ne 0 }">
		<form:hidden path="rendezvous"/>
	</jstl:if>
	
	
	<!-- Attributes -->
	
	<spring:message code="announcement.rendezvous" var="rendezvousHeader" />
	<b><form:label path="rendezvous"/><jstl:out value="${rendezvousHeader}"/>:&nbsp;</b>

	<jstl:choose>
		<jstl:when test="${announcement.id eq 0}">
			<form:select path="rendezvous">
				<form:options items="${rendezvouses}" itemLabel="name" itemValue="id"/>
			</form:select>
		</jstl:when>
			
		<jstl:when test="${announcement.id ne 0}">
			<jstl:out value="${announcement.rendezvous.name}"/>
		</jstl:when>
	</jstl:choose>
	
	<acme:textbox code="announcement.title" path="title"/>
	<acme:textarea code="announcement.description" path="description"/>

	<!-- Action buttons -->
	
	<acme:submit name="save" code="announcement.save"/> &nbsp;
	<acme:cancel url="/" code="announcement.cancel"/>
	
</form:form>