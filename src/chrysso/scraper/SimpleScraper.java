package chrysso.scraper;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import chrysso.nodes.QueueUrlPage;
import chrysso.page.UrlPage;

public class SimpleScraper implements IScraper {
	
	private String url;
	private Document document;
	
	@Override
	public Document getDocument() {
		return this.document;
	}
	
	@Override
	public boolean scrape(String url) {
		this.url = url;
		try {
			Connection connection = Jsoup.connect(url);
			connection.headers(this.getHeaders());
			//connection.proxy("208.98.186.80", 53630);
			Document doc = connection.get();
			this.document = doc;
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public Map<String, String> getHeaders() {
		@SuppressWarnings("rawtypes")
		Map map = new HashMap();
		map.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		map.put("Accept-Language", "en-US,en;q=0.5");
		map.put("Connection", "close"); //keep-alive
		map.put("Cookie", "devicePixelRatio=1");
		map.put("Pragma", "no-cache");
		map.put("Upgrade-Insecure-Requests", "1");
		map.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1");
		return map;
	}
	
	@Override
	public Elements metadatas() {
		return this.document.head().select("meta");
	}
	
	@Override
	public String title() {
		return this.document.title();
	}
	
	@Override
	public String charset() {
		Charset charset = this.document.charset();
		return charset.displayName();
	}
	
	@Override
	public QueueUrlPage getAllURLs() {
		String pattern = "([https://|http://].+)(/.+)"; // sólo acepta links externos o rutas internas, no #id.
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher;
		
		Elements elements = this.document.body().select("a"); //href with 'a' label
		QueueUrlPage queue = new QueueUrlPage();
		
		String href, context;
		for( Element e : elements ) {
			href = e.attr("href");
			matcher = regex.matcher(href);
			if(matcher.find()) {
				if(href.startsWith("/")) {
					try{
						String uri = this.baseUri();
						URL url = new URL(uri);
						href = url.getProtocol() + "://" + url.getHost() + href;
					}catch(Exception ex) {}
				}
				context = e.outerHtml();
				queue.enqueue(new UrlPage(href, context));
			}
		}
		
		return queue;
	}

	@Override
	public boolean supportMobile() {
		Element meta = this.document.head().selectFirst("meta[name=\"viewport\"]");
		return (meta != null);
	}

	@Override
	public String url() {
		return this.url;
	}

	@Override
	public String finalUrl() {
		return null;
	}

	@Override
	public String baseUri() {
		return this.document.baseUri();
	}
	
	@Override
	public boolean isSecure() {
		String[] uri = this.baseUri().split("://");
		return (uri[0].toUpperCase().equals("HTTPS"));
	}

	@Override
	public boolean useJquery() {
		boolean useJquery = false;
		Elements elements = this.document.select("script");
		String src;
		for(Element e : elements) { 
			src = e.attr("src").toUpperCase(); //example => https://code.jquery.com/jquery-3.3.1.min.js
			if(src.contains("JQUERY")) {
				useJquery = true;
			}
		}
		return useJquery;
	}

}
