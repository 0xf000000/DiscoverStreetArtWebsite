import { $ } from "./utils.js"
import {displayErrorMessage, deleteErrorMessage} from "./ErrorHandlingUpload.js"







function validatePassword(){
  if(!IsPasswordMatching()){
      displayErrorMessage("passwords do not match!", "alert-danger");
    disableButton(true);
    return;
  }



  if(!checkIfValidPassword()){
    displayErrorMessage("password: 6-20 Characters atleast one Uppercase letter, and atleast one number", "alert-danger");
    disableButton(true);
    return;
  }



  deleteErrorMessage();
  disableButton(false);


}

function IsPasswordMatching(){
  let password = $("#password").value.trim();
  let confirmPassword = $("#confirmPassword").value.trim();

  return password == confirmPassword
    
  
}



// just disables a button with the class .btn usually i only have one button on the website, could of been abstracter
function disableButton( B_enabled){
    $(".btn").disabled = B_enabled;
}


// validates the email to be a normal email atleast from the syntax wise
function validateEmail(email){

  return email.match(/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);

}



// checks if the password matches our security standarts :0 which are not rly high
function checkIfValidPassword(){
 let password = $("#password").value.trim();
  let isPasswordValid = true;
  if(!password.match(/[a-z]/g)){
    isPasswordValid= false;

  }

  if(!password.match(/[A-Z]/g)){
    isPasswordValid = false;
  
  }

  if(!password.match(/[0-9]/g)){
  isPasswordValid = false;


  }

  if(password.length <= 6 || password.length > 20){
    
    isPasswordValid = false;
  }

  

  return isPasswordValid;

}





export { validateEmail, validatePassword, checkIfValidPassword, IsPasswordMatching};



