// author: leon just a random jQuery rebuild cause it looks a bit cleaner in the code

function isOnMobileDevice(){
  return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
  // true for mobile device
  
  // false for not mobile device
}


let $ = (element) => {


  return document.querySelector(element);
}



export { $,isOnMobileDevice };
