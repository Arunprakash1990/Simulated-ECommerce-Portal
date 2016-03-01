package cust;

import javax.script.*;

public class getlatlng{
public static void main(String[] args) throws Exception {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("JavaScript");

    // JavaScript code in a String
    String script1 = (String)" function geo1(addr1){var geocoder = new google.maps.Geocoder(); var address = addr1;geocoder.geocode( { 'address': address}, function(results, status) {if (status == google.maps.GeocoderStatus.OK) {var latitude = results[0].geometry.location.lat();var longitude = results[0].geometry.location.lng(); return latitude,longitude;} }); }";
   
    // evaluate script
    engine.eval(script1);


    Invocable inv = (Invocable) engine;

    inv.invokeFunction("hello", "Scripting!!" );  //This one works.      
 }
}
