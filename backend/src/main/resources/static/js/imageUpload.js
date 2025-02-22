import { displayErrorMessage } from "./ErrorHandlingUpload.js";
import { redirectWithParams, uploadImage } from "./Requests.js";
import { base64Encode } from "./crypt.js";
import { assertFileIsValid } from "./imageValidation.js"; 
import { $ } from "./utils.js"

let progressArea = $('.progress-area');
let uploadArea = $('.uploaded-area');
let fileInput = $(".fileInput");
window.images = [];



window.onload = () => {
    const imageForms = $("#pictureForm");
    const button = $(".btn")
  button.addEventListener("click",eventRedirect);
    imageForms.addEventListener("click", activateFileInput);

}

function eventRedirect(){
  if(window.images.length == 0){
    displayErrorMessage("please upload atleast one picture", "alert-danger");
    return;
  }
  redirectWithParams();
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

    // we replace all the ; for our pic pointer also trimming and removing white space 
    fileName = fileName.replace(';','')
		.trim()
		.replace(/\s/g,'')
		.replace("=","");
    
    let filenameSplits = fileName.split(".");
    let LENGTH = filenameSplits.length -1;
    let base64EncodedName = "";
    for( let i = 0; i < LENGTH ; i++){
      if( i != LENGTH ){
      base64EncodedName += filenameSplits[i];
      }
    }
    
    base64EncodedName = base64Encode(base64EncodedName);
  

    base64EncodedName = base64EncodedName + '.'+ filenameSplits[LENGTH];
    console.log(base64EncodedName);
    uploadImage(base64EncodedName);

}


}



