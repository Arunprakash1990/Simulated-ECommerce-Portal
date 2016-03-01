package mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

public class Request{
   public static void main( String args[] ){
   
   }
   public void getconn()
   {
	   try{   
			 // To connect to mongodb server
	         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	         // Now connect to your databases
	         DB db = mongoClient.getDB( "project" );
			 System.out.println("Connect to database successfully");
			 DBCollection collection = db.getCollection("testCollection");
			// create a simple db object where counter value is 0
			//DBObject temp = new BasicDBObject("name", "someName").append("counter", 0);
			// insert it into the collection
			//collection.insert(temp);
			// create an increment query
			DBObject modifier = new BasicDBObject("counter", 1);
			DBObject incQuery = new BasicDBObject("$inc", modifier);
			// create a search query
			DBObject searchQuery = new BasicDBObject("name", "someName");
			// increment a counter value atomically
			WriteResult upRes = collection.update(searchQuery, incQuery);

	         mongoClient.close();
	         
	      }catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }   
	   
   }
   }
