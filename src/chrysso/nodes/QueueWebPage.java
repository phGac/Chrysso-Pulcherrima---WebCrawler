package chrysso.nodes;

import chrysso.page.WebPage;

public class QueueWebPage {
	
	private class NodeWebPage {
		public WebPage webPage;
		public NodeWebPage pointer;
		
		public NodeWebPage(WebPage webPage) {
			this.webPage = webPage;
			this.pointer = null;
		}
	}
	
	private NodeWebPage head;
	private NodeWebPage last;
	private int size;

	public QueueWebPage() {
		this.head = null;
		this.size = 0;
	}
	
	public void enqueue(WebPage web) {
		NodeWebPage newNode = new NodeWebPage(web);
		if(this.head == null) {
			this.head = newNode;
		} else {
			this.last.pointer = newNode;
		}
		this.last = newNode;
		this.size++;
	}
	
	public WebPage dequeue() {
		if(this.head != null) {
			WebPage web = this.head.webPage;
			NodeWebPage aux = this.head;
			
			this.head = aux.pointer;
			aux.pointer = null;
			if(this.head == null)
				this.last = null;
			this.size--;
			return web;
		} else {
			return null;	
		}
	}
	
	public WebPage get() {
		if(this.head == null) {
			return null;
		} else {
			return this.head.webPage;
		}
	}
	
	public void remove() {
		if(this.head != null) {
			NodeWebPage aux = this.head;
			this.head = aux.pointer;
			aux.pointer = null;
			if(this.head == null) {
				this.last = null;
			}
			this.size--;
		}
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return (this.size == 0);
	}

}
