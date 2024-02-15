/* author: leon 
   some functions that handle requests and the post requests to the server
*/


import { handleStatusCodeResponse } from "./ErrorHandlingUpload.js";
import {$} from "./utils.js";

async function postData(url, fetchOptions) {

   let response = await fetch(url, fetchOptions);

   let statusCode = await response.status


   return statusCode;
}

async function uploadImage(name) {
   const pictureForm = $("#pictureForm");
   
   const url = "/api/v1/upload";
   const method = "post";
   const formData = new FormData(pictureForm);

   let request = new XMLHttpRequest(); // creating xml request object need to use it cause there is no way in fetch() to track upload progress
   request.open(method, url);

   request.upload.addEventListener('progress', ({ loaded, total }) => handleProgressFunctionality(loaded, total, name ) );

   request.send(formData);
   // i hate xmlhttprequest object is there a normal fucking way to retrieve the status code
   request.onload = () => {
      handleStatusCodeResponse(request.status);
      
      window.images.push(name);

      if(window.images.length >= 3){
        redirectWithParams();
      }
   }

  

}


function redirectWithParams(){
   let URL_PARAMS = new URLSearchParams();
   URL_PARAMS.append("Succsess", null);
   let i = 0;
   window.images.forEach( element => { URL_PARAMS.append(`image${i++}`,  element )})
   window.location.href = `/pointUpload?${URL_PARAMS.toString()}`;
}



function handleProgressFunctionality(loaded, total, name){
   const uploadArea = $(".uploaded-area")
   const progressArea = $(".progress-area");
   let fileLoaded = Math.floor((loaded / total) * 100); // simple % calculation
   let filetotal = Math.floor(total / 1000);
   
   if( name.length >= 12){ 
      let splitName = name.split(".");
      name = splitName[0].substring(0,12) + "... ." + splitName[1];
   }
   
   let progressHTML = getUploadhtml(name, fileLoaded);
   let fileSize;

   
   // reacts to the size of the file and converts into kb/MB
   (filetotal < 1024) ? fileSize = filetotal + "KB" : fileSize = (loaded/ (1024 * 1024)).toFixed(2) + "MB";

      progressArea.innerHTML = progressHTML;


      // this is hella injectable i need to parse it, so i dont get csrf or XSS
      let uploadedHTML = getUploadedHtml(name, fileSize) ;

      if(loaded == total){
     //    progressArea.innerHTML = ""
         uploadArea.insertAdjacentHTML("afterbegin", uploadedHTML);

      }
}

function getUploadedHtml(name, fileTotal){

   return `<li class="row">
   <div class="content">
       <i class="fas fa-file-alt"></i>
       <div class="details">
          <span class="name"> ${name} Uploaded</span>
           <span class="size"> ${fileTotal}</span>
            </div>

        </div>
            <i class="fas fa-check"></i>
             </li>`;
}


function getUploadhtml( name, fileLoaded){
   return  `
   <li class="row">
   <i class="fas fa-file-alt"></i>
   <div class="content">
      <div class="details">
         <span class="name"> ${name} Uploading</span>
         <span class="percent">${fileLoaded}% </span>
      </div>
         <div class="progress-bar">
            <div class="progress" style="width: ${fileLoaded}%"></div>
         </div>
      </div>
   </li>`
}


function createResponseObject() {
   const INPUT_NAME = document.getElementById("name")
   const ARTIST_INPUT = document.getElementById("artist");
   const DESCRIPTION_INPUT = document.getElementById("description");
   const DATE_Input = document.getElementById("creationDate")
   const LATITUDE_INPUT_FIELD = document.getElementById("longitude");
   const LONGITUDE_INPUT_FIELD = document.getElementById("latitude");
  
   var POST_REQUEST_ARTPOINT_DATA = {
      picture_Name: "",
      picturePointer: "",
      creationDate: "",
      artist: "",
      description: "",
      date: "",
      comments: [],
      latitude: 2,
      longitude: 2
   }
   POST_REQUEST_ARTPOINT_DATA.picture_Name = INPUT_NAME.value;
   POST_REQUEST_ARTPOINT_DATA.artist = ARTIST_INPUT.value
   POST_REQUEST_ARTPOINT_DATA.description = DESCRIPTION_INPUT.value;
   POST_REQUEST_ARTPOINT_DATA.creationDate = DATE_Input.value;
   // small hack to have the date in ISO format so sql can parse it
   POST_REQUEST_ARTPOINT_DATA.date = new Date().toISOString().slice(0, 19).replace('T', ' ');
   POST_REQUEST_ARTPOINT_DATA.latitude = LATITUDE_INPUT_FIELD.value;
   POST_REQUEST_ARTPOINT_DATA.longitude = LONGITUDE_INPUT_FIELD.value;


   // the getAll returns the parameter as [] instead of retrieving the object thats why we do it like this.
   let urlParameter = getURLparameter();
 
   // well this is a bit tricky solution but well okay it works i guess cause if the person wants to upload multible images we need to have a way to seperate the image pointers NOTE: might write a batch job for deleting all pictures from the server that dont have a corresponding image pointer in our database,l.
   
   let S_AllPointer = "";
   urlParameter.forEach( (value, key) => { S_AllPointer =  S_AllPointer.concat( ";",`${value}`)});

   console.log(S_AllPointer);


   POST_REQUEST_ARTPOINT_DATA.picturePointer = "";

   return POST_REQUEST_ARTPOINT_DATA;
}

async function UploadArtPointData() {
   const URL = "api/v1/streetArt"
   const method = "POST";

   let BODY = JSON.stringify(createResponseObject());

   const fetchOptions = {
      method: method,
      body: BODY,
      headers: { 'Content-Type': 'application/json' }
   }

   let statusCode = await postData(URL, fetchOptions);



   handleStatusCodeResponse(statusCode);

   return statusCode;
}


function getURLparameter() {
   let queryString = window.location.search;
   let urlParams = new URLSearchParams(queryString);

   return urlParams;
}




export { postData, uploadImage, UploadArtPointData, getURLparameter };
