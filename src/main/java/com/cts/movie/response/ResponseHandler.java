package com.cts.movie.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	public static ResponseEntity<Object> generateResponse(String message , HttpStatus statusMsg, Object responseObj)
	{
		Map<String,Object> mapObj =new HashMap<String,Object>();
		
		mapObj.put("Message", message);
		mapObj.put("Status", statusMsg);
		mapObj.put("Payload", responseObj);
		return new ResponseEntity<Object>(mapObj , statusMsg);
	}
}
