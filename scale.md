# Scale  
To scale the API, we should implement the API Gateway to balance the load using the Service Discovery and then spin up replicated pods.  
We can think about calculating and persisting some data locally to decrease the cost of some operations, like in the top 10 rated list that counts and calls the api for each movie every time.  
There`re other improvements quoted in the [to_do.md](to_do.md) file
