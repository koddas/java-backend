package se.idioti.example.sqlite;

import static spark.Spark.get;
import static spark.Spark.port;

import java.util.List;

/**
 * This demonstrates how to expose the storage through a REST API using Spark.
 * 
 * @author "Johan Holmberg, Malmö university"
 * @since 1.0
 */
public class APIRunner {

	public static void main(String[] args) throws Exception {
		port(8080);
		
		Storage storage = new Storage();
		storage.setup();
		
		// A demonstration of how to use code within an endpoint
		get("/unicorns", (req, res) -> {
			List<Unicorn> unicorns = storage.fetchUnicorns();
			String output = "";
			
			for (Unicorn unicorn : unicorns) {
				output += unicorn.name + "\n";
			}
			
			return output;
		});
		
		// A demonstration of how to use code within an endpoint
		get("/unicorn/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Unicorn unicorn = storage.fetchUnicorn(id);
			
			return unicorn.name;
		});
	}

}
