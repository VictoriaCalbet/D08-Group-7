<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<!-- Form -->
<form:form action="${urlAction}" modelAttribute="actorForm">
	<!-- Hidden Attributes -->

	<!-- Editable Attributes -->
	<acme:textbox code="actorForm.name" path="name" />
	<acme:textbox code="actorForm.surname" path="surname" />
	<acme:textbox code="actorForm.phone" path="phone" />
	<acme:textbox code="actorForm.username" path="username" />
	<acme:password code="actorForm.password" path="password"/>
	<acme:password code="actorForm.repeatedPassword" path="repeatedPassword"/>

	<security:authorize access="!hasAnyRole('CUSTOMER', 'ADMIN')">

		<acme:checkbox code="actorForm.acceptTerm" path="acceptTerm" />
		<a href="legal-terms/index.do">
			<spring:message code="actorForm.legalTerms" />
		</a><br/>

	</security:authorize><br/>

	<!-- Action buttons -->
	<acme:submit name="save" code="actorForm.save" /> &nbsp;
	<acme:cancel url="/" code="actorForm.cancel" /> <br/>

</form:form>