package ua.kiev.prog.ShortLinkServlet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UrlDatabase {

    public final static UrlDatabase INSTANCE = new UrlDatabase();

    private final Map<String, UrlRecord> db = new HashMap<>();

    private UrlDatabase() {
    }

    public synchronized String save(String url) {
        if (isUrlExist(url)) {
            Iterator<String> i = db.keySet().iterator();
            String key = "";
            while (i.hasNext()) {
                key = i.next();
                if (getShort(key).equals(url)) {
                    break;
                }
            }
            return key;
        } else {
            String key = RandomString.randomString();

            UrlRecord value = new UrlRecord();
            value.setUrl(url);

            db.put(key, value);
            return key;
        }
    }

    public synchronized String get(String shortUrl) {
        UrlRecord value = db.get(shortUrl);
        value.inc();

        return value.getUrl();
    }

    private synchronized String getShort(String shortUrl){
        UrlRecord value = db.get(shortUrl);
        return value.getUrl();
    }

    public synchronized Collection<UrlRecord> getStats() {
        return db.values();
    }

    private boolean isUrlExist(String url) {
        for (UrlRecord urlRecord : db.values()) {
            if (urlRecord.getUrl().equals(url)) return true;
        }
        return false;
    }

    static public class UrlRecord {
        private String url;
        private long counter;

        public void inc() {
            counter++;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getCounter() {
            return counter;
        }

        public void setCounter(long counter) {
            this.counter = counter;
        }
    }
}
