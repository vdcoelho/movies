# Assumptions
I assumed that:   
- I shouldn't save any Omdb's data locally.
- When a movie is not found by title in the Omdb's API then I should return 404 (not found).
- The user can rate multiple times the same movie.
- The API key used in Omdb is our responsibility, the user doesn't need to know about it.