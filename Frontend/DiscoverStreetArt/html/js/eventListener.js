

import { handleInputChange } from "./ErrorHandlingUpload.js";

function setupEventListener(){
    const INPUT_NAMES = document.querySelector("#name");
    const fileInputs = document.querySelector("#pictureInput")
    const fileHeader = document.querySelector("#browseToFIle")
    fileInputs.addEventListener("change", (event)=>{
        handleInputChange(event);
        fileHeader.innerText = fileInputs.files.item(0).name;


    });
    INPUT_NAMES.addEventListener("focusout", handleInputChange);

    
 }


 export {setupEventListener};