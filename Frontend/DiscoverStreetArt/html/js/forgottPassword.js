import { postData} from "./postRequest.js"
import { $ } from "./utils.js"


window.onload = () =>{
let form = $("form");

form.addEventListener("submit", PostEmail)



}


let PostEmail = async (event) => {

  event.preventDefault();
  const URL = "http://localhost:8080/api/v1/forgotPassword"
  let form = $("form");
  let data = new FormData(form);
  let fetchOptions =  {
    method: "POST",
    body: data
  }

  let statusCode = await  postData(URL ,fetchOptions);
  


}



