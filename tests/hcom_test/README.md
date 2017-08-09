# Task:

Your task is to write a web application which consumes data from three different data sources - represented as CSV files:
* ThirdPartyDataStore.csv
* SingleSourceOfTruthDataStore.csv
* OraculumDataStore.csv

These contain key-value pairs.
 
## The requirements are:
* there should be a page with a single button labelled as "Get data"
* upon clicking it on the result page the values should be alphabetically ordered and colored as:
  * data from ThirdPartyDataStore should be blue
  * data from SingleSourceOfTruthDataStore should be green
  * data from OraculumDataStore shuld be red
* all letters should be capital
* if you can't sort based on the letter use the key to compare them - lower id should come first
 
Write a Springboot based app, use frameworks, techniques that you prefer. **Write the app in way that tomorrow it will serve millions of users so you need to guarantee high quality!**
