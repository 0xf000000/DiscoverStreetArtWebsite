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


const LOCATION_BUTTON = document.getElementById("locationButton")
LOCATION_BUTTON.addEventListener("click", getLocation )

function getLocation(){

   navigator.geolocation.getCurrentPosition(successPosition, errorCallback);


}

const successPosition = (position) => {

   const Long = position.coords.longitude;
   const lat = position.coords.latitude 
   const locatioField = document.getElementById("locationDataField");

   locatioField.value = `${Long} long and ${lat}`
   
 };
 

 const errorCallback = (error) => {
  alert("could not retrieve Location user denied request");
 };


 // MAP 

 var map = L.map('map').setView({lon:6.960701 , lat:50.937066}, 13);

 L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="https://openstreetmap.org/copyright">OpenStreetMap contributors</a>'
      }).addTo(map);
 

function onMapClick(e){

   alert("you clicked the map at " + e.latlng);
}

map.on("click", onMapClick);

 