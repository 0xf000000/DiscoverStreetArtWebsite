import { postData} from "./postRequest.js"
import { $ } from "./utils.js"
import { displayErrorMessage} from "./ErrorHandlingUpload.js";

window.onload = () =>{
let form = $("form");

form.addEventListener("submit", PostEmail)


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

    case 404: 
    displayErrorMessage("something went wrong please try again :( ", "alert-info");
    break; 
  }

    
}



