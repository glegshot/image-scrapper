package org.scrapper.downloader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpLinkDownloader implements Downloader {

    @Override
    public Map<String, String> download(File destinationPath, List<String> httpLinks) {

        Map<String, String> results = new HashMap<>();
        results.put("https://games.mxdwn.com/wp-content/uploads/2020/11/doooom.jpg", "OK");
        results.put("https://specials-images.forbesimg.com/imageserve/5dc5a8f1ca425400073c556a/960x0.jpg", "OK");
        return results;
    }
}
