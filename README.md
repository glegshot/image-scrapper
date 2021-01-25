# image-scrapper
scrapper of http image links from an html file<br>

# usage:<br>
1.run the command <br>
 ```gradle clean assemble uberJar```
2.once the fat jar will be created then execute below command<br>
```java -jar image-scrapper-1.0-SNAPSHOT.jar -s <sourcefilepath> -d <destinationdirectorypath>```

# description:<br>
this is a java based application which will scrape all the http/https links in a file and download all image extension files like jpeg,png,bmp etcc.
the application is purely command line interface based and can be operated with a simple one line of command.
All we have to do is provide the path to the file with the data to be scrapped and a path of the directory to be saved all the downloaded files to.


