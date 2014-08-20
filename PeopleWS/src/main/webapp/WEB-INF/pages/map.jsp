<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="map-headers.jsp"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <title>Google Maps Multiple Markers</title>
    <script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
    <script src="/static/handlebars-v1.3.0.js" type="text/javascript"></script>
</head>
<body>

<div class="mapWidget">
    <div class="form-container">
        <label>Enter Your Location</label>
        <input type="input" id="zip" name="zip" class="text">
        <input type="image" id="zip_but" name="zip_but" value="Find Location"
               onclick="changeLocationFromZip(document.getElementById('zip').value)" src="img/search.gif">

    </div>
    <div class="map-container">
        <div id="left-side">

            <div id="holder">
            </div>
        </div>

        <div id="map" style="width: 500px; height: 500px;"></div>
    </div>
</div>

<script src="/static/jquery-1.8.3.min.js"></script>

<script id="mapLine" type="text/x-handlebars-template">
    <div class="location" onclick="changeLocation({{lat}}, {{lng}})">
        <div class="name">{{name}}</div>
        <div class="city">{{city}}</div>
        <div class="postal">{{postal}}</div>
        <div class="takepart"><a href="#">Take Part</a></div>
        <div class="distance">{{distanceMsg}}</div>
    </div>
</script>

<script type="text/javascript">

    var zip = $("#zip");

    zip.bind('keydown', function (e) {
        if (e.keyCode === 13) {  //checks whether the pressed key is "Enter"
            changeLocationFromZip(zip.val());
        }
    });

    var source = $("#mapLine").html();
    var template = Handlebars.compile(source);
    var siteData = {};

    var mapDiv = $("#map");

    var map = new google.maps.Map(mapDiv[0], {
        zoom: 10,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });


    function getAjax(lat, lng, locName) {

        var NameMap = {};
        var DistanceMap = {};

        var $table = $('<div>');

        $.ajax({
            url: '/sitesForTrial.app',
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
                $.each(data, function (index) {

                    if (lat != undefined) {
                        var distance = getDistanceFromLatLonInKm(lat, lng, data[index].lat, data[index].lng);
                        DistanceMap[distance] = data[index].name;
                        data[index].distanceMsg = Math.round(distance) + ' Kilometers Away';
                    }

                    var line = template(data[index]);

                    NameMap[data[index].name] = line;
                    siteData[data[index].name] = {"lat": data[index].lat, "lng": data[index].lng, "locationName": data[index].name};

                });

                var bounds = new google.maps.LatLngBounds();

                for (var key in siteData) {

                    var marker = new google.maps.Marker({
                        position: new google.maps.LatLng(siteData[key].lat, siteData[key].lng),
                        map: map,
                        title: siteData[key].locationName
                    });

                    bounds.extend(marker.position);

                    var infowindow = new google.maps.InfoWindow();

                    bindInfoWindow(marker, map, infowindow, siteData[key].locationName);
                }

                map.fitBounds(bounds);

                var arr = [];

                for (var key in DistanceMap) {
                    arr.push(key);
                }

                arr.sort(function (a, b) {
                    return a - b
                });

                if (lat != undefined) {
                    var arrayLength = arr.length;
                    for (var i = 0; i < arrayLength; i++) {
                        if (arr[i] != undefined && arr[i] != 'undefined') {
                            var name = DistanceMap[arr[i]];
                            var line = NameMap[name];
                            $table.append(line);
                        }
                    }
                }
                else {
                    for (var key in NameMap) {
                        $table.append(NameMap[key]);
                    }
                }

                $table.append('</div>');

                $('#holder').html($table);

                if (lat != undefined) {
                    console.log("change loc in ajax func " + lat + " " + lng);
                    changeLocation(lat, lng, locName);
                }
            }
        });
    }

    getAjax();

    function bindInfoWindow(marker, map, infowindow, strDescription) {
        google.maps.event.addListener(marker, 'click', function () {
            infowindow.setContent(strDescription);
            infowindow.open(map, marker);
        });
    }

    function changeLocation(lat, lng, name) {

        console.log("change loc " + lat + " " + lng + " " + name);
        var mapOptions = {
            zoom: 12,
            center: new google.maps.LatLng(lat, lng)
        }
        map.setOptions(mapOptions);
        console.log("call display marker")
        displayMarker(lat, lng, name);
    }

    function joinTrial(lat, lng, name) {
        window.location = "http://www.google.com";
    }

    function displayMarker(lat, lng, name) {
        var marker = new google.maps.Marker({
            position: new google.maps.LatLng(lat, lng),
            map: map,
            title: name
        });
    }

    function changeLocationFromZip(zip) {
        console.log('changeLocationFromZip ' + zip);
        var lat = '';
        var lng = '';
        var geocoder = new google.maps.Geocoder();
        geocoder.geocode({ 'address': zip}, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                lat = results[0].geometry.location.lat();
                lng = results[0].geometry.location.lng();

                getAjax(lat, lng, zip);
            }
        });
    }

    function getDistanceFromLatLonInKm(lat1, lon1, lat2, lon2) {

        console.log('get distance ' + lat1 + ' ' + lon1 + lat2 + ' ' + lon2);

        var R = 6371; // Radius of the earth in km
        var dLat = deg2rad(lat2 - lat1);  // deg2rad below
        var dLon = deg2rad(lon2 - lon1);
        var a =
                        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2)
                ;
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        var d = R * c; // Distance in km
        return d;
    }

    function deg2rad(deg) {
        return deg * (Math.PI / 180)
    }


</script>
</body>
</html>