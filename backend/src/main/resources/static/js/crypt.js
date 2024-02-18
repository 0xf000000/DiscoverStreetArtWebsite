// author: leon because i have some urlDecode issues with some picture namen we will build a base64 encoder and decoder here in order to fix this shit


function base64ToBytes(base64){
  const binString = atob(base64);
  
  return Uint8Array.from(binString, (m) => {m.codePointAt(0);})
}


function bytesToBase64(bytes){
  const binString = String.fromCodePoint(...bytes);

  return btoa(binString);
}

// decoding with it
function base64Decode(input){

  return new TextDecoder().decode(base64ToBytes(input));

}


// this will be the export make things a bit cleaner
function base64Encode(input){

  return bytesToBase64(new TextEncoder().encode(input));
}


export { base64Decode , base64Encode }

