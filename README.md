# dbbikes_springboot
Dublin Bikes, Spring Boot, ThymeLeaf

Using JCDecaux API with Spring Boot and ThymeLeaf to display 
- a list of JCDecaux contracts
- a list of Dublin stations
- to show stations on OpenStreetMap using LeafletJS

Use realtime api
=================
An api key may be obtained from JCDecaux to retrieve realtime data from the api.


Use recorded data
=================
Without an api key the application can still be used
Pre-recorded JSON data from the api has been saved. 
Set the profile in application.properties
**spring.profiles.active=recorded**
