# Scale  
To scale the API, we should implement the API Gateway to balance the load using the Service Discovery and then spin up replicated pods.  
We can think about calculate and persist some data locally to decrease the cost os some operations, like in the top 10 rated list that count and call the api for each movie every time.  
There`re another improvements quoted in the [to_do.md](to_do.md) file
