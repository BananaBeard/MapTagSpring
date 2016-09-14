<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>IP Address Location</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Control positioning</title>
    <style type="text/css">
        <%@include file="/resources/css/RsltStyle.css" %>
    </style>
    <link href="https://fonts.googleapis.com/css?family=Exo+2:100" rel="stylesheet">
</head>
<body>
<div id="map"></div>
<script>
    function initMap() {

        var myLatLng = {lat: ${latitude}, lng: ${longitude}};
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 12,
            center: {lat: ${latitude}, lng: ${longitude}},
            mapTypeControl: true,
            mapTypeControlOptions: {
                style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
                position: google.maps.ControlPosition.TOP_CENTER
            },
            zoomControl: true,
            zoomControlOptions: {
                position: google.maps.ControlPosition.LEFT_CENTER
            },
            scaleControl: true,
            streetViewControl: true,
            streetViewControlOptions: {
                position: google.maps.ControlPosition.LEFT_TOP
            },
            fullscreenControl: true
        });

        var marker = new google.maps.Marker({
            position: myLatLng,
            map: map,
            title: '${latitude}   ${longitude}'
        });
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCapyxcJWT8XDuReEakbvFbVksfCPC00NE&callback=initMap">
</script>
<div id="inf">
    IP Address Information <br>
    IP:  ${ip} <br>
    Lat: ${latitude} <br>
    Lon: ${longitude}
</div>
</body>
</html>