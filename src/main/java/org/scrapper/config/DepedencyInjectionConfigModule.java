package org.scrapper.config;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.scrapper.ImageScrapperApplication;
import org.scrapper.adapter.HttpAdapter;
import org.scrapper.adapter.OkHttpAdapter;
import org.scrapper.downloader.Downloader;
import org.scrapper.downloader.HttpLinkDownloader;
import org.scrapper.parser.CommandParser;
import org.scrapper.parser.HttpLinkParser;
import org.scrapper.parser.Parser;

public class DepedencyInjectionConfigModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(HttpAdapter.class).to(OkHttpAdapter.class);
        bind(Downloader.class).to(HttpLinkDownloader.class);
        bind(Parser.class).annotatedWith(Names.named("HttpLinkParser")).to(HttpLinkParser.class);
        bind(Parser.class).annotatedWith(Names.named("CommandParser")).to(CommandParser.class);
    }

}
