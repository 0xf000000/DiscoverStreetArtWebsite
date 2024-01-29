// CONST VARIABLES
const form = document.querySelector("#pictureForm");
const fileInput = document.getElementById("pictureInput");
const INPUT_NAME = document.getElementById("name");
const ARTIST_INPUT = document.getElementById("artist");
const DESCRIPTION_INPUT = document.getElementById("description");
const submitButton = document.querySelector('#submitPicture');
const DATE_Input = document.getElementById("creationDate")
const LOCATION_BUTTON = document.getElementById("locationButton");
const LATITUDE_INPUT_FIELD = document.getElementById("longitude");
const LONGITUDE_INPUT_FIELD = document.getElementById("latitude");
const STREETART_DATA_FORM = document.getElementById("streetArtDATA");

// GLOBAL SCOPE VARIABLES
let UPLOADPOINTER = null;
let upload_MARKER = null;


// EVENT LISTENERS 
INPUT_NAME.addEventListener("focusout", checkReqInput)
form.addEventListener("submit", handleSubmit);

fileInput.addEventListener("change", handleInputChange);
LOCATION_BUTTON.addEventListener("click", getLocation );





function checkReqInput(){
  if(checkReqInputFields()){
      displayErrorMessage("please provide a name to the streetArt :(", "alert-danger");
  }

  else{

  try{
    document.querySelector(".statusMessage").removeChild(document.querySelector(".alert"))
  }
  catch(error){
    console.log(error);
  }
  }

}

function checkReqInputFields(){



  return INPUT_NAME.value.trim() == "" || LONGITUDE_INPUT_FIELD.value.trim() == "";

}



// handles the default behaviours of the form tag element


async function handleSubmit(event){
  event.preventDefault();
   let response = await uploadImage(); 

     if( response.status == 200){
      
         let response =  await UploadArtPointData();
        console.log(response);
          
         if(response != 200 ){

            displayErrorMessage("there was a problem with uploading the streetartPoint data sorry please try again", "alert-warning" )
         }
         

     }
   
      
   
     
 
}

function createResponseObject(){
   var POST_REQUEST_ARTPOINT_DATA = {
      picture_Name: "",
       picturePointer: "",
       creationDate: "",
       artist: "",
       description: "",
       date:"",
       comments: [],
       latitude: 2,
       longitude: 2
   }
   POST_REQUEST_ARTPOINT_DATA.picture_Name = INPUT_NAME.value;
   POST_REQUEST_ARTPOINT_DATA.artist = ARTIST_INPUT.value
   POST_REQUEST_ARTPOINT_DATA.description = DESCRIPTION_INPUT.value;
   POST_REQUEST_ARTPOINT_DATA.creationDate = DATE_Input.value;
   POST_REQUEST_ARTPOINT_DATA.date = new Date().toISOString().slice(0, 19).replace('T', ' ');
   POST_REQUEST_ARTPOINT_DATA.latitude = LATITUDE_INPUT_FIELD.value;
   POST_REQUEST_ARTPOINT_DATA.longitude = LONGITUDE_INPUT_FIELD.value;
   POST_REQUEST_ARTPOINT_DATA.picturePointer = UPLOADPOINTER;

   return POST_REQUEST_ARTPOINT_DATA;
}

async function UploadArtPointData(){
   const URL = "http://localhost:8080/api/v1/streetArt"
   const method = "POST";

  let BODY =  JSON.stringify( createResponseObject());
    


   const fetchOptions = { 
      method: method,
      body: BODY,
      headers: { 'Content-Type': 'application/json' }
   }

   let response = await fetch(URL, fetchOptions);

   const statusCode = await response.status;
  return statusCode

}

// POST REQUESTS

async function uploadImage(){
   
 const url = "http://localhost:8080/api/v1/upload"
 const method = "post"
 const formData = new FormData(form);

 const fetchOptions = {
    method: method,
    body: formData
 }

  let response = await fetch(url, fetchOptions);

   const statusCode = await response.status;

   showAlertSigns(statusCode);

   let responseStatusAndBody = {
      status: response.status,
      body : response.body
   }

   return responseStatusAndBody;
}

function displayErrorMessage(infoMessage, statusMessage){
   let message = document.querySelector(".statusMessage");
    
  try{

  
    let STATUSALERT = document.querySelector(".alert");
    message.removeChild(STATUSALERT);
  }catch(error){

    console.log(error);
  }
  
   let STATUS = document.createElement("div");
   STATUS.classList.add("alert");
   STATUS.classList.add(statusMessage);
   STATUS.innerText = infoMessage
   message.appendChild(STATUS);
   message.style.display = "block";

}



// POST REQUEST AND STATUS ERROR HANDLING

function showAlertSigns( statusCode){
   
   switch(statusCode){
      case 200: 
      displayErrorMessage("upload was sucsessful!", "alert-info");

     
      break;




      case 500 : 
      displayErrorMessage("sorry something went wrong please try again", "alert-danger");

      break;
      case 400: 
      displayErrorMessage("are you sure this is an immage what u are trying to upload there?", "alert-danger");
      break;

      

   }

}

// INPUT HANDLERS

function handleInputChange(event){
   try {
      
      
      assertFileIsValid(fileInput.files);
     
    } catch (err) {
      // need to change that here just asserting that this works rn 
      alert("no valid iput file!");
       submitButton.disabled = true;
      return;
      }

      const files = event.target.files;
      UPLOADPOINTER = files[0].name;

      
  
      if(!checkReqInputFields()){
  
      submitButton.disabled = false;
    }else{
    displayErrorMessage("please fill out the required Fields", "alert-danger");
  }

  


  }


function assertFileIsValid(fileList){

   const allowedTypes = ["image/jpeg", "image/png"];

   for(const file of fileList){
      const { name: fileName } = file;

      if(!allowedTypes.includes(file.type)){
         throw new Error(` X File ${fileName} could not be uploaded. only images please!`);
      }


   }



}

// GET GEOLOCATION

function getLocation(){

   navigator.geolocation.getCurrentPosition(successPosition, errorCallback);


}

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
 

 const errorCallback = (error) => {
  alert("could not retrieve Location user denied request");
 };


 // MAP 

 var map = L.map('map').setView({lon:6.960701 , lat:50.937066}, 13);

 L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="https://openstreetmap.org/copyright">OpenStreetMap contributors</a>'
      }).addTo(map);
 

function onMapClick(event){
   const latitude = event.latlng.lat;
   const longitude = event.latlng.lng;

   if( upload_MARKER !== null){
      map.removeLayer(upload_MARKER);
   }
  
   upload_MARKER = new  L.marker(event.latlng).addTo(map);
   LONGITUDE_INPUT_FIELD.value = longitude;
   LATITUDE_INPUT_FIELD.value = latitude;



}

map.on("click", onMapClick);

 
