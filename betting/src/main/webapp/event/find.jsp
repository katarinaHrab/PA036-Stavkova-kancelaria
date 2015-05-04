<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>


<script>
    $(document.ready(function() { 
        $("#name").autocomplete({
        name = "Sparta"
    });
</script>

<s:layout-render name="/layout.jsp" nadpis="Find an event">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" var="actionBean"/>
            
             
            <s:form beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" action="/event/editAction">
                
                <fieldset>
                    <table>
                        <tr>
                            <th><s:label for="name" name="name"/></th>
                            <td><input id="name" name="name"  autocomplete="on" /></td>
                        </tr> 
                    </table>
                </fieldset>
            </s:form>
        
            <s:errors/>    
                
    </s:layout-component>
</s:layout-render>
            
        

            