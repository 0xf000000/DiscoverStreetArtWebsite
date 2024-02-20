/* author: leon there will be some small functionality here for redirecting and making the slideshow better*/
import { $, isOnMobileDevice } from "./utils.js"


window.onload  = () => {


  let SuchenButton = $(".SuchenButton");
  let hochLadenButton = $(".hochLadenButton");
  console.log(SuchenButton)
  hochLadenButton.addEventListener("click",() => { window.location.href = "/uploadImage"})
  SuchenButton.addEventListener("click", () => {  window.location.href= "/map";})
}

SLIDE_SHOW_INIT();

function SLIDE_SHOW_INIT(){



if(isOnMobileDevice()){
    setMobileSlideshow();
}
else{
  setSlideShowUp()
}

}


// well i really really really dont like this solution cause we will dublicate here but if i try to handle it in the same it gets even more messy
async function setMobileSlideshow(){
  let container = $(".mobileSlideShow");

  let response = await fetch("/api/v1/streetArt/latest");
  let streetArtArray = await response.json();
  let statusCode = await response.status;

  for(let i = 0; i < streetArtArray.length -1; i++){
    let html;
    if (i == 0 ){
      html = getMobileSlideShowHtml(picturePointer[0], streetArtArray[i].picture_Name, "test",true);
    }
    else{
     let picturePointer = streetArtArray[i].picturePointer.split(";");
     html = getMobileSlideShowHtml(picturePointer[0], streetArtArray[i].picture_Name, "test", false);
    container.insertAdjacentHTML("afterbegin", html);
    
    }
    }

}




// sets the newest streetArtPoints into the slideshow
async function setSlideShowUp(){

// if use is not on mobile get the following divs
  
  let  firstRow = $(".firstThreePictures");
  let  secondRow = $('.secondPictures');
   let thirdRow = $('.thirdPictures');
  

  let fetchOptions = {
  method: "GET",

}

  let response = await fetch("/api/v1/streetArt/latest", fetchOptions);
  let streetArtArray = await response.json();
  let statusCode = await response.status;

  
  for( let i = 0; i < streetArtArray.length; i++){

    let picturePointer = streetArtArray[i].picturePointer.split(";");
    let html;
    html = getSlideShowHtml(picturePointer[0], streetArtArray[i].picture_Name, "test");

    
    if( i <= 2  ){

    firstRow.insertAdjacentHTML("afterbegin",html); // we switch the container when were over 4
    }
    else if(i <= 5){

      secondRow.insertAdjacentHTML("afterbegin", html);
    }
    else{
      thirdRow.insertAdjacentHTML("afterbegin", html);
    }

  }
}


function getMobileSlideShowHtml(imagePath, unterschrift, adresse, isFirst){

  if(isFirst){
    return ` <div class="carousel-item active">
      <div class="card">
        <img src="${imagePath}" class="card-img-top" alt="Kunstwerk 1">
        <div class="card-body">
          <h5 class="card-title">${unterschrift}</h5>
          <p class="card-text">${adresse}</p>
          <p class="card-text"><small class="text-muted">Universitätsstraße 33, 50931 Köln</small></p>
        </div>
      </div>
    </div>`
  }

  return  `
      <div class="carousel-item active">
        <div class="card">
        <img src="${imagePath}" class="card-img-top" alt="Kunstwerk 1">
        <div class="card-body">
          <h5 class="card-title">${unterschrift}</h5>
          <p class="card-text">${adresse}</p>
          <p class="card-text"><small class="text-muted">Universitätsstraße 33, 50931 Köln</small></p>
        </div>
      </div>
    </div>
`



}

//NOTE: this is highly injectable need to parse the variables first here not really a big deal rn but someone could hook your browser or try to steal some things with that. This is getting so messy im ashamed im going to give this to uni
function getSlideShowHtml( imagePath, unterschrift, adresse){
  // in order to get both slideshows working we need to check for mobile
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

