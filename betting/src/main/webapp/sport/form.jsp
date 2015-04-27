<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.CountryActionBean" var="countryActionBean"/>
<s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.SportActionBean" var="sportActionBean"/>

<tr>
    <th><s:label for="kindofsport" name="sport.kindofsport"/></th>
    <td><s:text id="kindofsport" name="sport.kindofsport"/></td>
</tr>