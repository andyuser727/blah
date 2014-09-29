var mapDiv;
var map;
var bounds;
var arrayOfDistancesFromLocation;
var siteData;
var NameMap;
var DistanceMap;
var htmlSource;
var source;
var template;
var zip;

$(document).ready(function () {

    mapDiv = $("#map");
    source = $("#mapLine").html();
    template = Handlebars.compile(source);
    zip = $("#zip");

    map = new google.maps.Map(mapDiv[0], {
        zoom: 10,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
    getSiteData();
});

function getSiteData(lat, lng, locName) {

    siteData = {};
    NameMap = {};
    DistanceMap = {};

    htmlSource = $('<div>');

    zip.bind('keydown', function (e) {
        if (e.keyCode === 13) {  //checks whether the pressed key is "Enter"
            changeLocationFromZip(zip.val());
        }
    });

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

            bounds = new google.maps.LatLngBounds();
            createMarkers(siteData);
            map.fitBounds(bounds);
            sortDistances();
            buildHtml(lat);
            htmlSource.append('</div>');
            $('#holder').html(htmlSource);

            if (lat != undefined) {
                console.log("change loc in ajax func " + lat + " " + lng);
                changeLocation(lat, lng, locName);
            }
        }
    });
}

function buildHtml(lat) {
    if (lat != undefined) {
        var arrayLength = arrayOfDistancesFromLocation.length;
        for (var i = 0; i < arrayLength; i++) {
            if (arrayOfDistancesFromLocation[i] != undefined && arrayOfDistancesFromLocation[i] != 'undefined') {
                var name = DistanceMap[arrayOfDistancesFromLocation[i]];
                var line = NameMap[name];
                htmlSource.append(line);
            }
        }
    }
    else {
        for (var key in NameMap) {
            htmlSource.append(NameMap[key]);
        }
    }
}

function sortDistances() {
    arrayOfDistancesFromLocation = [];

    for (var key in DistanceMap) {
        arrayOfDistancesFromLocation.push(key);
    }

    arrayOfDistancesFromLocation.sort(function (a, b) {
        return a - b
    });
}

function createMarkers(siteData) {
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
}

function bindInfoWindow(marker, map, infowindow, strDescription) {
    google.maps.event.addListener(marker, 'click', function () {
        infowindow.setContent(strDescription);
        infowindow.open(map, marker);
    });
}

function changeLocation(lat, lng, name) {
    var mapOptions = {
        zoom: 12,
        center: new google.maps.LatLng(lat, lng)
    }
    map.setOptions(mapOptions);
    console.log("call display marker")
    displayMarker(lat, lng, name);
}

function displayMarker(lat, lng, name) {
    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(lat, lng),
        map: map,
        title: name
    });
}

function changeLocationFromZip(zip) {
    var lat = '';
    var lng = '';
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({ 'address': zip}, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            lat = results[0].geometry.location.lat();
            lng = results[0].geometry.location.lng();

            getSiteData(lat, lng, zip);
        }
    });
}

function getDistanceFromLatLonInKm(lat1, lon1, lat2, lon2) {
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

