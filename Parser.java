import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * This class is thread safe.
 */
public class Parser {
	  private File file;
	  public synchronized void setFile(File f) {
	    file = f;
	  }
	  public synchronized File getFile() {
	    return file;
	  }
	  public String getContent() throws IOException {
	    FileInputStream i = null;
	    String output = null;
	    // Use StringBuffer instead of string, avoid flooding the string pool
	    StringBuffer sb = new StringBuffer();
	    try {		    
		    int data;
		    i = new FileInputStream(file);
		    while ((data = i.read()) > 0) {
		    	//output += (char) data;
		    	sb.append((char) data);
		    }
		    output = sb.toString();
	    } finally{
	      // Close the inputstream after use for it to be eligible for garbage collector
	    	i.close();
	    	i=null;
	    }
	    
	    return output;
	  }
	  public String getContentWithoutUnicode() throws IOException {
		  FileInputStream i = null;
		  // Use StringBuffer instead of string, avoid flooding the string pool
		  StringBuffer sb = new StringBuffer();
		  String output = null;
		  try{
	    	i = new FileInputStream(file);
	    	int data;
	    	while ((data = i.read()) > 0) {
	    		if (data < 0x80) {
	    			//output += (char) data;
	    			sb.append((char) data);		
	    		}
	    	}
	    	output = sb.toString();
		  } finally{
	    	// Close the inputstream after use for it to be eligible for garbage collector
	    	i.close();
	    	i=null;
		  }
		  return output;
	  }
	  
	  public void saveContent(String content) throws IOException {
	    FileOutputStream o = null;
	    try{
	    	o = new FileOutputStream(file);
		    for (int i = 0; i < content.length(); i += 1) {
		      o.write(content.charAt(i));
		    }
	    } finally {
	      // Close the outputstream after use for it to be eligible for garbage collector
	    	o.close();
	    	o=null;
	    }
	  }
}
