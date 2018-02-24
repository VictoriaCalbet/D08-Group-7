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
	<form:hidden path="rendezvous"/>
	
	<!-- Attributes -->
	
	<form:select path="rendezvous">
		<form:options items="${rendezvouses}" itemLabel="name" itemValue="id"/>
	</form:select>
	<acme:textbox code="announcement.title" path="title"/>
	<acme:textarea code="announcement.description" path="description"/>

	<!-- Action buttons -->
	
	<acme:submit name="save" code="announcement.save"/> &nbsp;
	<acme:cancel url="/" code="announcement.cancel"/>
	
</form:form>