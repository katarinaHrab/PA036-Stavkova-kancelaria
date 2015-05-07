<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" nadpis="Leagues">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.LeagueActionBean" var="actionBean"/>
        
            <s:form beanclass="cz.muni.fi.pa036.betting.web.LeagueActionBean" action="/league/add">
                <s:submit name="add" value="New League"/>
            </s:form>
            
            <table class="zakladni">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Sport</th>
                    <th>Country</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${actionBean.allLeagues}" var="league">
                    <tr>
                        <td>
                            <c:out value="${league.id}"/>
                        </td>
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
                            <c:if test="${actionBean.isUserAdminOrBookmaker}">
                                <s:link beanclass="cz.muni.fi.pa036.betting.web.LeagueActionBean" event="edit">
                                    <s:param name="league.id" value="${league.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/pencil.png" alt="edit league" title="edit league"></s:link>
                                <s:link beanclass="cz.muni.fi.pa036.betting.web.LeagueActionBean" event="delete" onclick="return confirm('Really delete?')">
                                    <s:param name="league.id" value="${league.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/cross-script.png" alt="delete league" title="delete league"></s:link>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>   
        
            <s:form beanclass="cz.muni.fi.pa036.betting.web.LeagueActionBean" action="/league/add">
                <p style="text-align: right">
                <s:submit name="add" value="New League"/>
                </p>
            </s:form>
        
    </s:layout-component>
</s:layout-render>
