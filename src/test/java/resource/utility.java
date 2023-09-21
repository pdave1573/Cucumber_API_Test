package resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class utility {

	static RequestSpecification req;
	static ResponseSpecification res;
	String logFileLocation;
	String key;
	String baseURI;
	static FileInputStream dataFile;
	File file;
	
	protected RequestSpecification request;
	protected Response response;
	static HashMap<String,String> placeIDMap = new HashMap<String,String>();

	public RequestSpecification requestSpecification() {
		if(req==null) {
			PrintStream log = null;
			globalProperties();
			try {
				log = new PrintStream(logFileLocation);
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				file = new File(logFileLocation);
			}
			req = new RequestSpecBuilder()
					.setBaseUri(baseURI)
					.setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.addQueryParam("key", key)
					.build();

			return req;
		}
		else
			return req;
	}

	public ResponseSpecification responseSpecification() {
		if(res==null) {
			res = new ResponseSpecBuilder()
					.expectContentType(ContentType.JSON)
					.expectStatusCode(200)
					.build();

			return res;
		}
		else
			return res;
	}

	public void globalProperties() {
		Properties prop = new Properties();
		FileInputStream propFile;
		try {
			propFile = new FileInputStream("src/test/java/resource/global.properties");
			prop.load(propFile);
			key = prop.getProperty("key");
			baseURI = prop.getProperty("BaseUri");
			logFileLocation = prop.getProperty("logFileLocation");
			dataFile = new FileInputStream(prop.getProperty("dataFileLocation"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setDataFile() {
		Properties prop = new Properties();
		FileInputStream propFile;
		try {
			propFile = new FileInputStream("src/test/java/resource/global.properties");
			prop.load(propFile);
			dataFile = new FileInputStream(prop.getProperty("dataFileLocation"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void callAPI(String api, String httpMethod){
		apiEndPointResources resource;

		if(httpMethod.equalsIgnoreCase("post")) {
			resource = apiEndPointResources.valueOf(api);	
			response = request.when().post(resource.getResource())
					.then().spec(responseSpecification()).extract().response();

		}
		else if(httpMethod.equalsIgnoreCase("get")) {
			resource = apiEndPointResources.valueOf(api);
			response = request.when().get(resource.getResource())
					.then().spec(responseSpecification()).extract().response();
		}
		else if(httpMethod.equalsIgnoreCase("put")) {
			resource = apiEndPointResources.valueOf(api);
			response = request.when().put(resource.getResource())
					.then().spec(responseSpecification()).extract().response();
		}
		else if(httpMethod.equalsIgnoreCase("delete")) {
			resource = apiEndPointResources.valueOf(api);
			response = request.when().delete(resource.getResource())
					.then().spec(responseSpecification()).extract().response();
		}
		else
			Assert.fail("Invalid API: " + api);
	}

	public void valueCheck(Response response, String key, String value) {
		JsonPath js = new JsonPath(response.asString());
		Assert.assertEquals(js.get(key), value);
	}

	public String getValue(Response response, String key) {
		JsonPath js = new JsonPath(response.asString());
		return js.get(key).toString();
	}

	public void printResponse(Response response) {
		System.out.println(response.asPrettyString());
	}

	public void checkStatusCode(int responseCode) {
		Assert.assertEquals(responseCode, response.getStatusCode());
	}

	public void mapPlaceIDToName(String name) {
		String placeID = getValue(response,"place_id");
		placeIDMap.put(name, placeID);
	}

	public String getMapValue(String name) {
		return placeIDMap.get(name);
	}

	public static ArrayList<Object> readExcel() throws IOException {
		ArrayList<Object> value = new ArrayList<Object>();
		Workbook wb=null;
		//constructs an XSSFWorkbook object, by buffering the whole stream into the memory  
		setDataFile();
		wb=new XSSFWorkbook(dataFile);  
		if(wb==null)
			setDataFile();

		
		org.apache.poi.ss.usermodel.Sheet sheet= wb.getSheetAt(0);

		//Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			//For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case NUMERIC:
					value.add(cell.getNumericCellValue());
					break;
				case STRING:
					value.add(cell.getStringCellValue());
					break;
				}
			}
		}
		wb.close();
		return value;
	}

	public static String ReadCellData(String sheetName, int vRow, int vColumn)  
	{  
		String value=null;          //variable for storing the cell value  
		Workbook wb=null;           //initialize Workbook null  
		try  
		{  
			//constructs an XSSFWorkbook object, by buffering the whole stream into the memory  
			setDataFile();
			wb=new XSSFWorkbook(dataFile);  
			if(wb==null)
				setDataFile(); 
		}  
		catch(FileNotFoundException e)  
		{  
			e.printStackTrace();  
		}  
		catch(IOException e1)  
		{  
			e1.printStackTrace();  
		}  
		org.apache.poi.ss.usermodel.Sheet sheet= wb.getSheet(sheetName);   //getting the XSSFSheet object at given index  
		Row row= sheet.getRow(vRow); //returns the logical row  
		Cell cell=row.getCell(vColumn); //getting the cell representing the given column  
		
		value=cell.getStringCellValue();    //getting cell value  
		
		return value;               //returns the cell value  
	}  
	
	public static double ReadCellDoubleData(String sheetName, int vRow, int vColumn)  
	{  
		double value;          //variable for storing the cell value  
		Workbook wb=null;           //initialize Workbook null  
		try  
		{  
			//constructs an XSSFWorkbook object, by buffering the whole stream into the memory  
			setDataFile();
			wb=new XSSFWorkbook(dataFile);  
			if(wb==null)
				setDataFile();
		}  
		catch(FileNotFoundException e)  
		{  
			e.printStackTrace();  
		}  
		catch(IOException e1)  
		{  
			e1.printStackTrace();  
		}  
		org.apache.poi.ss.usermodel.Sheet sheet= wb.getSheet(sheetName);   //getting the XSSFSheet object at given index  
		Row row= sheet.getRow(vRow); //returns the logical row  
		Cell cell=row.getCell(vColumn); //getting the cell representing the given column  
		
		value= cell.getNumericCellValue();    //getting cell value  
		
		return value;               //returns the cell value  
	}  
	
	public static ArrayList<Object> ReadRowData(String sheetName, int vRow)  
	{    
		Workbook wb=null;           //initialize Workbook null  
		try  
		{  
			//constructs an XSSFWorkbook object, by buffering the whole stream into the memory  
			setDataFile();
			wb=new XSSFWorkbook(dataFile);  
			if(wb==null) {
				setDataFile();
			}
		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
		org.apache.poi.ss.usermodel.Sheet sheet= wb.getSheet(sheetName);   //getting the XSSFSheet object at given index  
		Row row= sheet.getRow(vRow); //returns the logical row  
		Iterator<Cell> cellIterator=row.cellIterator(); //getting the cell representing the given column  
		ArrayList<Object> value = new ArrayList<Object>();
		
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
		
			switch (cell.getCellType()) {
			case NUMERIC:
				value.add(cell.getNumericCellValue());
				break;
			case STRING:
				value.add(cell.getStringCellValue());
				break;
			}
		}
	
		if(value.isEmpty())
			throw new NullPointerException("Row doesn't exists!");
		else
			return value;               //returns the cell value  
	}  

}
