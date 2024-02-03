import { $ } from "./utils.js"
import {displayErrorMessage, deleteErrorMessage} from "./ErrorHandlingUpload.js"
window.onload = () => {
let passwordInput = $("#password");
  passwordInput.addEventListener("focusout", checkIfValidPassword);



}


function disableButton( B_enabled){
    $(".btn").disabled = B_enabled;
}



function checkIfValidPassword(){
 let password = $("#password").value;

  if(!password.match(/[a-z]/g)){
    displayErrorMessage("there should be atLeast one lowercase Letter in your password :()", "alert-danger");
    disableButton(true);
    return;
  }

  if(!password.match(/[A-Z]/g)){
    displayErrorMessage("there should be atleast one Uppercase Letter in your password :(", "alert-danger");
    disableButton(true);
    return;
  }

  if(!password.match(/[0-9]/g)){
    displayErrorMessage("there should be atleast one number in your password :(" ,"alert-danger");
    disableButton(true);
    return;
  }

  if(password.length <= 6 || password.length > 20){

    displayErrorMessage("your password should be more than 6 characters and less than 20 :(", "alert-danger");
    disableButton(true)
    return;
  }

  deleteErrorMessage();
  disableButton(false);


}
