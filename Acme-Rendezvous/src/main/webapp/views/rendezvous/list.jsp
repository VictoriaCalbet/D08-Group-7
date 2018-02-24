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



<security:authentication property="principal" var="loggedactor"/>

<display:table name="rendezvouses" id="row" requestURI="${requestURI}" pagesize="5">

	<jstl:set var="isDeleted" value="${row.isDeleted}" />
	<jstl:set var="isDraft" value="${row.isDraft}" />
	<jstl:set var="isAdult" value="${row.isAdultOnly}" />
	
	
	
	<jstl:if test="${isDeleted eq true}">
		<jstl:set var="style" value="background-color:SlateGray ;" />
	</jstl:if>
	
	<jstl:if test="${(isDeleted eq false) && (isDraft eq false) && (isAdult eq false)}">
		<jstl:set var="style" value="background-color:lightSeaGreen ;" />
	</jstl:if>
	
	<jstl:if test="${(isDeleted eq false) && (isDraft eq false) && (isAdult eq true)}">
		<jstl:set var="style" value="background-color:brown;" />
	</jstl:if>
	
	<jstl:if test="${(isDeleted eq false) && (isDraft eq true) && (isAdult eq true)}">
		<jstl:set var="style" value="background-color:brown;" />
	</jstl:if>
	
	<jstl:if test="${(isDeleted eq false) && (isDraft eq true) && (isAdult eq false)}">
		<jstl:set var="style" value="background-color:sandyBrown;" />
	</jstl:if>


	<spring:message code="rendezvous.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" style="${style}"/>
	
	<spring:message code="rendezvous.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" style="${style}" />
	
	<spring:message code="rendezvous.meetingMoment" var="meetingMomentHeader" />
	<spring:message code="rendezvous.meetingMoment.pattern" var="datePattern"/>
	<display:column title="${meetingMomentHeader}" style="${style}">
		<fmt:formatDate value="${row.meetingMoment}" pattern="${datePattern}"/>
	</display:column>
	
	
	<spring:message code="rendezvous.picture" var="pictureHeader" />
	<display:column title="${pictureHeader}" style="${style}">
		<img src="${row.picture}" height="42" width="42">
	</display:column>
	
	<spring:message code="rendezvous.gpsPoint.latitude" var="gpsPointLatitudeHeader" />
	<display:column property="gpsPoint.latitude" title="${gpsPointLatitudeHeader}" style="${style}"/>
	
	<spring:message code="rendezvous.gpsPoint.longitude" var="gpsPointLongitudeHeader" />
	<display:column property="gpsPoint.longitude" title="${gpsPointLongitudeHeader}" style="${style}" />
	
	<security:authorize access="hasRole('USER')"> 
	
		<spring:message code="rendezvous.isAdultOnly" var="isAdultOnlyHeader" />
		<display:column property="isAdultOnly" title="${isAdultOnlyHeader}" style="${style}"/>
		
		<spring:message code="rendezvous.isDraft" var="isDraftHeader" />
		<display:column property="isDraft" title="${isDraftHeader}" style="${style}"/>
		
	</security:authorize>
	
	<spring:message code="rendezvous.creator" var="creatorHeader" />	
	<display:column title="${creatorHeader}">	
		<a href="user/info.do?rendezvousId=${row.id}">
		 	<spring:message code="rendezvous.creatorButton" />
		</a>
	</display:column>
	
	<spring:message code="rendezvous.attendant" var="attendantHeader" />	
	<display:column title="${attendantHeader}">
	<jstl:choose>
		<jstl:when  test = "${fn:length(row.rsvps) ==0}">	
			<spring:message code="rendezvous.notAttendants"/>
		</jstl:when>
		<jstl:otherwise> 
			<a href="user/listAttendant.do?rendezvousId=${row.id}"> 
		<spring:message code="rendezvous.attendantButton"/>
		</a>	</jstl:otherwise>
	</jstl:choose>
	</display:column>
	
	<spring:message code="rendezvous.similar" var="similarHeader" />	
	<display:column title="${similarHeader}">
	<jstl:choose>
		<jstl:when  test = "${fn:length(row.isLinkedTo) ==0}">	
			<spring:message code="rendezvous.notSimilar"/>
		</jstl:when>
		<jstl:otherwise> 
			<a href="rendezvous/list.do?rendezvousId=${row.id}"> 
		<spring:message code="rendezvous.similarButton"/>
		</a>	</jstl:otherwise>
	</jstl:choose>
	</display:column>
	
	<spring:message code="rendezvous.comments" var="commentHeader" />	
	<display:column title="${commentHeader}">
			<a href="comment/list.do?rendezvousId=${row.id}"><spring:message code="rendezvous.commentButton"/></a>
	</display:column>
	
	
	<security:authorize access="hasRole('USER')">
	
	<spring:message code="rendezvous.delete" var="deleteHeader" />	
	<display:column title="${deleteHeader}">	
		<jstl:if test="${!row.isDeleted && row.isDraft && row.creator.userAccount.username==loggedactor.username}">	
			<a href="rendezvous/user/delete.do?rendezvousId=${row.id}">
			 	<spring:message code="rendezvous.deleteButton" />
			</a>
		</jstl:if>
	</display:column>
	
	<spring:message code="rendezvous.edit" var="editHeader" />	
	<display:column title="${editHeader}">	
		<jstl:if test="${!row.isDeleted && row.isDraft && row.creator.userAccount.username==loggedactor.username}">	
			<a href="rendezvous/user/edit.do?rendezvousId=${row.id}">
			 	<spring:message code="rendezvous.editButton" />
			</a>
		</jstl:if>
	</display:column>
	
	<spring:message code="rendezvous.link" var="linkHeader" />	
	<display:column title="${linkHeader}">	
		<jstl:if test="${!row.isDeleted && row.creator.userAccount.username==loggedactor.username}">	
			<a href="rendezvous/user/link.do?rendezvousId=${row.id}">
			 	<spring:message code="rendezvous.linkButton" />
			</a>
		</jstl:if>
	</display:column>
	
	<spring:message code="rendezvous.RSVPButton" var="rsvpHeader" />
	<display:column title="${rsvpHeader}">
	<jstl:choose>
	<jstl:when test="${!principalRendezvouses.contains(row) }">
			
			<a href="RSVP/user/RSVPAssure.do?rendezvousId=${row.id}"> <spring:message code="rendezvous.RSVPButton" /></a>	

	</jstl:when>
	
	
	<jstl:otherwise>
		<spring:message code="rendezvous.AlreadyRSVPed" />
		</jstl:otherwise>
		</jstl:choose>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	
	<spring:message code="rendezvous.delete" var="deleteHeader" />	
	<display:column title="${deleteHeader}">	
			<a href="rendezvous/administrator/delete.do?rendezvousId=${row.id}">
			 	<spring:message code="rendezvous.deleteButton" />
			</a>
	</display:column>
	
	</security:authorize>
	
	
</display:table>

<span style="background-color:lightSeaGreen"><spring:message code="rendezvous.allPublic" /></span>
<span style="background-color:brown"><spring:message code="rendezvous.adult" /></span>
<span style="background-color:sandyBrown"><spring:message code="rendezvous.draft" /></span>
<span style="background-color:SlateGray"><spring:message code="rendezvous.deleted" /></span>

