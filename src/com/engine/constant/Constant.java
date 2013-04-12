/**
 * 
 */
package com.engine.constant;

import java.util.regex.Pattern;


/**
 * @author YUAN
 *
 */
public class Constant {
	/*
	 *  set the filter of crawler target
	 */
	public final static Pattern FILTERS = 
			Pattern.compile(".*(\\.(data|test|java|c|cpp|cc|z|rpm|uai|css|pfm|pptx|ppt|doc|docx|pdf|js|bmp|gif|jpe?g" 
                    + "|png|tiff?|mid|mp2|mp3|mp4"
                    + "|wav|avi|mov|mpeg|ram|m4v|pdf" 
                    + "|rm|smil|wmv|swf|wma|zip|rar|tbz2|gz|icsgz))$");
	
    /*         
     * Max number of outgoing links which are processed from a page          
     * 
     */         
	public final static int MAX_OUT_LINK = 100; 
	
	public final static int MAX_DOWNLOAD_SIZE = 1048576;
	/*
	 *  set the start web site url of the crawler
	*/
	public final static String CONTAIN_URL = ".ics.uci.edu";
	
	/*
	 *  set crawler results Storage Folder
	*/
	public final static String CRAWL_STORAGE_FOLDER = "data/crawl/root";
	
	/*
    * numberOfCrawlers shows the number of concurrent threads that should
    * be initiated for crawling.
    */
	public final static int NUMBER_OF_CRAWLERS = 15;
	
	/*
	 * depth of crawling, which is 0 for seed page and -1 for infinity
	 */
	public final static int MAX_DEPTH_OF_CRAWLING = 100;
	
	/*
	 * if the crawler should crawl https pages
	 */
	public final static boolean INCLUDE_HTTPS_PAGES = true;
	/*
	 * politenessDelay is the delay of crawler's frequency
	 */
	public final static int POLITENESS_DELAY_TIME = 300;
	
	/*
	 * numberOfPagesLimit shows number of pages can be crawled
	 * -1 means unlimited
	 */
	public final static int NUMBER_OF_PAGES_LIMIT = -1;
	
	/*
	 * thread sleep time
	 */
	public final static int THREAD_SLEEP_TIME = 10 * 1000;
	
	/*
	 * whether resume crawl is allowed
	 */
	public final static boolean IS_RESUME = false;
	
	/*
	 * set the connection time out time 
	 */
	public final static int CONNECTION_TIME_OUT = 30*1000;
	/*
	 * set the name of crawler seen by web server
	 */
	public final static String USER_AGENT_NAME = "UCI IR crawler 70008170 & 30965302";
	
	/*
	 * set the name of file which stores all pages
	 * page content format docid + " " + length + " " + content
	 */
	public final static String PAGE_FILE_NAME = "pages2/Page";

	public static final String DIRECTORY_FILE_NAME = "directory2";
	
	public static final String INDEX_FILE_NAME = "scores2/InvertedIndexes";
	
	public static final String SCORE_FILE_NAME = "scores2/Indexes";
	
	public static final double SIMILARITY = 0.8;

	public static final int HASHKEY = 100;

	/*
	 * word filter for tokenizer
	 */
	public static final Pattern WORD_FILTER = 
			Pattern.compile("\\W+");
	

	/*
	 * number of inverted index files
	 */

	public static final char INDEX_FILE_SIZE = 50;

	public static final String TERM_FILE_NAME = "TermIndexes";

	public static final int CORPUS_SIZE = 36514;
	
	
}
