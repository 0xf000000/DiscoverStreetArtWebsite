import { assertFileIsValid } from "./imageValidation.js";

function displayErrorMessage(infoMessage, statusMessage){
   
    let message = deleteErrorMessage();

    let STATUS = document.createElement("div");
    STATUS.classList.add("alert");
    STATUS.classList.add(statusMessage);
    STATUS.innerText = infoMessage
    message.appendChild(STATUS);
    message.style.display = "block";
 
 }


 function deleteErrorMessage(){
    let message = document.querySelector(".statusMessage");
     let STATUSALERT = document.querySelector(".alert");

     if(STATUSALERT != null){
        message.removeChild(STATUSALERT);
     }

     return message;

 }

 function handleStatusCodeResponse( statusCode){
   
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

 

 function handleInputChange(event){
   
    const submitButton = document.querySelector('#submitPicture');
    const pictureInput = document.querySelector("#pictureInput");
 
       if(!checkReqInputFields()){
          displayErrorMessage("please fill out all required fields :0", "alert-danger")
         // submitButton.disabled = true
          return;
     }
     if (pictureInput.files[0] == null){
       displayErrorMessage("please provide a image for your streetArtpoint", "alert-danger")
       return;
    }
    
       
      if(!assertFileIsValid(pictureInput.files)){
       displayErrorMessage("please provide a valid input file! (jpg/png)", "alert-danger");
       //submitButton.disabled = true
       return;
      }
 
      deleteErrorMessage();
       
     //submitButton.disabled = false;
 
   }

   function  checkReqInputFields(){
    const LONGITUDE_INPUT_FIELD = document.getElementById("latitude");
    const fileINPUT = document.querySelector("#pictureInput");
    const INPUT_NAME = document.getElementById("name");
    
    if (INPUT_NAME.value.trim() == "" ){
       return false;
    }
 
    if(LONGITUDE_INPUT_FIELD.value.trim() == ""){
       return false;
    }
 
    if(fileINPUT.files == null){
       return false;
    }
 
    return true;
 
 }


 

 export {displayErrorMessage, handleStatusCodeResponse, deleteErrorMessage, handleInputChange, checkReqInputFields};