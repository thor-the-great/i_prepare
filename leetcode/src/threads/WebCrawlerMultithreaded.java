
package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 1242. Web Crawler Multithreaded
 * Medium
 *
 * Given a url startUrl and an interface HtmlParser, implement a Multi-threaded web crawler to
 * crawl all links that are under the same hostname as startUrl.
 *
 * Return all urls obtained by your web crawler in any order.
 *
 * Your crawler should:
 *
 * Start from the page: startUrl
 * Call HtmlParser.getUrls(url) to get all urls from a webpage of given url.
 * Do not crawl the same link twice.
 * Explore only the links that are under the same hostname as startUrl.
 *
 *
 * As shown in the example url above, the hostname is example.org. For simplicity sake, you may
 * assume all urls use http protocol without any port specified. For example, the urls
 * http://leetcode.com/problems and http://leetcode.com/contest are under the same hostname,
 * while urls http://example.org/test and http://example.com/abc are not under the same hostname.
 *
 * The HtmlParser interface is defined as such:
 *
 * interface HtmlParser {
 *   // Return a list of all urls from a webpage of given url.
 *   // This is a blocking call, that means it will do HTTP request and return when this request
 *   is finished.
 *   public List<String> getUrls(String url);
 * }
 * Note that getUrls(String url) simulates performing a HTTP request. You can treat it as a
 * blocking function call which waits for a HTTP request to finish. It is guaranteed that getUrls
 * (String url) will return the urls within 15ms.  Single-threaded solutions will exceed the time
 * limit so, can your multi-threaded web crawler do better?
 *
 * Below are two examples explaining the functionality of the problem, for custom testing
 * purposes you'll have three variables urls, edges and startUrl. Notice that you will only have
 * access to startUrl in your code, while urls and edges are not directly accessible to you in code.
 *
 *
 *
 * Follow up:
 *
 * Assume we have 10,000 nodes and 1 billion URLs to crawl. We will deploy the same software onto
 * each node. The software can know about all the nodes. We have to minimize communication
 * between machines and make sure each node does equal amount of work. How would your web crawler
 * design change?
 * What if one node fails or does not work?
 * How do you know when the crawler is done?
 *
 *
 * Example 1:
 *
 *
 *
 * Input:
 * urls = [
 *   "http://news.yahoo.com",
 *   "http://news.yahoo.com/news",
 *   "http://news.yahoo.com/news/topics/",
 *   "http://news.google.com",
 *   "http://news.yahoo.com/us"
 * ]
 * edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
 * startUrl = "http://news.yahoo.com/news/topics/"
 * Output: [
 *   "http://news.yahoo.com",
 *   "http://news.yahoo.com/news",
 *   "http://news.yahoo.com/news/topics/",
 *   "http://news.yahoo.com/us"
 * ]
 * Example 2:
 *
 *
 *
 * Input:
 * urls = [
 *   "http://news.yahoo.com",
 *   "http://news.yahoo.com/news",
 *   "http://news.yahoo.com/news/topics/",
 *   "http://news.google.com"
 * ]
 * edges = [[0,2],[2,1],[3,2],[3,1],[3,0]]
 * startUrl = "http://news.google.com"
 * Output: ["http://news.google.com"]
 * Explanation: The startUrl links to all other pages that do not share the same hostname.
 *
 *
 * Constraints:
 *
 * 1 <= urls.length <= 1000
 * 1 <= urls[i].length <= 300
 * startUrl is one of the urls.
 * Hostname label must be from 1 to 63 characters long, including the dots, may contain only the
 * ASCII letters from 'a' to 'z', digits from '0' to '9' and the hyphen-minus character ('-').
 * The hostname may not start or end with the hyphen-minus character ('-').
 * See:  https://en.wikipedia.org/wiki/Hostname#Restrictions_on_valid_hostnames
 * You may assume there're no duplicates in url library.
 */
public class WebCrawlerMultithreaded {

    /**
     * Create pool of threads, put URLs to parse into the blocking queue. When url has been parsed -
     * add it to the set. At the end copy from set to list
     */
    ConcurrentHashMap map = new ConcurrentHashMap<>();
    Random rand = new Random();
    public List<String> crawl(String startUrl, HtmlParser htmlParser)  {
        Set<String> seen = map.newKeySet();
        BlockingQueue<String> q = new LinkedBlockingQueue<>();
        q.add(startUrl);
        String hostname = getHostname(startUrl);

        int N = 35;
        CountDownLatch latch = new CountDownLatch(N);

        Runnable worker = () -> {
            try {
                while(true) {
                    String url = q.poll(25 + rand.nextInt(25), TimeUnit.MILLISECONDS);
                    if (url == null)
                        break;
                    seen.add(url);
                    for (String nextUrl : htmlParser.getUrls(url)) {
                        if (!seen.contains(nextUrl) && isSameHost(hostname, nextUrl)) {
                            q.add(nextUrl);
                        }
                    }
                }
            } catch (InterruptedException e) {
            } finally {
                latch.countDown();
            }
        };

        for (int w = 0; w < N; w++)
            new Thread(worker).start();

        try {
            latch.await();
        } catch (Exception e) {}

        return new ArrayList(seen);
    }

    String getHostname(String url) {
        int idx = url.indexOf("/", 7);
        return idx == -1 ? url : url.substring(0, idx);
    }

    boolean isSameHost(String host, String newUrl) {
        if (newUrl.startsWith(host)) {
            return true;
        }
        return false;
    }
}

 interface HtmlParser {
    List<String> getUrls(String url);
 }
