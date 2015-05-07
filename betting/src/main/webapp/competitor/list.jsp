<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" nadpis="Competitors">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.CompetitorActionBean" var="actionBean"/>
        
            <s:form beanclass="cz.muni.fi.pa036.betting.web.CompetitorActionBean" action="/competitor/add">
                <s:submit name="add" value="New competitor"/>
            </s:form>
        
            <table class="zakladni">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Sport</th>
                    <th>Country</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${actionBean.allCompetitors}" var="competitor">
                    <tr>
                        <td>
                            <c:out value="${competitor.id}"/>
                        </td>
                        <td>
                            <c:out value="${competitor.name}" />
                        </td>
                        <td>
                            <c:out value="${competitor.sport.kindofsport}"/>
                        </td>
                        <td>
                            <c:out value="${competitor.country.name}"/>
                        </td>

                        <td>
                            <c:if test="${actionBean.isUserAdminOrBookmaker}">
                                <s:link beanclass="cz.muni.fi.pa036.betting.web.CompetitorActionBean" event="edit">
                                    <s:param name="competitor.id" value="${competitor.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/pencil.png" alt="edit competitor" title="edit competitor"></s:link>
                                <s:link beanclass="cz.muni.fi.pa036.betting.web.CompetitorActionBean" event="delete" onclick="return confirm('Really delete?')">
                                    <s:param name="competitor.id" value="${competitor.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/cross-script.png" alt="delete competitor" title="delete competitor"></s:link>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>           
        
            <s:form beanclass="cz.muni.fi.pa036.betting.web.CompetitorActionBean" action="/competitor/add">
                <p style="text-align: right">
                <s:submit name="add" value="New competitor"/>
                </p>
            </s:form>

    </s:layout-component>
</s:layout-render>