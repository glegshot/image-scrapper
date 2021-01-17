package org.scrapper.downloader;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface Downloader {

    Map<String, String> download(File destinationPath, List<String> httpLinks);

}
