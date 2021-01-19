package org.scrapper.parser;

import org.scrapper.exception.NotARegularFileException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpLinkParser implements Parser {

    @Override
    public List<String> parse(File sourceFile) {
        validate(sourceFile);
        String sourceText = readFile(sourceFile);
        List<String> httpLinks = extractLinks(sourceText);

        return httpLinks;
    }

    private List<String> extractLinks(String text) {
        Pattern pattern = Pattern.compile("http.+(.jpg|.png|.jpeg|.tif|.bmp)");
        Matcher matcher = pattern.matcher(text);
        List<String> httpLinks = new ArrayList<>();
        while (matcher.find()) {
            httpLinks.add(matcher.group());
        }
        return httpLinks;
    }



    private String readFile(File file) {
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        StringBuilder stringBuilder = null;
        try {
             fileInputStream = new FileInputStream(file);
             bufferedInputStream = new BufferedInputStream(fileInputStream);
             stringBuilder = new StringBuilder();
            int currentValue;
            while ((currentValue = bufferedInputStream.read()) != -1) {
                stringBuilder.append((char) currentValue);
            }

            return stringBuilder.toString();

        } catch (FileNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (IOException e){
            System.out.println(e.getLocalizedMessage());
        }finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if(bufferedInputStream != null){
                    bufferedInputStream.close();
                }
            }catch (IOException e){
                System.out.println(e.getLocalizedMessage());
            }
        }

        return null;
    }

    private void validate(File file) {

        try {
            if (!file.exists()) {
                throw new FileNotFoundException(file.getAbsolutePath());
            }
            if (!file.isFile()) {
                throw new NotARegularFileException(file.getAbsolutePath() + ": Not a regular File");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (NotARegularFileException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
