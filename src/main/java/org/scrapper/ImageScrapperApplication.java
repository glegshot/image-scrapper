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

    private final Parser httpLinkParser;
    private final Downloader httpLinkDownloader;

    @Inject
    public ImageScrapperApplication(@Named("HttpLinkParser")Parser httpLinkParser, Downloader httpLinkDownloader) {
        this.httpLinkParser = httpLinkParser;
        this.httpLinkDownloader = httpLinkDownloader;
    }

    public Map<String, String> scrapForImages(String sourcePath, String destinationPath) throws IOException {

        logger.info("Source File Path: {}, Destination Directory Path: {}", sourcePath, destinationPath);
        List<String> httpLinks = extractHttpLinksFromFile(sourcePath);
        logger.info("Total Image Links Found: {}", httpLinks.size());
        logger.info("Downloading .....");
        Map<String, String> results = httpLinkDownloader.download(destinationPath, httpLinks);
        results.forEach((key, value) -> logger.info(key + " ==> " + value));
        return results;

    }

    private List<String> extractHttpLinksFromFile(String sourcePath) throws IOException {
        File sourceFile = new File(sourcePath);
        return httpLinkParser.parse(sourceFile);
    }

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new DepedencyInjectionConfigModule());
        ImageScrapperApplication imageScrapperApplication = injector.getInstance(ImageScrapperApplication.class);
        try {

            Map<String,String> commands = new CommandParser().parse(args);
            Map<String, String> results = imageScrapperApplication.scrapForImages(commands.get("-s"), commands.get("-d"));

        }catch (IOException e){
            logger.error("ERROR {}",e.getLocalizedMessage());
        }
    }

}
