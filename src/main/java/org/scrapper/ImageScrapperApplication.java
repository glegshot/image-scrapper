package org.scrapper;


import com.google.inject.Guice;
import com.google.inject.Injector;
import org.scrapper.config.DepedencyInjectionConfigModule;
import org.scrapper.downloader.Downloader;
import org.scrapper.parser.CommandParser;
import org.scrapper.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ImageScrapperApplication {

    public static final Logger logger = LoggerFactory.getLogger(ImageScrapperApplication.class);

    Parser httpLinkParser;
    Downloader httpLinkDownloader;

    @Inject
    public ImageScrapperApplication(@Named("HttpLinkParser")Parser httpLinkParser, Downloader httpLinkDownloader) {
        this.httpLinkParser = httpLinkParser;
        this.httpLinkDownloader = httpLinkDownloader;
    }

    public Map<String, String> getImages(String sourcePath, String destinationPath) throws IOException {

        logger.info("Source File Path: {}, Destination Directory Path: {}", sourcePath, destinationPath);
        File sourceFile = new File(sourcePath);
        List<String> httpLinks = httpLinkParser.parse(sourceFile);
        logger.info("Total Image Links Found: {}", httpLinks.size());
        logger.info("Downloading .....");
        Map<String, String> results = httpLinkDownloader.download(destinationPath, httpLinks);
        results.entrySet().stream().forEach(entry -> logger.info(entry.getKey()+" ==> "+entry.getValue()));
        return results;

    }

    public static void main(String[] args) {

        Parser commandParser = new CommandParser();

        Injector injector = Guice.createInjector(new DepedencyInjectionConfigModule());
        ImageScrapperApplication imageScrapperApplication = injector.getInstance(ImageScrapperApplication.class);
        try {

            Map<String,String> commands = commandParser.parse(args);
            Map<String, String> results = imageScrapperApplication.getImages(commands.get("-s"), commands.get("-d"));

        }catch (IOException e){
            logger.error("ERROR {}",e.getLocalizedMessage());
        }
    }

}
