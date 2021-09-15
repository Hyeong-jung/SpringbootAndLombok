package com.springboot.lombok.controller;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.lombok.util.ConvertJsonToMap;
import com.springboot.lombok.util.ConvertJsonToMap2;

@RestController
@RequestMapping("/teststudy")
public class TestStudyController {

	
	@RequestMapping("/rtnstr") 
	public String valueTest(){ 
		String value = "테스트 String"; 
		return value; 
	}
	
	
    @PostMapping(value = "/posthashmap")
    public ResponseEntity<Map<String, String>> posthashmap(@RequestBody HashMap<String, String> paramMap) {

    	Map<String, String> resultMap = new HashMap<String, String>();

    	for (Map.Entry<String, String> entry : paramMap.entrySet()) {
    		System.out.println(entry.getKey() + " : " + entry.getValue());
    		
    		resultMap.put(entry.getKey(), entry.getValue());
    	}

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    
    
    
    @PostMapping(value = "/postlisthashmap")
    public ResponseEntity<List<Map<String, String>>> postlisthashmap(@RequestBody List<HashMap<String, String>> paramListMap) {

    	Map<String, String> resultMap = new HashMap<String, String>();
    	List<Map<String, String>> resultListMap = new ArrayList<>();
    	
		for (HashMap<String, String> map : paramListMap) {
			
			resultMap.clear();
			
	    	for (Map.Entry<String, String> entry : map.entrySet()) {
	    		System.out.println(entry.getKey() + " : " + entry.getValue());
	    		
	    		resultMap.put(entry.getKey(), entry.getValue());
	    	}
	    	
	    	resultListMap.add(resultMap);
	    	
		}
    	

        return new ResponseEntity<>(resultListMap, HttpStatus.OK);
    }
    
    
    @PostMapping(value = "/postpathvariable/{param1}/{param2}/{param3}")
    public ResponseEntity<Map<String, String>> postpathvariable(@PathVariable("param1") String param1, @PathVariable("param2") String param2, @PathVariable("param3") String param3) {

    	Map<String, String> resultMap = new HashMap<String, String>();
    	
    	resultMap.put("param1", param1);
    	resultMap.put("param2", param2);
    	resultMap.put("param3", param3);
    	


        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    
    @RequestMapping(value="/getservletrequest", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> getservletrequest(HttpServletRequest req) {

    	Map<String, String> resultMap = new HashMap<String, String>();
    	
    	String param1 = req.getParameter("param1");
    	String param2 = req.getParameter("param2");
    	String param3 = req.getParameter("param3");
    	
    	resultMap.put("param1", param1);
    	resultMap.put("param2", param2);
    	resultMap.put("param3", param3);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    
    @RequestMapping(value="/postservletrequest", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> postservletrequest(HttpServletRequest req) {

    	Map<String, String> resultMap = new HashMap<String, String>();
    	
    	String param1 = req.getParameter("param1");
    	String param2 = req.getParameter("param2");
    	String param3 = req.getParameter("param3");
    	
    	resultMap.put("param1", param1);
    	resultMap.put("param2", param2);
    	resultMap.put("param3", param3);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @RequestMapping(value="/getrequestparam", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> getrequestparam(@RequestParam(value="param1") final String param1, @RequestParam(value="param2") final String param2, @RequestParam(value="param3", required=false, defaultValue= "") final String param3) {

    	Map<String, String> resultMap = new HashMap<String, String>();

    	resultMap.put("param1", param1);
    	resultMap.put("param2", param2);
    	resultMap.put("param3", param3);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    
    
    @RequestMapping(value="/getrequestparamhashmap", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> getrequestparamhashmap(@RequestParam HashMap<String, String> paramMap) {

    	Map<String, String> resultMap = new HashMap<String, String>();

    	for (Map.Entry<String, String> entry : paramMap.entrySet()) {
    		System.out.println(entry.getKey() + " : " + entry.getValue());
    		
    		resultMap.put(entry.getKey(), entry.getValue());
    	}


        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    
    
	@RequestMapping("/jsontomap") 
	public ResponseEntity<Map<String, String>> jsontomap(){
		
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, String> resultMap = new HashMap<String, String>();
		
		String json = "{\"key1\":\"val1\", \"key2\":\"val2\", \"key3\":\"val3\"}";
		
		try {
			//Map<String, String> map = mapper.readValue(json, Map.class);
			resultMap = mapper.readValue(json, Map.class);
		} catch (IOException e) { 
			e.printStackTrace(); 
		}


		return new ResponseEntity<>(resultMap, HttpStatus.OK); 
	}
	
	@RequestMapping("/maptojson") 
	public String maptojson(){


		String value = "";

		ObjectMapper mapper = new ObjectMapper();

		Map<String, String> map = new HashMap<>(); 
		map.put("key1", "val1"); 
		map.put("key2", "val2");
		map.put("key3", "val3");
		
		try {
			
			String json1 = mapper.writeValueAsString(map);
			String json2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
			
			value = json1 + json2;

		} catch (JsonProcessingException e) {
			 e.printStackTrace();
		}
 
		return value; 
	}
	
	
	@RequestMapping("/convertjsontomap") 
	public ResponseEntity<Map<String, Object>> convertjsontomap() throws JsonParseException, JsonMappingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//String json = "{";
		//json += "\"name\" : \"abc\" ,";
		//json += "\"emailid\" : [\"abc@gmail.com\",\"def@gmail.com\",\"ghi@gmail.com\"]";
		//json += "}";
		
		//String json = "{\"key1\":\"val1\", \"key2\":\"val2\", \"key3\":\"val3\"}";
		String json = "{\"key1\":\"val1\", \"key2\":\"val2\", \"key3\":[\"abc@gmail.com\",\"def@gmail.com\",\"ghi@gmail.com\"]}";


		TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};


		resultMap = mapper.readValue(json, typeRef);

		
		return new ResponseEntity<>(resultMap, HttpStatus.OK); 
	}
	
	@RequestMapping("/useconvertjsontomap") 
	public ResponseEntity<Map<String, Object>> useconvertjsontomap() throws JsonParseException, JsonMappingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();


		//String json = "{\"key1\":\"val1\", \"key2\":\"val2\", \"key3\":\"val3\"}";
		String json = "{\"key1\":\"val1\", \"key2\":\"val2\", \"key3\":[\"abc@gmail.com\",\"def@gmail.com\",\"ghi@gmail.com\"]}";


		JsonObject jsonObject = Json.createReader(new StringReader(json)).readObject();
		
		ConvertJsonToMap convertjsontomap = new ConvertJsonToMap();

		
		resultMap = convertjsontomap.jsonToMap(jsonObject);

		return new ResponseEntity<>(resultMap, HttpStatus.OK); 
	}	
	
	
	@RequestMapping("/useconvertjsontomap2") 
	public ResponseEntity<Map<String, Object>> useconvertjsontomap2() throws JsonParseException, JsonMappingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
		//String json = "{\"key1\":\"val1\", \"key2\":\"val2\", \"key3\":\"val3\"}";
		String json = "{\"key1\":\"val1\", \"key2\":\"val2\", \"key3\":[\"abc@gmail.com\",\"def@gmail.com\",\"ghi@gmail.com\"]}";

		ConvertJsonToMap2 convertjsontomap = new ConvertJsonToMap2();
		JSONObject jsonObject = new JSONObject(json);
		
		resultMap = convertjsontomap.jsonToMap(jsonObject);

		
		return new ResponseEntity<>(resultMap, HttpStatus.OK); 
	}
	
	
	@GetMapping(value="/apicall")
    public ResponseEntity<Map<String, Object>> apicall() {

		Map<String, Object> resultMap = new HashMap<String, Object>();

    	RestTemplate rest = new RestTemplate();
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Content-Type", "application/json");
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("{\n");
    	sb.append("  \"author\": \"author\",\n");
    	sb.append("  \"genre\": \"genre\",\n");
    	sb.append("  \"publisher\": \"publisher\",\n");
    	sb.append("  \"title\": \"title\",\n");
    	sb.append("  \"quantity\": 100\n");
    	sb.append("}");
    	String body = sb.toString();
    	
    	HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
    	ResponseEntity<String> responseEntity = rest.exchange("http://localhost:8000/teststudy/posthashmap", HttpMethod.POST, requestEntity, String.class);
    	HttpStatus httpStatus = responseEntity.getStatusCode();
    	int status = httpStatus.value();
    	String response = responseEntity.getBody();
    	
		ConvertJsonToMap2 convertjsontomap = new ConvertJsonToMap2();
		JSONObject jsonObject = new JSONObject(response);
		
		//resultMap = convertjsontomap.jsonToMap(jsonObject);
		
		resultMap.put("status", status);
		resultMap.put("result", convertjsontomap.jsonToMap(jsonObject));


        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
	
	@GetMapping(value="/datetimesample")
    public ResponseEntity<Map<String, Object>> datetimesample() {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		LocalDateTime now = LocalDateTime.now();
		
		int year = now.getYear();
		Month month = now.getMonth();
		int day = now.getDayOfMonth();
		int hour = now.getHour();
		int minute = now.getMinute();

		LocalDateTime ldt = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), now.getHour(), 0, 0);
		
		
		LocalDate ld = LocalDate.parse("2021-09-30 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		resultMap.put("now", now);
		resultMap.put("month", month);
		resultMap.put("day", day);
		resultMap.put("hour", hour);
		resultMap.put("minute", minute);
		resultMap.put("localDateTime", ldt);
		resultMap.put("localDate", ld);
		
		
        return new ResponseEntity<>(resultMap, HttpStatus.OK);

	}
	
}
