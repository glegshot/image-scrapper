package org.scrapper.parser;


import java.util.HashMap;
import java.util.Map;

public class CommandParser extends BaseParser {

    @Override
    public Map<String, String> parse(String[] command) {
        Map<String, String> results = new HashMap<>();

        for(int i = 0;i<command.length;i=i+2){
            if("-s".equals(command[i])){
                results.put(command[i],command[i+1]);
            }
            if("-d".equals(command[i])){
                results.put(command[i], command[i+1]);
            }
        }

        return results;
    }


}
