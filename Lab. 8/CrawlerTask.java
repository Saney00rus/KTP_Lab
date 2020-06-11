import java.io.IOException;

public class CrawlerTask implements Runnable {

    private URLPool urlPool;
    private URLDepthPair urlDepthPair;

    public CrawlerTask(URLPool pool) throws IOException
    {
        urlPool = pool;
    }

    public void run() {
        while (true) {
            urlDepthPair = urlPool.getURL();
            try {
                Crawler crawler = new Crawler(urlDepthPair);
                urlPool.addListURL(crawler.getUrl());
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
