# RateMySpace
### How do I send things to a server?
Preferably, you should never have to. Currently the "Reviewer" class is capable of doing the following actions that will eventually find their way to a query to the server:

1. sendReview(Review review);
2. reportReview(Review review, Review.Flag flag);
3. getReviews(String searchTerm, SearchType searchType);

The Reviewer will collect the data and parse it automagically. Then it calls the QueryManager which handles connecting to the MySQL server, futzing with drivers and actually sending the queries. 

Reviewer acts as an abstraction away from all that messyness. 

