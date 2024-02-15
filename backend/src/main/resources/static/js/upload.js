import { handleInputChange, handleStatusCodeResponse } from "./ErrorHandlingUpload.js";
import { setupEventListener } from "./eventListener.js";
import { getLocation } from "./map.js";
import { uploadImage, UploadArtPointData } from "./Requests.js";

// GLOBAL SCOPE VARIABLES
let upload_MARKER = null;


// EVENT LISTENERS
window.onload = () => {
   const submitButtoN = document.querySelector('#submitPicture');
   const LOCATION_BUTTONE = document.getElementById("locationButton");
  

   setupEventListener();
   submitButtoN.addEventListener("click", handleSubmit);
   // some problem with IOS that prevents to start the event when beeing clicked
   
   LOCATION_BUTTONE.addEventListener("click", getLocation);
   LOCATION_BUTTONE.addEventListener("click", handleInputChange);
   
 

   
}

async function handleSubmit(event) {
   
     let statusCode = await UploadArtPointData();

      handleStatusCodeResponse(statusCode);
      
      if(statusCode == 200 ){

      window.location.href= "/map"; 
      
   }
}

// TODO: this solution is hella shitty but its the best way i can do it rn


let map = L.map('map').setView({ lon: 6.960701, lat: 50.937066 }, 13);

L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
   maxZoom: 19,
   attribution: '&copy; <a href="https://openstreetmap.org/copyright">OpenStreetMap contributors</a>'
}).addTo(map);


// when u click on the map you get the selected latitude and longitute 
function onMapClick(event) {
   const LONGITUDE_INPUT_FIELD = document.querySelector("#longitude");
   const LATITUDE_INPUT_FIELD = document.querySelector("#latitude");
   const latitude = event.latlng.lat;
   const longitude = event.latlng.lng;

   if (upload_MARKER !== null) {
      map.removeLayer(upload_MARKER);
   }

   upload_MARKER = new L.marker(event.latlng).addTo(map);
   LONGITUDE_INPUT_FIELD.value = longitude;
   LATITUDE_INPUT_FIELD.value = latitude;

}
map.on("click", onMapClick);
map.on("click",(event) => {

   handleInputChange(event);
});
