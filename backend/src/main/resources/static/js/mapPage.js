
import { $ } from "./utils.js"

// well i really dont like the global solution should update it in some time
window.streetArtMap = new Map();
window.streetArtMarkers = [];
window.currentMarker;




var mymap = L.map('mapid').setView([50.9575, 6.9603], 13); // Köln Koordinaten

  L.tileLayer('https://{s}.basemaps.cartocdn.com/rastertiles/voyager/{z}/{x}/{y}{r}.png', {
    attribution: 'Map tiles by <a href="https://carto.com/attributions">CARTO</a>',
    subdomains: 'abcd',
    maxZoom: 19
  }).addTo(mymap);

  var images = [
  "/pictures/google.com",
  ""
    // Fügen Sie hier weitere Bildpfade hinzu
  ];
  var currentIndex = 0;

  var previewCard = document.querySelector('.preview-card');
  var previewImage = document.querySelector('.preview-image');
  var imageNavigation = document.querySelector('.image-navigation');
  var previousImageButton = document.querySelector('.previous-image');
  var nextImageButton = document.querySelector('.next-image');
  var closeBtn = document.querySelector('.close-btn');

  function showPreviewCard(marker) {
    
      let longlat = marker.getLatLng();
    
     let S_LongLat = S_parseLongLat(longlat.lat, longlat.lng);
     let streetArtPoint = window.streetArtMap.get(S_LongLat);
    let containerAddress = $(".address");
    let containerTitle = $(".title");
    let InfoButton = $(".infoButton");
    images = []; //images = []; // image is global
    
    containerTitle.innerText = streetArtPoint.picture_Name;
  containerAddress.innerText = streetArtPoint.date;
    InfoButton.addEventListener("click", () => { window.location.href =`/singlePage?artId=${streetArtPoint.streetArtId}`;});

// loading ne images first we need to parse the data
    
    let picturePointer = streetArtPoint.picturePointer.split(";");
    let LENGTH = picturePointer.length;
    
    for( let i = 0; i <  LENGTH; i++){
    images.push( `/pictures/${picturePointer[i]}`);
    }

    previewImage.src =`/pictures/${picturePointer[0]}`;
    previewCard.classList.remove('hidden');
    
    window.currentMarker = marker;
    updatePreviewCardPosition(marker);
    showImageNavigationButtons();
  }

  function hidePreviewCard() {
    previewCard.classList.add('hidden');
  }

  function showImageNavigationButtons() {
    if (images.length > 1) {
      imageNavigation.classList.remove('hidden');
    } else {
      imageNavigation.classList.add('hidden');
    }
  }


// that this works is the living example that javascript is really shit
  function updatePreviewCardPosition(marker) {
    let usableMarker = marker;
    if( usableMarker == undefined){

      usableMarker = window.currentMarker;
    
    }

    let markerPos = mymap.latLngToContainerPoint( usableMarker.getLatLng());
    previewCard.style.left = markerPos.x - (previewCard.offsetWidth / 2) + 'px';
    previewCard.style.top = markerPos.y - previewCard.offsetHeight - 40 + 'px';
  }
 
  function updateImage(direction) {
    currentIndex = (currentIndex + direction + images.length) % images.length;
    previewImage.src = images[currentIndex];
  }

  

  previousImageButton.addEventListener('click', function(event) {
    event.stopPropagation();
    updateImage(-1);
  });

  nextImageButton.addEventListener('click', function(event) {
    event.stopPropagation();
    updateImage(1);
  });

  closeBtn.addEventListener('click', function() {
    hidePreviewCard();
  });

  // Aufrufen beim Initialisieren der Karte
  showImageNavigationButtons();
  updateImage(0); // Initialisiert das erste Bild

  mymap.on('moveend', function() {
    if (!previewCard.classList.contains('hidden')) {
      updatePreviewCardPosition();
    }
  });


  mymap.on('click', function() {
    hidePreviewCard();
  });

async function setMarkerUp(){
  const URL = "/api/v1/streetArt/all";
  const fetchOptions = {
    method: "GET",
  }

  let response = await  fetch(URL, fetchOptions);
  let streetArtArray = await response.json();
  let responseStatus = await response.status;

  if( responseStatus != 200 ){
    alert("sorry there was a problem with fetching data please try again another time");
    return;
  }
  
  let LENGTH = streetArtArray.length;

  for( let i = 0 ; i < LENGTH; i++){

    let longitude = streetArtArray[i].longitude;
    let latitude = streetArtArray[i].latitude;
    let S_longLat = S_parseLongLat(longitude,latitude);

    window.streetArtMap.set(S_longLat, streetArtArray[i]);
    let longLat = F_parseLongLat(longitude,latitude); 
  
    let marker = L.marker(longLat).addTo(mymap);
    window.streetArtMarkers.push(marker);
    
    // initialize marker cards when u click on them
  }

    MARKER_CARD_INIT();
  
}

// this uses a global stored array in order to accsess all markers. tried it like this cause we need to hold the marker references in order to implement the eventListener

function MARKER_CARD_INIT(){
let markers = window.streetArtMarkers;
let LENGTH = markers.length;
  for(let i = 0; i <  LENGTH; i++){
    let marker = markers[i];

    // does this need to be in a callback?
    marker.on("click", () => { showPreviewCard(marker) });

  }

}


// parses longitude and latitude to a string and combines them
function S_parseLongLat(long,lat){
  
  let S_long = long.toString();
  let S_lat = lat.toString();



  return S_long + S_lat;
}

// parses two longlat strings and returns a array with float longLat
function F_parseLongLat(long, lat){
  
  let F_long = parseFloat(long);
  let F_lat = parseFloat(lat);

  return [F_long, F_lat];
  
}


setMarkerUp();


