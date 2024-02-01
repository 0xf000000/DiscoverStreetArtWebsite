

import { handleInputChange } from "./ErrorHandlingUpload.js";

function setupEventListener(){
    const INPUT_NAMES = document.querySelector("#name");
    const fileInputs = document.querySelector("#pictureInput")
   
    fileInputs.addEventListener("change", handleInputChange);
    INPUT_NAMES.addEventListener("focusout", handleInputChange);
    
 }


 export {setupEventListener};