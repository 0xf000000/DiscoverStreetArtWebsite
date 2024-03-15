import { displayErrorMessage } from "./ErrorHandlingUpload.js";

function getLocation() {

    navigator.geolocation.getCurrentPosition(successPosition, errorCallback);
 
 
 } 


 const errorCallback = (error) => {
    displayErrorMessage("could not retrieve Location user denied request", "alert-danger");
   };



const successPosition = (position) => {

    const Long = position.coords.longitude;
    const lat = position.coords.latitude 
   
    LATITUDE_INPUT_FIELD.value = lat;
    LONGITUDE_INPUT_FIELD.value = Long;
 
    if( upload_MARKER !== null){
       map.removeLayer(upload_MARKER);
    }
   
    upload_MARKER = new L.marker([lat, Long]).addTo(map);
  };


  

   export {errorCallback, successPosition, getLocation};