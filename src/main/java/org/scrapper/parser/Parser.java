package org.scrapper.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface Parser {

    List<String> parse(File sourceFile) throws IOException;

}
