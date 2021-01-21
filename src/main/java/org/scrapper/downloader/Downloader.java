package org.scrapper.downloader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Downloader {

    Map<String, String> download(File destinationPath, List<String> httpLinks) throws IOException;

}
