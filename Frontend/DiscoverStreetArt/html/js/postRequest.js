import { handleStatusCodeResponse } from "./ErrorHandlingUpload.js";


async function postData(url, fetchOptions){

    let response = await fetch(url, fetchOptions);

    let statusCode = await response.status


    return statusCode;
}

async function uploadImage(){
    const pictureForm =  document.querySelector("#pictureForm");
     const url = "http://localhost:8080/api/v1/upload"
     const method = "post"
     const formData = new FormData(pictureForm);
    
     const fetchOptions = {
        method: method,
        body: formData
     }
    
      let statusCode = await postData(url, fetchOptions);
    
    
       return statusCode;
    }


    function createResponseObject() {
        const INPUT_NAME = document.getElementById("name")
        const ARTIST_INPUT = document.getElementById("artist");
        const DESCRIPTION_INPUT = document.getElementById("description");
        const DATE_Input = document.getElementById("creationDate")
        const LATITUDE_INPUT_FIELD = document.getElementById("longitude");
        const LONGITUDE_INPUT_FIELD = document.getElementById("latitude");
        const fileInputs = document.getElementById("pictureInput");
        
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
        POST_REQUEST_ARTPOINT_DATA.picturePointer = fileInputs.files.item(0).name;;
     
        return POST_REQUEST_ARTPOINT_DATA;
     }

     async function UploadArtPointData() {
        const URL = "http://localhost:8080/api/v1/streetArt"
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


export { postData, uploadImage, UploadArtPointData};