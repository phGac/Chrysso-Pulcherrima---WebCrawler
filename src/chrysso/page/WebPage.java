package chrysso.page;

import org.jsoup.nodes.Document;

import chrysso.nodes.QueueUrlPage;

public class WebPage {
	
	private String name;
	private String url;
	private boolean secure;
	private String charset;
	private boolean mobile;
	private QueueUrlPage links;
	private UrlPage[] linksPointer;
	private int rankPage;
	private int value;
	
	private boolean useJquery;
	private Document document;
	
	public WebPage() {}
	
	public WebPage(String url) {
		this.url = url;
	}

	public WebPage(String name, String url, Document document) {
		this.name = name;
		this.url = url;
		this.document = document;
	}
	
	public WebPage(String name, String url, boolean secure, String charset, boolean mobile, UrlPage[] linksPointer, int rankPage, int value) {
		this.name = name;
		this.url = url;
		this.secure = secure;
		this.charset = charset;
		this.mobile = mobile;
		this.linksPointer = linksPointer;
		this.rankPage = rankPage;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean isSecure() {
		return secure;
	}
	
	public void setSecure(boolean secure) {
		this.secure = secure;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public boolean isMobile() {
		return mobile;
	}

	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

	public UrlPage[] getLinksPointer() {
		return linksPointer;
	}

	public void setLinksPointer(UrlPage[] linksPointer) {
		this.linksPointer = linksPointer;
	}

	public int getRankPage() {
		return rankPage;
	}

	public void setRankPage(int rankPage) {
		this.rankPage = rankPage;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
	public boolean getUseJquery() {
		return useJquery;
	}

	public void setUseJquery(boolean useJquery) {
		this.useJquery = useJquery;
	}
	
	public void setLinks(QueueUrlPage links) {
		this.links = links;
	}
	
	public QueueUrlPage getLinks() {
		return this.links;
	}
	
	public String toString() {
		return "WebPage[\n\tname='"+name+"',\n\turl='"+url+"',\n\tsecure="+secure+",\n\tcharset='"+charset+"',\n\tmobile="+mobile+",\n\trankPage="+rankPage+",\n\tvalue="+value+"\n]";
	}

}
