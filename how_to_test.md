## How to test

There's two main ways to test: 
## - Calling the endpoints using a client like Postman:

> ###### IMPORTANT
> All the endpoints are protected by Spring Security and you must to use the Basic Authentication to consume them.
> Was configured in memory user authentication, then use the username `backbase` and password `backbase` or add the header`'Authorization': 'Basic YmFja2Jhc2U6YmFja2Jhc2U='`. Otherwise you will get the **HttpStatus 401 (Unauthorized)**.  

### 1- Find movie by title:
This endpoint search a movie given a **movie's title** as parameter, and when it's found the response will have the movie's details.
> Method: GET  
> Url: `http://localhost:8080/api/v1/movies/by-title?title={title}`  
> Response Status: 200 (OK), 404 (Movie not found), 401 (Unauthorized)  
>
> eg. `http://localhost:8080/api/v1/movies/by-title?title=robocop`
>
> Response example:
>```
>{
>    "title": "RoboCop",
>    "year": "1987",
>    "rated": "R",
>    "released": "17 Jul 1987",
>    "runtime": "102 min",
>    "genre": "Action, Crime, Sci-Fi",
>    "director": "Paul Verhoeven",
>    "writer": "Edward Neumeier, Michael Miner",
>    "actors": "Peter Weller, Nancy Allen, Dan O'Herlihy",
>    "plot": "In a dystopic and crime-ridden Detroit, a terminally wounded cop returns to the force as a powerful cyborg haunted by submerged memories.",
>    "language": "English",
>    "country": "United States",
>    "awards": "Nominated for 2 Oscars. 11 wins & 13 nominations total",
>    "poster": "https://m.media-amazon.com/images/M/MV5BZWVlYzU2ZjQtZmNkMi00OTc3LTkwZmYtZDVjNmY4OWFmZGJlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg",
>    "ratings": [
>        {
>            "source": "Internet Movie Database",
>            "value": "7.6/10"
>        },
>        {
>            "source": "Rotten Tomatoes",
>            "value": "91%"
>        },
>        {
>            "source": "Metacritic",
>            "value": "70/100"
>        }
>    ],
>    "metascore": "70",
>    "imdbRating": "7.6",
>    "imdbVotes": "254,132",
>    "imdbID": "tt0093870",
>    "type": "movie",
>    "dvd": "01 Aug 2006",
>    "boxOffice": "$53,424,681",
>    "production": "N/A",
>    "website": "N/A",
>    "response": "True"
>}
>```


### 2- Indicate whether a movie won Oscar
This endpoint search for a movie's information for Oscar's nominees and awarded, given a **movie's title** as parameter. **Optionally** can be provided the category parameter, otherwise will return the default category "Best Picture".  
> Method: GET  
> Url: `http://localhost:8080/api/v1/nominees/by-movie?title={title}&category={category}`  
> Response Status: 200 (OK), 404 (Movie not found), 401 (Unauthorized)  
>
> e.g. `http://localhost:8080/api/v1/nominees/by-movie?title=titanic`  
> e.g. `http://localhost:8080/api/v1/nominees/by-movie?title=titanic&category=Directing`    
>
> Response example: 
>```
>{
>    "category": "Directing",
>    "wasWinner": true,
>    "wasNominee": true,
>    "yearOfNominated": "1997 (70th)"
>}
>```


### 3- Allow users to give a rating to movies
This endpoint allows to a user give a rating to a movie. Must provide in the payload the movie's title and the rate between 1 and 5.
> Method: POST  
> Url: `http://localhost:8080/api/v1/ratings`  
> Payload: ```{"rate": {rate}, "title": "{title}"} ```  
> Response Status: 200 (OK), 404 (Movie not found), 401 (Unauthorized), 400 (Bad Request)
>
> e.g. **valid Payload**: ```{"rate": 4,"title": "titanic"} ```  
> e.g. **Invalid Payload**: ```{"rate": 12,"title": "titanic"} ```  

### 4- Top 10 rated movies
This endpoint provides a list of 10 top-rated movies ordered by box office value. If the box office is unknown then will be put after the movies that contains the value, ordered by box office.
> Method: GET  
> Url: `http://localhost:8080/api/v1/ratings/top10-rated`  
> Response Status: 200 (OK), 401 (Unauthorized)  
>  
> Response example:
>```
>[
>{
>"imdbID": "tt0120338",
>"title": "Titanic",
>"votes": 2,
>"rating": 3.5,
>"boxOffice": 659363944
>},
>{
>"imdbID": "tt0114709",
>"title": "Toy Story",
>"votes": 1,
>"rating": 4.0,
>"boxOffice": 223225679
>},
>{
>"imdbID": "tt0093870",
>"title": "RoboCop",
>"votes": 1,
>"rating": 3.0,
>"boxOffice": 53424681
>},
>{
>"imdbID": "tt0411008",
>"title": "Lost",
>"votes": 1,
>"rating": 5.0,
>"boxOffice": null
>}
>]
>```

## - Frontend embedded
Just to collaborate on understanding all features, I built a frontend application using part of Backbase Tech stack: Angular, HTML, CSS, TypeScript.
The source code is in the /movie-front.zip file, [right here](movie-front.zip)  
Some things, like authentication, were replaced by hard coded to lower the time of codification. It's just an example of use, it's not production ready.   

The web application is published in the follow path and port:   
`http://localhost:8080/`  

![Front End capture](capture_screen_frontend.png)

