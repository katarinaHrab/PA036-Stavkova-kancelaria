<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" nadpis="Overview of Leagues and Events">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" var="eventActionBean"/>
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.SportActionBean" var="sportActionBean"/>
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.CountryActionBean" var="countryActionBean"/>
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.LeagueActionBean" var="leagueActionBean"/>
        
        <s:form beanclass="cz.muni.fi.pa036.betting.web.LeagueActionBean" action="/league/setFilterLeagues">
            <fieldset>
                <table>
                    <tr>
                        <th><s:label for="country" name="country"/></th>
                        <td>
                            <s:select id="countryId" name="countryId">
                                <s:option value="" label="" />
                                <s:options-collection collection="${countryActionBean.allCountries}" value="name" label="name" />
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <th><s:label for="sport" name="sport"/></th>
                        <td>
                            <s:select id="sportId" name="sportId">
                                <s:option value="" label="" />
                                <s:options-collection collection="${sportActionBean.allSports}" value="kindofsport" label="kindofsport" />
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td><s:submit name="setFilterLeagues">Filter leagues</s:submit></td>
                    </tr>
                </table>
            </fieldset>
        </s:form>
        
            <table class="zakladni">
                <tr>
                    <th>Name</th>
                    <th>Sport</th>
                    <th>Country</th>
                    <th>Events</th>
                </tr>
                <c:forEach items="${leagueActionBean.getFilterLeagues()}" var="league">
                    <tr>
                        <td>
                            <c:out value="${league.name}" />
                        </td>
                        <td>
                            <c:out value="${league.sport.kindofsport}"/>
                        </td>
                        <td>
                            <c:out value="${league.country.name}"/>
                        </td>

                        <td>
                            <s:link beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" event="eventsByLeague">
                                    <s:param name="league.id" value="${league.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/list.png" alt="events" title="events"></s:link>  
                        </td>
                    </tr>
                </c:forEach>
            </table>   
    </s:layout-component>
</s:layout-render>

