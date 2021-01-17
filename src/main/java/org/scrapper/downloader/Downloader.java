package org.scrapper.downloader;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface Downloader {

    Map<String, String> download(String destinationPath, List<String> httpLinks);

}
