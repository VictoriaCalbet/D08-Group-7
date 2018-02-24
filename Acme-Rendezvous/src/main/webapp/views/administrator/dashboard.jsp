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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<security:authorize access="hasRole('ADMINISTRATOR')">
	<br/>
	
	<!-- Dashboard 1 -->
	<h3> <spring:message code="administrator.avgAndStdRendezvousesCreatedPerUser"/> </h3>
	<h4> <jstl:out value="${avgAndStdRendezvousesCreatedPerUser}"/></h4>
	
	<!-- Dashboard 2 -->
	<h3> <spring:message code="administrator.ratioUserRendezvousesCreatedVsNeverCreated"/> </h3>
	<h4> <jstl:out value="${ratioUserRendezvousesCreatedVsNeverCreated}"/></h4>
	
	<!-- Dashboard 3 -->
	<h3> <spring:message code="administrator.avgAndStdUsersPerRendezvous"/> </h3>
	<h4> <jstl:out value="${avgAndStdUsersPerRendezvous}"/></h4>
	
	<!-- Dashboard 4 -->
	<h3> <spring:message code="administrator.avgAndStdRendezvousesRSVPdPerUser"/> </h3>
	<h4> <jstl:out value="${avgAndStdRendezvousesRSVPdPerUser}"/></h4>
	
	<!-- Dashboard 5 -->
	<h3> <spring:message code="administrator.top10RendezvousesOfUsersWhoHaceRSVPdThem"/> </h3>
	<h4> <jstl:out value="${top10RendezvousesOfUsersWhoHaceRSVPdThem}"/></h4>
	
	<!-- Dashboard 6 -->
	<h3> <spring:message code="administrator.avgAndStdAnnouncementPerRendezvous"/></h3>
	<h4> <jstl:out value="${avgAndStdAnnouncementPerRendezvous}"/></h4>
	
	<!-- Dashboard 7 -->
	<h3> <spring:message code="administrator.rendezVousesThatNoAnnouncementsAbove75NoAnnouncementsPerRendervous"/></h3>
	<h4> <jstl:out value="${rendezVousesThatNoAnnouncementsAbove75NoAnnouncementsPerRendervous}"/></h4>
	
	<!-- Dashboard 8 -->
	<h3> <spring:message code="administrator.rendezVousesThatLinkedNumberOfRendezVousesIsGtThanAvgPlus10PerCent"/></h3>
	<h4> <jstl:out value="${rendezVousesThatLinkedNumberOfRendezVousesIsGtThanAvgPlus10PerCent}"/></h4>
	
	<!-- Dashboard 9 -->
	<h3> <spring:message code="administrator.avgAndStdNoQuestionPerRendezvous"/></h3>
	<h4> <jstl:out value="${avgAndStdNoQuestionPerRendezvous}"/></h4>
	
	<!-- Dashboard 10 -->
	<h3> <spring:message code="administrator.avgAndStdNoAnswersToQuestionsPerRendezvous"/></h3>
	<h4> <jstl:out value="${avgAndStdNoAnswersToQuestionsPerRendezvous}"/></h4>
	
	<!-- Dashboard 11 -->
	<h3> <spring:message code="administrator.avgAndStdRepliesPerComment"/></h3>
	<h4> <jstl:out value="${avgAndStdRepliesPerComment}"/></h4>
	
</security:authorize>