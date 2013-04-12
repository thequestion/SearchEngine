package com.engine.searcher;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hit;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

import com.engine.constant.Constant;
import com.engine.file.IndexFile;
import com.engine.parser.IPage;
import com.engine.parser.PageURL;

public class SearchEngine implements ISearch {

	@Override
	public String getTile(String url) {
		if(url == null) return null;
		try {
			return TitleExtractor.getPageTitle(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<IPage> search(String query, int size) {
		if (query == null)
			return null;
		List<IPage> docs = new ArrayList<IPage>();
		try {
			Hits results = performSearch(query.toLowerCase());
			if (size > results.length())
				return null;
			for (int i = 0; i < size; i++) {
				Document doc = results.doc(i);
				String url = doc.get("url");
				try{
					int id = Integer.parseInt(doc.get("id"));
					String content = doc.get("content");
					PageURL page = new PageURL(id, url, content);
					docs.add(page);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return docs;
	}

	/** Creates a new instance of SearchEngine */
	public SearchEngine(){
		try {
			searcher = new IndexSearcher(Constant.INDEX_FILE_NAME);
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		parser = new QueryParser("content", new StandardAnalyzer());
	}

	private Hits performSearch(String queryString) throws IOException,
			ParseException {
		Query query = parser.parse(queryString);

		Hits hits = searcher.search(query);
		return hits;
	}

	private static List<String> constructQuery() {
		List<String> results = new ArrayList<String>();
		results.add("mondego");
		results.add("machine learning");
		results.add("software engineering");
		results.add("security");
		results.add("student affairs");
		results.add("graduate courses");
		results.add("Crista Lopes");
		results.add("REST");
		results.add("computer games");
		results.add("information retrieval");
		return results;
	}


	private static double getGoogle() {
		double score = 5;

		for (int i = 2, j = 4; i <= 5; i++, j--) {
			score += (double) j / (double) (Math.log10(i) / Math.log10(2));
		}

		return score;
	}

	public void performaceCalculator() {
		try {
			List<String> queries = constructQuery();
			for (String s : queries) {
				IndexFile handler = new IndexFile("google/" + s);
				List<String> urls = handler.readLinesFromFile();
				Hits results = performSearch(s);
				double score = 0;
				int start = 5;
				for (int i = 0; i < 5; i++) {
					Document doc = results.doc(i);
					String test = doc.get("url");
					int rel = 0;
					for (String t : urls) {
						if (test.equals(t)) {
							rel = start;
						}
					}
					start--;
					if (i + 1 == 1)
						score += rel;
					else if (rel != 0) {
						score += (double) rel
								/ (double) (Math.log10(i + 1) / Math.log10(2));
					}

				}
				DecimalFormat df = new DecimalFormat("#.####");

				System.out.println(s + " " + df.format(score / getGoogle()));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private IndexSearcher searcher;
	private QueryParser parser;



}
