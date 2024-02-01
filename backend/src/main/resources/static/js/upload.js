import { handleStatusCodeResponse } from "./ErrorHandlingUpload.js";
import { setupEventListener } from "./eventListener.js";
import { getLocation } from "./map.js";
import { postData, uploadImage, UploadArtPointData } from "./postRequest.js";

// GLOBAL SCOPE VARIABLES
let upload_MARKER = null;


// EVENT LISTENERS
window.onload = (event) => {
   const submitButtoN = document.querySelector('#submitPicture');
   const LOCATION_BUTTONE = document.getElementById("locationButton");
   const imageForms = document.querySelector("#pictureForm");
   const fileInputs = document.getElementById("pictureInput");

   setupEventListener();
   submitButtoN.addEventListener("click", handleSubmit);
   LOCATION_BUTTONE.addEventListener("click", getLocation);
   imageForms.addEventListener("click", () => {

      fileInputs.click();
   })
}

async function handleSubmit(event) {

   event.preventDefault();

   let statusCode = await uploadImage();

   handleStatusCodeResponse(statusCode);

   if (statusCode == 200) {
     let statusCode2 = await UploadArtPointData();

     if(statusCode2 == 200){
      
      window.location.href= "/map";
   }
   }
}

// TODO: this solution is hella shitty but its the best way i can do it rn


var map = L.map('map').setView({ lon: 6.960701, lat: 50.937066 }, 13);

L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
   maxZoom: 19,
   attribution: '&copy; <a href="https://openstreetmap.org/copyright">OpenStreetMap contributors</a>'
}).addTo(map);

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