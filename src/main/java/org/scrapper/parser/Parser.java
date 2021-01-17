package org.scrapper.parser;

import java.io.File;
import java.util.List;

public interface Parser {

    public List<String> parse(File sourceFile);

}
