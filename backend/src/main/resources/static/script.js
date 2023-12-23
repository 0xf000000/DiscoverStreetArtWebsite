fetch("http://localhost:8080/streetArt/all")
.then((response) => response.json())
.then((json) => console.log(json));

