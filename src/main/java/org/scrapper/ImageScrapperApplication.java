package org.scrapper;


import org.scrapper.adapter.HttpAdapter;
import org.scrapper.adapter.OkHttpAdapter;
import org.scrapper.downloader.Downloader;
import org.scrapper.downloader.HttpLinkDownloader;
import org.scrapper.parser.HttpLinkParser;
import org.scrapper.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ImageScrapperApplication {

    public static final Logger logger = LoggerFactory.getLogger(ImageScrapperApplication.class);

    Parser httpLinkParser;
    Downloader httpLinkDownloader;


    public ImageScrapperApplication(Parser httpLinkParser, Downloader httpLinkDownloader) {
        this.httpLinkParser = httpLinkParser;
        this.httpLinkDownloader = httpLinkDownloader;
    }

    public Map<String, String> getImages(String sourcePath, String destinationPath) throws IOException {

        logger.info("Source File Path: {}, Destination Directory Path: {}", sourcePath, destinationPath);
        File sourceFile = new File(sourcePath);
        List<String> httpLinks = httpLinkParser.parse(sourceFile);
        logger.info("Total Image Links Found: {}", httpLinks.size());
        Map<String, String> results = httpLinkDownloader.download(destinationPath, httpLinks);
        return results;

    }

    public static void main(String[] args) {

        for(String arg: args){
            System.out.println(arg);
        }
        /*HttpAdapter httpAdapter = new OkHttpAdapter();
        Parser httpLinkParser = new HttpLinkParser();
        Downloader httpLinkDownloader = new HttpLinkDownloader(httpAdapter);
        ImageScrapperApplication imageScrapperApplication = new ImageScrapperApplication(httpLinkParser, httpLinkDownloader);*/
        /*try {
            if("--help".equals(args[0])) {
                imageScrapperApplication.displayHelp();
            }else {
                Map<String, String> results = imageScrapperApplication.getImages(args[0], args[1]);
            }
        }catch (IOException e){
            logger.error("ERROR {}",e.getLocalizedMessage());
        }*/
    }

}
