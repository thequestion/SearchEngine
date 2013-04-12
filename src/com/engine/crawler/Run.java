package com.engine.crawler;

public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CrawlerController crawler = new CrawlerController();
		crawler.StartCrawl("http://www.ics.uci.edu/prospective/en/degrees/software-engineering/");
	}

}
