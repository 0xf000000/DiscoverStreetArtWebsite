import { postData} from "./postRequest.js"
import { $ } from "./utils.js"
import { deleteErrorMessage, displayErrorMessage} from "./ErrorHandlingUpload.js";
import { validateEmail } from "./validatePassword.js";


window.onload = () =>{
let form = $("form");
let input = $("#email");

form.addEventListener("submit", PostEmail);
input.addEventListener("focusout", validateInputIsEmail);
}




function validateInputIsEmail(){
  let submitButton = $(".custumSubmitButton");
  let input = $("#email").value
  

  if(validateEmail(input)){
    deleteErrorMessage();
    submitButton.disabled = false;

  } else{
    submitButton.disabled= true;
    displayErrorMessage("this is not a valid email!", "alert-danger");
  }


}



let PostEmail = async (event) => {

  event.preventDefault();
  const URL = "http://localhost:8080/forgotPassword"
  let form = $("form");
  let data = new FormData(form);
  let fetchOptions =  {
    method: "POST",
    body: data
  }

  let statusCode = await  postData(URL ,fetchOptions);
  
  switch( statusCode){

    case 200: 
    displayErrorMessage("email got succsesfully sended!", "alert-info");
    
      window.location.href = "http://localhost:8080/login?emailForPasswordReset";
    break;

    case 400: 
    displayErrorMessage("something went wrong please try again :( ", "alert-danger");
    break; 
  }

    
}



