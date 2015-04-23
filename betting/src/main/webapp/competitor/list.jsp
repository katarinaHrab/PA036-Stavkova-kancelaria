<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" nadpis="Hráči">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.CompetitorActionBean" var="actionBean"/>
        
            <table class="zakladni">
                <tr>
                    <th>ID</th>
                    <th>Jméno</th>
                    <th>Sport</th>
                    <th>Země</th>
                    <th>Akce</th>
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
                            <c:if test="${actionBean.isUserAdmin}">
                                <s:link beanclass="cz.muni.fi.pa036.betting.web.CompetitorActionBean" event="edit">
                                    <s:param name="competitor.id" value="${competitor.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/pencil.png" alt="upravit hráče" title="upravit hráče"></s:link>
                                <s:link beanclass="cz.muni.fi.pa036.betting.web.CompetitorActionBean" event="delete" onclick="return confirm('Skutečně smazat?')">
                                    <s:param name="competitor.id" value="${competitor.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/cross-script.png" alt="odstranit zprávu" title="odstranit zprávu"></s:link>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>           
        </div>

    </s:layout-component>
</s:layout-render>