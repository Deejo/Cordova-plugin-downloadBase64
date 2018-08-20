package com.deejo.cordova.plugin.DownloadBase64;

// The native APIs
import android.os.Environment;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

// Cordova-required packages
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DownloadBase64 extends CordovaPlugin {

  String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
  
  @Override
  public boolean execute(String action, JSONArray args,
    final CallbackContext callbackContext) {
      // Verify that the user sent a 'show' action
      if (!action.equals("downloadFile")) {
        callbackContext.error("\"" + action + "\" is not a recognized action.");
        return false;
      }
	  
      String url;
	  String fileName;
	  
      try {
        JSONObject options = args.getJSONObject(0);
        url = options.getString("url");
        filePath = filePath + options.getString("folderName");
		fileName = options.getString("fileName");
      } catch (JSONException e) {
        callbackContext.error("Error encountered: " + e.getMessage());
        return false;
      }
	  
      downloadFile(url, filePath, fileName); 
 	//return returnValue;
  }
  
  public void downloadFile(final String url, final String filePath, final String fileName) {

        Thread networkThread = new Thread() {

            @Override
            public void run() {

                File outputFile = new File(filePath, fileName);

                try {
			
		    CallbackContext callbackContext;
			
                    URL u = new URL(url);
                    URLConnection conn = u.openConnection();
                    int contentLength = conn.getContentLength();

                    DataInputStream stream = new DataInputStream(u.openStream());

                    byte[] buffer = new byte[contentLength];
                    stream.readFully(buffer);
                    stream.close();

                    DataOutputStream fos = new DataOutputStream(new FileOutputStream(outputFile));
                    fos.write(buffer);
                    fos.flush();
                    fos.close();
					
		    // Send a positive result to the callbackContext
		    //PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
		    //callbackContext.sendPluginResult(pluginResult);
		    //return true;
			
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        };
        networkThread.start();
    }
  
}
