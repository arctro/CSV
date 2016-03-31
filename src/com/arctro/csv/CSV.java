package com.arctro.csv;

import java.util.ArrayList;
import java.util.HashMap;

import com.arctro.csv.exceptions.HeaderBodyMismatchException;

/**
 * A class for parsing and creating CSV files
 * @author Ben McLean
 * @version 1.0
 */
public class CSV {
	/**
	 * The column delimiter
	 */
	private String DELIMITER = ",";
	
	/**
	 * The new line delimiter
	 */
	private String LINE_DELIMITER = "\n";
	
	HashMap<Object, Integer> map;
	Object[] headers;
	ArrayList<Object[]> body;
	
	/**
	 * Create an empty CSV
	 */
	public CSV(){
		map = new HashMap<Object, Integer>();
		headers = new Object[]{};
		body = new ArrayList<Object[]>();
	}
	
	/**
	 * Define a CSV with a header
	 * @param headers The CSV header
	 */
	public CSV(Object[] headers){
		this.headers = headers;
		
		map = new HashMap<Object, Integer>();
		for(int i = 0; i < headers.length; i++){
			map.put(headers[i], i);
		}
		
		body = new ArrayList<Object[]>();
	}
	
	public CSV(String csv){
		this(csv, ",", "\n");
	}
	
	public CSV(String csv, String delimiter, String lineDelimiter){
		String[] rows = csv.split(lineDelimiter);
		headers = (Object[]) rows[0].split(delimiter);
		
		body = new ArrayList<Object[]>();
		for(int i = 1; i < rows.length; i++){
			body.add((Object[]) rows[i].split(delimiter));
		}
	}
	
	/**
	 * Change the header
	 * @param headers The header
	 * @throws HeaderBodyMismatchException The header and body column count is inconsistant
	 */
	public void setHeader(Object[] headers) throws HeaderBodyMismatchException{
		this.headers = headers;
		for(int i = 0; i < headers.length; i++){
			map.put(headers[i], i);
		}
		
		if(body.size() > 0 && body.get(0).length != headers.length){
			throw new HeaderBodyMismatchException(headers.length, body.get(0).length);
		}
	}
	
	/**
	 * Add a row to the body
	 * @param data The data to add
	 * @throws HeaderBodyMismatchException The header and body column count is inconsistant
	 */
	public void add(Object[] data) throws HeaderBodyMismatchException{
		if(headers.length != data.length){
			throw new HeaderBodyMismatchException(headers.length, data.length);
		}
		
		body.add(data);
	}
	
	/**
	 * Add a row to the body
	 * @param index The index of the row
	 * @param data The data to add
	 * @throws HeaderBodyMismatchException The header and body column count is inconsistant
	 */
	public void add(int index, Object[] data) throws HeaderBodyMismatchException{
		if(headers.length != data.length){
			throw new HeaderBodyMismatchException(headers.length, data.length);
		}
		
		body.add(index, data);
	}
	
	/**
	 * Returns a row of the body
	 * @param row The row to return
	 * @return A row of the body
	 */
	public Object[] get(int row){
		return body.get(row);
	}
	
	/**
	 * Returns a column of a row of the body
	 * @param row The row to select
	 * @param index The column to select
	 * @return A column of a row of the body
	 */
	public Object get(int row, int index){
		return body.get(row)[index];
	}
	
	/**
	 * Returns a column of a row of the body
	 * @param row The row to select
	 * @param index The column to select
	 * @return A column of a row of the body
	 */
	public Object get(int row, Object index){
		return body.get(row)[map.get(index)];
	}
	
	/**
	 * Convert the CSV to a string
	 */
	public String toString(){
		StringBuilder output = new StringBuilder();
		
		//Append header
		int hl = headers.length;
		for(int i = 0; i < hl; i++){
			output.append(headers[i]);
			output.append(DELIMITER);
		}
		output.setLength(output.length() - 1);
		output.append(LINE_DELIMITER);
		
		//Append body
		int bs = body.size();
		for(int i = 0; i < bs; i++){
			for(int j = 0; j < hl; j++){
				output.append(body.get(i)
						[j]);
				output.append(DELIMITER);
			}
			output.setLength(output.length() - 1);
			output.append(LINE_DELIMITER);
		}
		
		return output.toString();
	}
	
	/**
	 * Set the delimiter between columns
	 * @param delimiter The delimiter between columns
	 */
	public void setDelimiter(String delimiter){
		this.DELIMITER = delimiter;
	}
	
	/**
	 * Set the delimiter between rows
	 * @param delimiter The delimiter between rows
	 */
	public void setLineDelimiter(String delimiter){
		this.LINE_DELIMITER = delimiter;
	}
}