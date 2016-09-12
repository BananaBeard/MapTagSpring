<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta name="viewport" content="width=device-width">
    <title>IP Address Location</title>
    <style type="text/css">
        <%@include file="/resources/css/AddrStyle.css" %>
    </style>
    <link href="https://fonts.googleapis.com/css?family=Exo+2:100" rel="stylesheet">
</head>

<body>
<div id="main">
    <div id="form">
        <h2>Enter IP</h2>
        <form:form method="POST" action="/addAddress">

        <form:input path="ip" />
    </div>
    <div id="button">
        <input type="submit" value="Submit"/>
    </div>

    </form:form>

</div>
</body>
</html>
