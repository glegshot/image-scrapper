package org.scrapper.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.Map;

@RunWith(JUnit4.class)
public class CommandParserTest {

    Parser commandParser;

    @Before
    public void init() {
        commandParser = new CommandParser();
    }

    @Test
    public void successfullyParseValidCommandStringWithSourceAndDestinationValues() {

        String commandString = "-s ./sample.html -d ./hello";
        Map<String,String> results =  commandParser.parse(commandString);
        Assert.assertEquals(results.get("-s"),"./sample.html");
        Assert.assertEquals(results.get("-d"), "./hello");

    }

}
