function assertFileIsValid(fileList){

    const allowedTypes = ["image/jpeg", "image/png"];
 
    for(const file of fileList){
       const { name: fileName } = file;
       
       if(!allowedTypes.includes(file.type)){
         return false;
       }
 
    }
    
    return true;
 }


 export {assertFileIsValid};