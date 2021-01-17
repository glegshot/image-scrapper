package org.scrapper.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientAdapter {

    public Map<String,String> download(List<String> httpLinks){
        Map<String, String> results = new HashMap<>();
        results.put("http://abc.jpeg", "success");
        return results;
    }

}
