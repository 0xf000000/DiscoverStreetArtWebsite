import { $} from "../js/utils.js"
import {postData, getURLparameter} from "../js/postRequest.js"
import {validatePassword } from "../js/validatePassword.js"
import { displayErrorMessage } from "./ErrorHandlingUpload.js";

window.onload = () => {
 let form = $("form");
 let password = $("#password");
  let confirmPassword = $("#confirmPassword");
  form.addEventListener("submit", (event) => { postNewPassword(event)} );
  password.addEventListener("focusout", validatePassword);
  confirmPassword.addEventListener("focusout", validatePassword);


}


 async function postNewPassword( event){
  event.preventDefault();

  let URL = "http://localhost:8080/forgotPassword/updatePassword";
 let newPassword = $("#password").value;
  let urlParameter = getURLparameter();

  let token = urlParameter.get("token");
let data = {
  password: newPassword,
    token : token
  }

  let fetchOptions = {
    method: "POST",
    headers:{
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data),
  }

  let statusCode = await postData(URL, fetchOptions );

  if(200 == statusCode){
    window.location.href = "http://localhost:8080/login?passwordChanged" 
  }
  else if ( 400 == statusCode){
    displayErrorMessage("something went wrong with reseting your password! please try again", "alert-danger");
  }

}

