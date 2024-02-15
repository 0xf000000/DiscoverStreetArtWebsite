import { displayErrorMessage } from "./ErrorHandlingUpload.js";
import { uploadImage } from "./Requests.js";
import { assertFileIsValid } from "./imageValidation.js"; 
import { $ } from "./utils.js"
let progressArea = $('.progress-area');
let uploadArea = $('.uploaded-area');
let fileInput = $(".fileInput");
window.images = [];



window.onload = () => {
    const imageForms = $("#pictureForm");
    
    imageForms.addEventListener("click", activateFileInput );

}

function activateFileInput(){
    let fileInput = $(".fileInput");
    fileInput.click();
}


fileInput.onchange = ({ target }) => {
  let files = target.files;
 
if( files.length > 3  ){
    displayErrorMessage("you only upload 3 files at once", "alert-danger");
    target.files = null;
    return;
} 

if (!assertFileIsValid(files)){
    displayErrorMessage("are you sure this is an image?", "alert-danger");
    return;
}

let file = files[0]; // selects the first file \

if(file){
    let fileName = file.name; 

  
    uploadImage(fileName);
}




}



