const form = document.querySelector("form");
const APIURL = "api/v1/upload";
const fileInput = document.querySelector('input');

const submitButton = document.querySelector('button');
form.addEventListener("submit", handleSubmit);

fileInput.addEventListener("change", handleInputChange)

// handles the default behaviours of the form tag element
function handleSubmit(event){
  event.preventDefault();
  // uploadImage(); 
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


   
}



function showAlertSigns( statusCode){
   if( statusCode === 200){
      let succsess = document.querySelector(".sucsess");
      succsess.style.display ="block";
   }

   else{
      let error = document.querySelector(".error");
      error.style.display ="block";
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


