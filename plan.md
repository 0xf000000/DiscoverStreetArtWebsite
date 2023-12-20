--------------------------------------------------------
Plan for Discover StreetArt Website For Uni
--------------------------------------------------------

TeckStack
- SpringBoot
- Libra (the js MapLibary)
- we will se but that for now it
- mysql
- maeby s3BUCKEt


--WHAT DOES IT DO-----------------------------------------

- eine Webapp die einem ermoeglicht all StreetArt in seiner Umgebung angezeigt zu bekommen.
- man soll sich Einloggen koennen und dann die moeglichkeit haben auch entdeckte streetArt selber hochzuLaden.
- es koennte auch einen Block geben, sowie eine Art Forum in der informationen ueber diese sache ausgetauscht werden kann.
-- um selber streetArt zu sehen sollte man nicht eingelogt sein sollen,
eine Moeglichkeit narchichten zu speichern

----------------------------------------------------------


PAGES

/Landing Page ( sollte einen Call to Action haben, und vielleicht eine kleine Slideshow, die die neusten bilder anzeigt.
/Login
/register
/Logout
/map
/Blog
/upload
/members

DATABASE STRUCTS

TABLE1 = USER( username, password(hash), salt, userID, emailAddress(we could here think about encrypting),  


TABLE2= StreetArtPoints( Geolocation, picture, creation, artist, description, ArtID PRIMARY KEY)


TABLE3= COMMENTS( extern key to streetArt, varCHAR(200) COMMENT, userthat wrote the comment )


REST SCHNITSTELLE


// diese schnittstelle soll die daten eines punktes zuruecksenden koennen, der // gewaelt wird
GET -> api/v1/art/{$1}


// get ALL streetArtPoint
GET -> api/v1/art/all


// get all comments from the streetArt Point ID
GET api/v1/comments/{$1} 


// post new User 
POST api/v1/art/

// adds a User to the database
PUT api/v1/adduser/


// deletes a user
DELETE api/v1/deleteUser



















