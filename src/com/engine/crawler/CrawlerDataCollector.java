package com.engine.crawler;

import edu.uci.ics.crawler4j.crawler.*;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.*;

import com.engine.constant.*;
import com.engine.file.DirectoryFile;
import com.engine.file.PageFile;
import com.engine.parser.Directory;

public class CrawlerDataCollector extends WebCrawler {

	@Override
	public boolean shouldVisit(WebURL url) {
		String href = url.getURL();
		if (href == null) {
			return false;
		} else {
			href = href.toLowerCase();
		}
		return !href.contains("?")
				&& !href.startsWith("http://ftp.ics.uci.edu/pub/")
				&& !href.startsWith("http://fano.ics.uci.edu/ca/rules/")
				&& !href.startsWith("http://drzaius.ics.uci.edu/cgi-bin/cvsweb.cgi/")
				&& !href.matches(".*(/[^/]+)\\1\\1/")
				&& href.contains((Constant.CONTAIN_URL))
				&& !Constant.FILTERS.matcher(href).matches();
	}

	@Override
	public void visit(Page page) {
		int docid = page.getWebURL().getDocid();
		String url = page.getWebURL().getURL();

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			
			String pageContent = htmlParseData.getText();
			
			int pageContentLength = pageContent.length();
			Directory line = new Directory(docid, pageContentLength, url);
			DirectoryFile dirFile = new DirectoryFile(Constant.DIRECTORY_FILE_NAME);
			PageFile pageFile = new PageFile(Constant.PAGE_FILE_NAME + docid);
			dirFile.writeDirectory(line);
			pageFile.writePage(pageContent);
		}
		
		//trace
		System.out.println(docid);
	}

}
