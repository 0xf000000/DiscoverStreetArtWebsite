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
       displayErrorMessage(" upload was sucsessfull!", "alert-info");      
       break;

       case 500 : 
       displayErrorMessage("sorry something went wrong please try again", "alert-danger");
         exit();
       break;
       case 400: 
       displayErrorMessage("are you sure this is an immage what u are trying to upload there?", "alert-danger");
       exit();
       break;
       case 413:
         displayErrorMessage(" Image is to big", "alert-danger");
         exit();
         break;
 
       
 
    }
 
 }

 

 function handleInputChange(event){
   
    const submitButton = document.querySelector('#submitPicture');
  
 
       if(!checkReqInputFields()){
          displayErrorMessage("please fill out all required fields :0", "alert-danger")
          submitButton.disabled = true
          return;
     }
     
    
 
      deleteErrorMessage();
       
     submitButton.disabled = false;
 
   }

   function  checkReqInputFields(){
    const LONGITUDE_INPUT_FIELD = document.getElementById("latitude");
    const INPUT_NAME = document.getElementById("name");
    
    if (INPUT_NAME.value.trim() == "" ){
       return false;
    }
 
    if(LONGITUDE_INPUT_FIELD.value.trim() == ""){
       return false;
    }
 
   
 
    return true;
 
 }


 

 export {displayErrorMessage, handleStatusCodeResponse, deleteErrorMessage, handleInputChange, checkReqInputFields};
