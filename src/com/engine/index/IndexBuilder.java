package com.engine.index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexDeletionPolicy;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.KeepOnlyLastCommitDeletionPolicy;
import org.apache.lucene.store.LockObtainFailedException;

import com.engine.constant.Constant;
import com.engine.file.IndexFile;
import com.engine.parser.Page;
import com.engine.parser.PageParser;

public class IndexBuilder {
	private IndexWriter getIndexWriter(boolean create) throws IOException {
		if (indexWriter == null) {
			indexWriter =new IndexWriter(Constant.INDEX_FILE_NAME,new StandardAnalyzer(),create,
					IndexWriter.MaxFieldLength.UNLIMITED);

		}
		return indexWriter;
	}

	private void closeIndexWriter() throws IOException {
		if (indexWriter != null) {
			indexWriter.close();
		}
	}

	public void create() throws CorruptIndexException,
			LockObtainFailedException, IOException {
		getIndexWriter(true);
		PageParser parser = new PageParser();
		parser.prepareReadPage();
		while (parser.hasNextPage()) {
			Page page = parser.getNextPage();
			if(page != null){
				Document doc = parsePageToDoc(page);
				if (doc != null) {
					indexWriter.addDocument(doc);
				}
				// trace
				System.out.println(page.getPageId());
			}
			
		}
		parser.finalizeReadPage();
		closeIndexWriter();
	}

	public Document parsePageToDoc(Page page) {
		if (page.getLength() > 0) {
			Document doc = new Document();
			Field pageID = new Field("id", "" + page.getPageId(),
					Field.Store.YES, Field.Index.NO);
			String url = page.getURL();
			
			Field pageURL = new Field("url", url, Field.Store.YES,
					Field.Index.NO);
			Field contentField = new Field("content", page.getContent(),
					Field.Store.YES, Field.Index.ANALYZED);
			doc.add(pageID);
			doc.add(pageURL);
			doc.add(contentField);
			List<String> boosters = getBoosters();
			if(boosters.contains(url))
				doc.setBoost(2F);
			
			return doc;
		}
		return null;
	}

	private static List<String> constructQuery(){
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
	
	private List<String> getBoosters(){
		List<String> queries = constructQuery();
		List<String> results = new ArrayList<String>();
		for(String s:queries){
			IndexFile handler = new IndexFile("google/"+s);
			results.addAll( handler.readLinesFromFile() );
		}
		return results;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IndexBuilder test = new IndexBuilder();
		try {
			test.create();
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private IndexWriter indexWriter;

}
