<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" nadpis="Edit an event">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" var="actionBean"/>
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.CompetitorActionBean" var="competitorActionBean"/>
        
        <table class="zakladni">
            <tr>
                <th>Competitor</th>
                <th>Bet value</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${actionBean.event.eventCompetitors}" var="eventCompetitor">
                <tr>
                    <td>${eventCompetitor.competitor.name}</td>
                    <td>${eventCompetitor.odds}</td>
                    <td>
                        <s:link beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" event="removeCompetitor">
                            <s:param name="event.id" value="${eventCompetitor.event.id}"/>
                            <s:param name="competitorId" value="${eventCompetitor.competitor.id}"/>
                            <img src="${pageContext.request.contextPath}/img/cross-script.png" alt="remove competitor" title="remove competitor">
                        </s:link>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <s:form beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" action="/event/addCompetitor">
            <s:hidden name="event.id" value="${actionBean.event.id}"/>
            <s:label for="competitorId">Competitor:</s:label>
            <s:select id="competitorId" name="competitorId">
                <s:options-collection collection="${competitorActionBean.allCompetitors}" value="id" label="name" />
            </s:select>
            <s:label for="odds">Odds:</s:label>
            <s:text id="odds" name="odds"/>
            <s:submit name="addCompetitor">Add competitor</s:submit>
        </s:form>
        
        <s:errors/>
    </s:layout-component>
</s:layout-render>