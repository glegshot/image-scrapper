package org.scrapper.parser;

import org.scrapper.exception.NotARegularFileException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpLinkParser extends BaseParser {

    @Override
    public List<String> parse(File sourceFile) throws IOException {
        validate(sourceFile);
        String sourceText = readFile(sourceFile);
        List<String> httpLinks = extractLinks(sourceText);

        return httpLinks;
    }

    private List<String> extractLinks(String text) {
        String regex = "http.+(.jpg|.png|.jpeg|.tif|.bmp)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        List<String> httpLinks = new ArrayList<>();
        while (matcher.find()) {
            httpLinks.add(matcher.group());
        }
        return httpLinks;
    }


    private String readFile(File file) throws IOException {
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            int currentValue;
            while ((currentValue = bufferedInputStream.read()) != -1) {
                stringBuilder.append((char) currentValue);
            }

        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }

        return stringBuilder.toString();
    }

    private void validate(File file) throws FileNotFoundException {

        if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath());
        }
        if (!file.isFile()) {
            throw new NotARegularFileException(file.getAbsolutePath() + ": Not a regular File");
        }

    }
}
