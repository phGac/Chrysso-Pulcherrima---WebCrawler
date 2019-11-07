package chrysso;

import chrysso.nodes.QueueUrlPage;
import chrysso.nodes.QueueWebPage;
import chrysso.page.WebPage;
import chrysso.scraper.SimpleScraper;

public class ChryssoPulcherrima {

	private SimpleScraper scraper;
	
	public ChryssoPulcherrima() {
		this.scraper = new SimpleScraper();
	}
	
	public ChryssoPulcherrima(String proxyIP, String port) {
		System.setProperty("https.proxyHost", proxyIP);
		System.setProperty("https.proxyPort", port);
		this.scraper = new SimpleScraper();
	}
	
	public QueueWebPage scrape(QueueUrlPage urls) {
		QueueWebPage webs = new QueueWebPage();
		webs = this.scraperUrls(urls, this.scraper, webs, 0);
		return webs;
	}
	
	public QueueWebPage scrape(String[] urls) {
		QueueWebPage webs = new QueueWebPage();
		webs = this.scraperUrls(urls, this.scraper, webs, 0);
		return webs;
	}
	
	private QueueWebPage scraperUrls(String[] urls, SimpleScraper sscraper, QueueWebPage websToReturn, int position) {
		if(urls.length > position) {
			WebPage web = new WebPage();
			
			sscraper.scrape(urls[position]);
			web = this.setsWebPage(web, sscraper);
			
			websToReturn.enqueue(web);
			position++;
			this.scraperUrls(urls, sscraper, websToReturn, position);
		}
		return websToReturn;
	}
	
	private QueueWebPage scraperUrls(QueueUrlPage urls, SimpleScraper sscraper, QueueWebPage websToReturn, int position) {
		if(urls.size() > 0) {
			String url = urls.dequeue().getUrl();
			WebPage web = new WebPage();
			
			sscraper.scrape(url);
			web = this.setsWebPage(web, sscraper);
			
			websToReturn.enqueue(web);
			position++;
			this.scraperUrls(urls, sscraper, websToReturn, position);
		}
		return websToReturn;
	}
	
	private WebPage setsWebPage(WebPage web, SimpleScraper scraper) {
		web.setDocument(scraper.getDocument());
		web.setName(scraper.title());
		web.setUrl(scraper.url());
		web.setSecure(scraper.isSecure());
		web.setCharset(scraper.charset());
		web.setMobile(scraper.supportMobile());
		web.setLinks(scraper.getAllURLs());
		//web.setLinksPointer(sscraper.getAllURLs());
		
		web.setUseJquery(scraper.useJquery());
		return web;
	}
	
}
