<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" nadpis="Edit country">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.CountryActionBean" var="actionBean"/>
        
        <s:form beanclass="cz.muni.fi.pa036.betting.web.CountryActionBean" action="/country/save">
            <s:hidden name="country.id"/>
            <fieldset>
                <table>
                    <%@include file="form.jsp"%>
                    <tr>
                        <th></th>
                        <td><s:submit name="save">Save changes</s:submit></td>
                    </tr>
                </table>
            </fieldset>
        </s:form>
        
        <s:errors/>
    </s:layout-component>
</s:layout-render>