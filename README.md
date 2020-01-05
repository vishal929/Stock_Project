



This is my financial data scraping project implemented in Java.

Basically, this project is just to get my feet wet with logging, YAML format, and scraping.

Dependencies: You will need Log4j2, SnakeYAML, and jsoup in order to run my progam. 


Once you set up the paths for Log4j2,SnakeYAML, and jsoup, you first compile all the java documents.

i.e: javac -cp ".;%pathtologcore%;%pathtologapi%;%pathtosnakeyaml%;%pathtojsoup%;" *.java


Then, you are ready to run the driver of the project: 


i.e java -cp ".;%pathtologcore%;%pathtologapi%;%pathtosnakeyaml%;%pathtojsoup%;" Driver.java


From the options, you can choose to gather data from an annual filing for a certain company for a certain year. 
I am planning on implementing an API to get stock prices as well to help with analysis.


BIG PROBLEM: SEC is very lax in their xbrl format: the us_gaap namespaces change from year to year, so it is very hard to actually scrape the data because I need to account for all possible namespaces. If I cannot figure out how to solve this, then I at least learned practices of logging,YAML list format, and scraping.  
