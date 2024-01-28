const form = document.querySelector("#pictureForm");
const APIURL = "api/v1/upload";
const fileInput = document.getElementById("pictureInput")

const submitButton = document.querySelector('#submitPicture');
form.addEventListener("submit", handleSubmit);

fileInput.addEventListener("change", handleInputChange)

// handles the default behaviours of the form tag element
function handleSubmit(event){
  event.preventDefault();
 let statusCode =  uploadImage(); 

 if (statusCode == 200 ){

      UploadArtPointData();
 }
}



// handles the immage Upload to the server

async function uploadImage(){
   
 const url = "http://localhost:8080/api/v1/upload"
 const method = "POST"
 const formData = new FormData(form);

 const fetchOptions = {
    method: "post",
    body: formData
 }

  let response = await fetch(url, fetchOptions);

   const statusCode = await response.status;

   showAlertSigns(statusCode);


   return statusCode;
}



function showAlertSigns( statusCode){
   let message = document.querySelector(".statusMessage");
   let STATUS = document.createElement("div");
   switch(statusCode){
      case 200: 

      STATUS.classList.add("alert");
      STATUS.classList.add("alert-info");
      STATUS.innerText = "image succsessfully uploaded!"
      message.appendChild(STATUS);
      message.style.display = "block";
      break;




      case 500 : 
      STATUS.classList.add("alert");
      STATUS.classList.add("alert-warning");
      STATUS.innerText = "i am sorry something went wrong with uploading the immage please try again"
      message.appendChild(STATUS);
      message.style.display = "block";

      break;
      case 400: 
      STATUS.classList.add("alert");
      STATUS.classList.add("alert-warning");
      STATUS.innerText = "are you sure this is an immage file? Please try again!"
      message.appendChild(STATUS);
      message.style.display = "block";

      break;

      

   }

}



function handleInputChange(){
   try {
      
      assertFileIsValid(fileInput.files);
     
    } catch (err) {
      // need to change that here just asserting that this works rn 
      alert("no valid iput file!");
       submitButton.disabled = true;
      return;
      }

     
     
      submitButton.disabled = false;
}


function assertFileIsValid(fileList){

   const allowedTypes = ["image/jpeg", "image/png"];

   for(const file of fileList){
      const { name: fileName } = file;

      if(!allowedTypes.includes(file.type)){
         throw new Error(` X File ${fileName} could not be uploaded. only images please!`);
      }


   }



}


const successCallback = (position) => {
   console.log(position.coords);
 };
 
 const errorCallback = (error) => {
   console.log("SSS");
   console.log(error);
 };
 
navigator.geolocation.getCurrentPosition(successCallback, errorCallback);
 