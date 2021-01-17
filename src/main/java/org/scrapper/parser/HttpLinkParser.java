package org.scrapper.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HttpLinkParser implements Parser{


    @Override
    public List<String> parse(File sourceFile) {
        
        List<String> httpLinks = new ArrayList<>();
        httpLinks.add("https://games.mxdwn.com/wp-content/uploads/2020/11/doooom.jpg");
        httpLinks.add("https://specials-images.forbesimg.com/imageserve/5dc5a8f1ca425400073c556a/960x0.jpg");

        return httpLinks;
    }
}
