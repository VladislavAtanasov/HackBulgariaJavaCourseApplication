import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.*;

public class DepedenciesResolving {

	public static String readFile(String filename){
	    String result = "";
	    BufferedReader br = null;
	    try {
	        br = new BufferedReader(new FileReader(filename));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            line = br.readLine();
	        }
	        result = sb.toString();
	    } catch(Exception e) {
	    	System.out.println("The file has not been found");
	        e.printStackTrace();
	    } finally{
	    	if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	    }
	    return result;
	}
	
	public static Map<String, JSONArray> getMapPackages(JSONObject object){
		Map<String, JSONArray> map = new HashMap<>(object.length());
		
		Iterator keys = object.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			JSONArray jarr;
			try {
				jarr = new JSONArray(object.getJSONArray(key).toString());
				map.put(key, jarr);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}
	
	public static Map<String, JSONArray> getMapDependencies(JSONObject object){
		Map<String, JSONArray> map = new HashMap<>(object.length());
		JSONArray jarr;
		try {
			jarr = new JSONArray(object.getJSONArray("dependencies").toString());
			map.put("dependencies", jarr);
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		return map;
	}
	
	public static void installPackages(Map<String, JSONArray> packages, Map<String, JSONArray> projects){
		JSONArray toBeInstalled = projects.get("dependencies");
		for (int i = 0; i < toBeInstalled.length(); i++) {
			try {
				String project = toBeInstalled.getString(i);
				DFS(packages, project);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		System.out.println("All done.");
	}
	
	private static void DFS(Map<String, JSONArray> listPacks, String projectName){
		JSONArray dependecies = listPacks.get(projectName);
		System.out.println("Installing " + projectName + "...");
		if (dependecies.length() > 0) {
			System.out.println("In order to install " + projectName + " you need to install " + dependecies.toString());
		}
		for (int i = 0; i < dependecies.length(); i++) {
			try {
				DFS(listPacks, dependecies.getString(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, JSONException {
		String data = readFile("D:\\Hack Bulgaria\\Algorithms\\JavaWindows\\HBJavaCourseApplication\\src\\all_packages.json");
		String data2 = readFile("D:\\Hack Bulgaria\\Algorithms\\JavaWindows\\HBJavaCourseApplication\\src\\dependencies.json");
		JSONObject obj = new JSONObject(data);
		JSONObject obj2 = new JSONObject(data2);
		Map<String, JSONArray> packages = getMapPackages(obj);
		Map<String, JSONArray> dependencies = getMapDependencies(obj2);
		installPackages(packages, dependencies);
	}

}
