package in.clayfish.printful.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import in.clayfish.printful.models.Configuration;

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public abstract class LibUtils {
    /**
     * @param url
     * @return
     */
    public static Connection createConnection(final String base64Key, final String url,
                                              final Configuration config) {
        return Jsoup.connect(url).timeout(config.getTimeout())
                .header("Accept", "application/json")
                .header("Accept-Encoding", "gzip, deflate, sdch, br")
                .header("Accept-Language", "en-GB,en;q=0.8,en-US;q=0.6,hi;q=0.4")
                .header("Connection", "keep-alive")
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36")
                .header("Content-type", "application/json")
                .ignoreContentType(true).header("Authorization", "Basic " + base64Key);
    }
}
