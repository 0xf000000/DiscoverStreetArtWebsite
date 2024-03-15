

import { handleInputChange } from "./ErrorHandlingUpload.js";

function setupEventListener(){
    const INPUT_NAMES = document.querySelector("#name");
   
   
    INPUT_NAMES.addEventListener("focusout", handleInputChange);

    
 }


 export {setupEventListener};