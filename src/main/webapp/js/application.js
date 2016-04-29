var map;
var zoom = 14;
var markersAllUsers = [];
var polyLinesAllUsers = [];
var polyLineOneUser;
var markersOneUser = [];



window.initMap=function(){

    var mapOptions = {
        center: {lat: 55.5185192, lng: 15.7812413},
        zoom: 5
    };
    map = new google.maps.Map(document.getElementById("map"), mapOptions);
    map.addListener('zoom_changed', function () {
        zoom = map.getZoom();
    });

    loadAllGeo();

    /*
     setInterval(function(){
     loadAllGeo("lineAll")// this will run after every 5 seconds
     }, 5000);*/
};

function selectChange(str) {
    if (str === "") {
        return;
    }

    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        /* 0 = uninitialized,1 = loading, 2 = loaded, 3 = interactive, and 4 = complete.*/
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            var jsonData = JSON.parse(xhttp.responseText);
            var user = jsonData.user;
            var locationList = jsonData.locations;

            //clear the map
            clear();
            //rest this two because it is per user
            polyLineOneUser = null;
            markersOneUser = [];

            polyLineOneUser = createPolyLine('#0000FF', locationList);
            var markers = createMarker(locationList);
            Array.prototype.push.apply(markersOneUser, markers);
            showPerUser();

        }
        ;
    };
    //this have a problem ...this would be explained
    xhttp.open("GET", "../../locations?format=json&uid=" + str, true);
    xhttp.send();

}







function loadAllGeo() {


    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            var jsonData = JSON.parse(xhttp.responseText);
            var allUserLocation = jsonData.allUsersAndLocations;
            var all = allUserLocation.length;
            for (var i = 0; i < all; i++) {
                var userLocation = allUserLocation[i];
                //alert(userLocation);
                var user = userLocation.user;
                var locations = userLocation.locations;
                var markers = createMarker(locations);
                var ployline = createPolyLine('#FF0000', locations);
                Array.prototype.push.apply(markersAllUsers, markers);
                polyLinesAllUsers.push(ployline);
            }

            showAllGeo();
        }
        ;
    };
    xhttp.open("GET", "../../locations", true);
    xhttp.send();

}
function createMarker(locationList) {
    //alert(locationList.length);
    var markers = [];
    var s = locationList.length;
    for (var i = 0; i < s; i++) {
        var location = locationList[i];
        var time = location.time;
        var latitude = location.latitude;
        var longitude = location.longitude;
        var date = new Date(time);
        var marker = new google.maps.Marker({
            position: new google.maps.LatLng(latitude, longitude),
            title: "Date: " + date.toString()

        });
        markers.push(marker);


    }
    return markers;

}
// Sets the map on all markers in the array.
function setMapOnAllMarkers(markerList, map) {
    var len = markerList.length;
    for (var i = 0; i < len; i++) {
        markerList[i].setMap(map);
    }
    if (map && len) {
        var pos = markerList[0].getPosition();
        var lat = pos.lat();
        var lon = pos.lng();
        map.panTo(new google.maps.LatLng(lat, lon));
        map.setZoom(zoom);
    }
}

function createPolyLine(color, locationList) {
    var s = locationList.length;
    var points = [];
    for (var i = 0; i < s; i++) {
        var location = locationList[i];
        var time = location.time;
        var latitude = location.latitude;
        var longitude = location.longitude;

        points.push(new google.maps.LatLng(latitude, longitude));


    }
    var polyLine = new google.maps.Polyline({
        path: points,
        geodesic: true,
        strokeColor: color,
        strokeOpacity: 1.0,
        strokeWeight: 6
    });
    return polyLine;


}

function setMapOnAllPolyLines(polyList, map) {
    for (var i = 0; i < polyList.length; i++) {
        polyList[i].setMap(map);
    }
    var polyLength = polyList.length;
    if (map && polyLength) {
        var poly = polyList[0];
        var path = poly.getPath();
        var len = path.length;
        if (len) {
            var lat = path.getAt(0).lat();
            var lng = path.getAt(0).lng();
            map.panTo(new google.maps.LatLng(lat, lng));
            map.setZoom(zoom);
        }
    }

}

function clear() {
    setMapOnAllMarkers(markersOneUser, null);
    if (polyLineOneUser) {
        polyLineOneUser.setMap(null);
    }
}
function showPerUser() {
    var isMarkerChecked = document.getElementById("marker").checked;
    var isLineChecked = document.getElementById("line").checked;
    if (isMarkerChecked) {
        setMapOnAllMarkers(markersOneUser, map);
    } else {
        setMapOnAllMarkers(markersOneUser, null);
    }

    if (isLineChecked && polyLineOneUser) {
        polyLineOneUser.setMap(map);
    } else {
        if (polyLineOneUser) {
            polyLineOneUser.setMap(null);
        }
    }
}
function showAllGeo() {
    var isMarkerChecked = document.getElementById("markerAll").checked;
    var isLineChecked = document.getElementById("lineAll").checked;
    if (isMarkerChecked) {
        setMapOnAllMarkers(markersAllUsers, map);
    } else {
        setMapOnAllMarkers(markersAllUsers, null);
    }
    if (isLineChecked) {
        setMapOnAllPolyLines(polyLinesAllUsers, map);
    } else {
        setMapOnAllPolyLines(polyLinesAllUsers, null);
    }




}

