package org.scrapper.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Parser {

    List<String> parse(File sourceFile) throws IOException;
    Map<String,String> parse(String command);

}
