package dataProviders;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import managers.FileReaderManager;
import testDataTypes.Login;

public class JsonDataReader {

	private String logintestfilepath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath();
	private List<Login> loginList;
	
	public JsonDataReader(){
		loginList = getLoginData();
	}
	
	private List<Login> getLoginData(){
		logintestfilepath = System.getProperty("user.dir") + logintestfilepath + "Login.json";
		
		Gson gson = new Gson();
		BufferedReader bufferReader = null;
		try {
			bufferReader = new BufferedReader(new FileReader(logintestfilepath));
			//JsonReader reader = new JsonReader(new StringReader(logintestfilepath));
			//reader.setLenient(true);
			
			Login[] logins = gson.fromJson(bufferReader, Login[].class);
			return Arrays.asList(logins);
		}catch(FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : " + logintestfilepath);
		}finally {
			try { if(bufferReader != null) bufferReader.close();}
			catch (IOException ignore) {}
		}	
	}
	

	public final Login getLoginByUserName(String userName){
		for(Login log : loginList) {
			if(log.uname.equalsIgnoreCase(userName)) return log;
		}
		return null;
	}
}
