package controllers;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;

import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.Service;
import views.html.index;

public class Application extends Controller {
	
	@Inject
	private Service service;
	
    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result json() {
        ObjectNode result = Json.newObject();
        result.put("message", "Hello World!");
        
        setHeaders();
        return ok(result);
    }

    public Result plaintext() {
    	setHeaders();
        return ok("Hello World!");
    }
    
	public Promise<Result> dblookup() {
		int val = new java.util.Random().nextInt(10000);
		setHeaders();
		return Promise.promise(() -> 
			service.dblookup(val)).map(i -> 
				ok(buildJsonResult(val, i)));
	}
	
	private void setHeaders() {
        response().setHeader("server",  "vertx");
        response().setHeader("date", DateTimeFormatter.RFC_1123_DATE_TIME.format(
				java.time.ZonedDateTime.now()));		
	}
	
	private ObjectNode buildJsonResult(int id, int randomNumber) {
		ObjectNode result = Json.newObject();
        result.put("id", id);
        result.put("randomNumber", randomNumber);   
        return result;
	}
}
