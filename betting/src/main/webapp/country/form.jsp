<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.CountryActionBean" var="countryActionBean"/>

<tr>
    <th><s:label for="name" name="country.name"/></th>
    <td><s:text id="name" name="country.name"/></td>
</tr>