package random_problems;

import java.util.HashMap;
import java.util.Map;

public class Codec {
  Map<String, String> idToUrlMap = new HashMap();
  Map<String, String> urlToIdMap = new HashMap();

  long count = 0;

  char[] map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
  String preffix = "http://tinyurl.com/";

  public Codec() {
    idToUrlMap.clear();
    urlToIdMap.clear();
  }

  // Encodes a URL to a shortened URL.
  public String encode(String longUrl) {
    if (urlToIdMap.containsKey(longUrl))
      return preffix + urlToIdMap.get(longUrl);

    String tinyUrl = encodeIdToStr(++count);
    urlToIdMap.put(longUrl, tinyUrl);
    idToUrlMap.put(tinyUrl, longUrl);
    return preffix + tinyUrl;
  }

  // Decodes a shortened URL to its original URL.
  public String decode(String shortUrl) {
    return idToUrlMap.get(shortUrl.substring(preffix.length()));
  }


  String encodeIdToStr(long id) {
    StringBuilder sb = new StringBuilder();
    while(id > 0) {
      int idx = (int)(id%62);
      sb.append(map[idx]);
      id = id/62;
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Codec obj = new Codec();
    //System.out.println(obj.encoder.withoutPadding().encodeToString("this is test string that I need to encode".getBytes()));
  }
}
