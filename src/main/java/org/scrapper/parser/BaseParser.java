package org.scrapper.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Base class implementing Parser, inherit from this class and override the methods as per requirements.
 */
public class BaseParser implements Parser {


    @Override
    public List<String> parse(File sourceFile) throws IOException {
        return null;
    }

    @Override
    public Map<String, String> parse(String commandString) {
        return null;
    }
}
