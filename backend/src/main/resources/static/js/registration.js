import { $ } from "./utils.js"
import {displayErrorMessage, deleteErrorMessage} from "./ErrorHandlingUpload.js"
import {validatePassword} from "./validatePassword.js"

window.onload = () => {

let passwordInput = $("#password");
let emailInput = $("#email");
let confirmPassword = $("#confirmPassword");
  passwordInput.addEventListener("focusout", validatePassword);
  emailInput.addEventListener("focusout", checkIfEmailIsValid);
  confirmPassword.addEventListener("focusout", validatePassword);

}






// just disables the button
function disableButton( B_enabled){
    $(".btn").disabled = B_enabled;
}

function validateEmail(email){

  return email.match(/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);

}




function checkIfEmailIsValid(){
let emailField = $("#email").value.trim();

  if(!validateEmail(emailField)){
    displayErrorMessage("please use a valid Email Address!", "alert-danger");
    disableButton(true);
    return;
  }

  disableButton(false);
  deleteErrorMessage();

}

