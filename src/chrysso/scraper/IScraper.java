package chrysso.scraper;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import chrysso.nodes.QueueUrlPage;

public interface IScraper {
	Document getDocument();
	boolean scrape(String url);
	Elements metadatas();
	boolean supportMobile();
	String title();
	String charset();
	String url();
	String finalUrl();
	String baseUri();
	QueueUrlPage getAllURLs();
	boolean isSecure();
	boolean useJquery();
}
