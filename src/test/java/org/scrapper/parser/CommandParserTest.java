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

        String[] command = new String[4];
        command[0] = "-s";
        command[1] = "./sample.html";
        command[2] = "-d";
        command[3] = "./hello";

        Map<String, String> results = commandParser.parse(command);
        Assert.assertEquals(results.get("-s"), "./sample.html");
        Assert.assertEquals(results.get("-d"), "./hello");

    }


    @Test
    public void successfullyParseValidCommandStringWithSourceOnly() {

        String[] command = new String[4];
        command[0] = "-s";
        command[1] = "./sample.html";
        command[2] = "-d";
        command[3] = "";

        Map<String, String> results = commandParser.parse(command);
        Assert.assertEquals(results.get("-s"), "./sample.html");
        Assert.assertEquals(results.get("-d"), "");

    }

    @Test
    public void returnEmptyForInvalidCommandParameters() {

        String[] command = new String[4];
        command[0] = "-e";
        command[1] = "./sample.html";
        command[2] = "-g";
        command[3] = "";

        Map<String, String> results = commandParser.parse(command);
        Assert.assertEquals(results.size(),0);

    }


}
