package url_shortener;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This problem was asked by Microsoft.
 *
 * Implement a URL shortener with the following methods:
 *
 * shorten(url), which shortens the url into a six-character alphanumeric string, such as zLg6wl.
 * restore(short), which expands the shortened string into the original url. If no such shortened string exists,
 * return null.
 *
 * Hint: What if we enter the same URL twice?
 *
 */
class URLShortener{

    Map<String, String> shortToUrl;
    Map<String, String> urlToShort;
    char[] dict;
    int lengthOfShort = 6;
    Random rand = new Random();
    String domainBase = "";

    URLShortener(int length, String domainBase) {
        this.lengthOfShort = length;
        shortToUrl = new HashMap();
        urlToShort = new HashMap();
        int index = 0;
        dict = new char[62];
        for (int i = 0; i < 10; i ++) {
            dict[index] = (char) ('0' + i);
            index++;
        }
        for (int i = 0; i < 26; i ++) {
            dict[index] = (char) ('a' + i);
            index++;
        }
        for (int i = 0; i < 26; i ++) {
            dict[index] = (char) ('A' + i);
            index++;
        }
        this.domainBase = domainBase;
    }

    String makeShortUrl(String url) {
        if (urlToShort.containsKey(url))
            return addDomainToShort(urlToShort.get(url));
        else {
            String shortenedUrl = generateShort(url);
            shortToUrl.put(shortenedUrl, url);
            urlToShort.put(url, shortenedUrl);
            return addDomainToShort(shortenedUrl);
        }
    }

    String retriveUrl(String shortUrl) {
        shortUrl = parseShortFromDomain(shortUrl);
        if (shortToUrl.containsKey(shortUrl))
            return shortToUrl.get(shortUrl);
        else
            return null;
    }

    private String generateShort(String url) {
        boolean isOk = false;
        String key = "";
        while(!isOk) {
            key = "";
            for (int i = 0; i < lengthOfShort; i++) {
                key += dict[rand.nextInt(dict.length)];
            }
            if (!shortToUrl.containsKey(key))
                isOk = true;
        }
        return key;
    }

    private String addDomainToShort(String shortUrl) {
        return domainBase + "/" + shortUrl;
    }

    private String parseShortFromDomain(String shortUrl) {
        if (shortUrl.startsWith(domainBase))
            return shortUrl.substring(domainBase.length() + 1);
        else
            return "";
    }

    public static void main(String[] args) {
        URLShortener obj = new URLShortener(10, "http://sho.rt");
        String[] urls = new String[] {"google.com", "yahoo.com", "hulu.com", "google.com", "allmusic.com", "hulu.com"};
        for (String url : urls ) {
            System.out.print("original URL : " + url);
            String shortUrl = obj.makeShortUrl(url);
            System.out.print(", short URL : " + shortUrl);
            System.out.println(", decoded URL : " + obj.retriveUrl(shortUrl));
        }
    }
}
