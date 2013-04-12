package com.engine.crawler;

import com.engine.constant.*;

import edu.uci.ics.crawler4j.crawler.*;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.*;

public class CrawlerController {

	public CrawlerController() {

	}

	public void StartCrawl(String seedURL) {
		// crawler configuration
		CrawlConfig config = new CrawlConfig();
		// time it takes to crawl the entire domain
		Timer crawlTime = new Timer();

		setCrawlConfiguration(config);

		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);

		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);

		try {
			CrawlController controller = new CrawlController(config,
					pageFetcher, robotstxtServer);
			/*
			 * For each crawl, you need to add some seed urls. These are the
			 * first URLs that are fetched and then the crawler starts following
			 * links which are found in these pages
			 */
			controller.addSeed(seedURL);

			crawlTime.setStartTime(System.currentTimeMillis());
			/*
			 * Start the crawl. This is a blocking operation, meaning that your
			 * code will reach the line after this only when crawling is
			 * finished.
			 */
			controller.start(CrawlerDataCollector.class,
					Constant.NUMBER_OF_CRAWLERS);

			crawlTime.setEntTime(System.currentTimeMillis());
			crawlTime.calculateCrawlTime();

			controller.Shutdown();

			System.out.println("Time it took to crawl the entire domain: "
					+ crawlTime.getTotalCrawlTimeToString());

			while (!controller.isFinished()) {
				controller.waitUntilFinish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void setCrawlConfiguration(CrawlConfig config) {
		config.setCrawlStorageFolder(Constant.CRAWL_STORAGE_FOLDER);
		config.setPolitenessDelay(Constant.POLITENESS_DELAY_TIME);
		config.setMaxPagesToFetch(Constant.NUMBER_OF_PAGES_LIMIT);
		config.setMaxDepthOfCrawling(Constant.MAX_DEPTH_OF_CRAWLING);
		config.setMaxDownloadSize(Constant.MAX_DOWNLOAD_SIZE);
		config.setMaxOutgoingLinksToFollow(Constant.MAX_OUT_LINK);
		config.setConnectionTimeout(Constant.CONNECTION_TIME_OUT);
		config.setResumableCrawling(Constant.IS_RESUME);
		config.setUserAgentString(Constant.USER_AGENT_NAME);
	}

}
