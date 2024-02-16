/* author: leon there will be some small functionality here for redirecting and making the slideshow better*/
import { $ } from "./utils.js"

window.onload  = () => {
  let SuchenButton = $(".SuchenButton");
  let hochLadenButton = $(".hochLadenButton");
  console.log(SuchenButton)
  hochLadenButton.addEventListener("click",() => { window.location.href = "/uploadImage"})
  SuchenButton.addEventListener("click", () => {  window.location.href= "/map";})
  setSlideShowUp();
}


// sets the newest streetArtPoints into the slideshow
async function setSlideShowUp(){
let firstRow = $(".firstThreePictures");
let fetchOptions = {
  method: "GET",

}

  let response = await fetch("/api/v1/streetArt/latest", fetchOptions);
  let streetArtArray = await response.json();
  
  for( let i = 0; i < streetArtArray.length; i++){
    let picturePointer = streetArtArray[i].picturePointer.split(";");
    
    
    
      let html = getSlideShowHtml(picturePointer[0], streetArtArray[i].description, "test");
      firstRow.insertAdjacentHTML("afterbegin",html);
    

  }


}


function getSlideShowHtml( imagePath, unterschrift, adresse){

 return `<div class="col-md-4">
          <div class="card">
            <img src="pictures/${imagePath}"
                 class="card-img-top" alt="Kunstwerk 1">
            <div class="card-body">
              <h5 class="card-title">${unterschrift}</h5>
              <p class="card-text">${adresse}</p>
            </div>
          </div>
        </div>`
}
