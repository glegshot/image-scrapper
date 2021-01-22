package org.scrapper.parser;


import java.util.HashMap;
import java.util.Map;

public class CommandParser extends BaseParser {

    @Override
    public Map<String, String> parse(String commandString) {
        Map<String, String> results = new HashMap<>();
        results.put("-s","./sample.html");
        results.put("-d","./hello");
        return results;
    }
}
