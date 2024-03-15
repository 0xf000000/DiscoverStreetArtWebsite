-------------------------------------------------------
Plan for Discover StreetArt Website For Uni
--------------------------------------------------------

TeckStack
- SpringBoot
- Libra (the js MapLibary)
- we will se but that for now it
- mysql
- maeby s3BUCKEt

 COOL LOGIN PAGE https://codepen.io/alvaromontoro/pen/JjoWVmx
--WHAT DOES IT DO--------------------------------------:---

- eine Webapp die einem ermoeglicht all StreetArt in seiner Umgebung angezeigt zu bekommen.
- man soll sich Einloggen koennen und dann die moeglichkeit haben auch entdeckte streetArt selber hochzuLaden.
- es koennte auch einen Block geben, sowie eine Art Forum in der informationen ueber diese sache ausgetauscht werden kann.
-- um selber streetArt zu sehen sollte man nicht eingelogt sein sollen,
eine Moeglichkeit narchichten zu speichern
- PLAN FÜR HEUTE 

schoene FILE UPLOAD PAGE SCHREIBEN -> 

-- FORGOTT PASSWORD?


----------------------------------------------------------


PAGES

/Landing Page ( sollte einen Call to Action haben, und vielleicht eine kleine Slideshow, die die neusten bilder anzeigt.
/register
/map
/upload
/Blog 
/members // ist vielleicht zu viel, da dies unsere arbeit um einiges erschschwären würde
DATABASE STRUCTS

TABLE1 USER( username, password, email) 

TABLE2= StreetArtPoints( Geolocation, picture, creation, artist, description, ArtID PRIMARY KEY)


TABLE3= COMMENTS( extern key to streetArt, varCHAR(200) COMMENT, userthat wrote the comment )


REST SCHNITSTELLE


// diese schnittstelle soll die daten eines punktes zuruecksenden koennen, der // gewaelt wird
GET -> api/v1/art/{$1}


// get ALL streetArtPoint
GET -> api/v1/art/all

// add new streetartPoint
POST -> api/v1/art

// get all comments from the streetArt Point ID
GET api/v1/comments/{$1} 


// post a new comment
POST api/v1/comments




DELETE -> api/v1/art/{$1}





# USER LOGIN SECURITY HANDELING

i would use spring security for developing this kind of handeling

so the deleteUser and addUser endpoints would fall appart


# SPRING SECURITY TASKS


- /upload muss einen Login brauchen
- die möglichkeit kommentare zu schreiben darf nur von USERN performt werden

# TASKS

1- FINISH SECURITY IMPLEMENTATION
2. BUILD BACKEND and data visualisation
okok -> Logout button
-> secure api
implement https 
-> when ur logged in, u cant visit login page and registration page






