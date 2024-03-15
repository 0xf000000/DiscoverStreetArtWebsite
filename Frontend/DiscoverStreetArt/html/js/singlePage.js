import { $ } from "./utils.js"
import { getURLparameter } from "./Requests.js"




/*artist: "ss"

comments: [] (0)

creationDate: ""

date: "2024-02-21"

description: ""

latitude: 6.950333118438722

longitude: 50.93890574158038

picturePointer: "SU1HXzk1NTU=.jpeg"

picture_Name: "Frau Meer"

streetArtId: 8*/


async function getStreetArtPoint(id){
  const URL = `api/v1/streetArt/${id}`;
  let response = await fetch(URL);
  let STATUS_CODE = await response.status;

  if(STATUS_CODE != 200){
    console.log("[-] sorry something went wrong with fechting the data from the server :(");
    throw new Error("getStreetArtPoint(id) ... ");
  }

 let responseBody = await response.json();
  console.log(responseBody);

  return responseBody;

}

async function main(){
  let description = $(".description");
  let image = $(".SinglePageImageStreetArt");
  let date = $(".DatumHochgeladen");
  let title = $(".Title");
  let urlParameter = getURLparameter();
  let artId = urlParameter.get("artId");
  let streetArtPoint = await getStreetArtPoint(artId);
  let pictureForUs = streetArtPoint.picturePointer.split(";");
  let finalDescription = "";

  if( streetArtPoint.description === "" ){
    finalDescription = "no description saved";
  }
  else{ finalDescription = streetArtPoint.description}


  title.innerText = streetArtPoint.picture_Name;
  description.innerText = finalDescription;
  date.innerText = streetArtPoint.date; 
  image.src= `pictures/${pictureForUs[0]}`;

}

window.onload = () => {
main();
}

