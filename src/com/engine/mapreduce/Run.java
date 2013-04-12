package com.engine.mapreduce;

public class Run {

	/**
	 * @param args
	 */
	
	public static void main(String[] args){
//		Mapper test = new Mapper();
//		test.constructTermIndexes();
//		//trace
//		System.out.println("Done mapping");
		Reducer test2 = new Reducer();
//		test2.sortPartitions();
		//trace 
		System.out.println("Done sorting");
		test2.mergeTermIndexes();
		//trace
		System.out.println("Done merging");
	}

}
