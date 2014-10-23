package platu.main;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import platu.gui.FrontEnd;
import platu.project.Project;
import java.util.LinkedList;
import java.util.Properties;
import java.util.StringTokenizer;
import static java.lang.Runtime.*;

public class Main {

	// Static variables
	public final static int PROCESSORS = Runtime.getRuntime().availableProcessors();
   	public final static int THREADS = PROCESSORS;
   	public final static boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
    public static final SimpleDateFormat dateAndTime = new SimpleDateFormat("yyyy-MMM-dd hh.mm.ss a");
    public static String workingDirectory = System.getProperty("user.dir");
    
//    static{
//    	System.out.println(System.getProperty("user.dir"));
//      System.out.println("CORES: " + PROCESSORS);
//    	System.out.println("isWindows: " + isWindows);
//    }
    
    //TODO: use thread pool?
    public static ExecutorService exec = Executors.newFixedThreadPool(PROCESSORS);
    
    // Options
    public static long GRAPH_KEEP_ALIVE_TIME = 3000; //millisec
    public static long MAX_MEM = 0;

    public Main(String cmdFile) {
        try {
            BufferedReader br = null;

            if (cmdFile != null) {
                br = new BufferedReader(new FileReader(cmdFile));
            } else {
                br = new BufferedReader(new InputStreamReader(System.in));
            }

            long memUse;
            Interpretor in = new Interpretor();
            Project prj = new Project();
            
            while (true) {
                String commandline = null;
                commandline = br.readLine();

                if (commandline == null){
                	break;
                }
                else if(commandline.equalsIgnoreCase("reset")){
                    prj = new Project();
                    continue;
                }
                else if (in.interpretcommand(prj, commandline) == 1) {
                    memUse = getRuntime().totalMemory() - getRuntime().freeMemory();
                    MAX_MEM = MAX_MEM > memUse ? MAX_MEM : memUse;
                    break;
                }
            }

            br.close();

        } catch (java.io.FileNotFoundException ex) {
        	 System.err.println("error: command file \"" + new File(cmdFile).getAbsoluteFile() + "\" not found");
             System.exit(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Platu entry point.
     * @param args - Command line arguments.
     */
    public static void main(String[] args) {
    	if(args.length == 0)
    	{
    		FrontEnd.createGUI();
    	}
    	else{
	        try {
	        	boolean opt = false;
	        	boolean lpn = false;
	        	boolean cfg = false;
	            boolean par = false;
	            boolean outputOldFormat = false;
	            String outputFileName = "";
	            
	        	/*
	             * Create new project
	             */
	            Project prj = new Project();
	            
	            for (String arg : args) {
	            	if(cfg){
	            		setProperties(arg);
	                    opt = true;
	            		cfg = false;
	            	}
	            	else if(par){
	            		Options.setCompositionOrder(arg);
	            		par = false;
	            	}
	            	else if(outputOldFormat){
	            		outputFileName = arg;
	            		outputOldFormat = false;
	            	}
	            	else if (arg.equals("-cfg")) {
	                    cfg = true;
	                }
	                else if (arg.equals("-par")){
	                	par = true;
	                }
	                else if(arg.equals("-2of")){
	                	outputOldFormat = true;
	                }
	                else if(arg.endsWith(".lpn")){
	                	/*
	    	             * Read each supplied LPN file and add to the project
	    	             */
		        		File f = new File(arg);
		        		if(!f.exists()){
		        			System.err.println("Error: error reading " + arg);
		    				System.exit(1);
		        		}
		        		
		        		LinkedList<String> fileList = new LinkedList<String>();
		        		fileList.add(f.getAbsolutePath());
    		        		
		        		prj.setLabel(arg);
    		        	prj.readLpn(fileList);
	    	            
	    	            lpn = true;
	                }
	                else{
	                	System.err.println("Error: invalid argument " + arg);
	                	System.exit(1);
	                }
	            }
	            
	            if(!lpn){
	            	System.err.println("Error: no LPN file supplied");
	            	System.exit(1);
	            }
	            else if(outputFileName.length() > 0){
	            	prj.outputLpn(outputFileName);
	            }
	            
	            /*
	             * If no option file is supplied, look for the default option file in the working directory
	             */
	            if(!opt){
	            	File optFile = new File("mode.cfg");
	                if (optFile.exists()) {
	                	setProperties(optFile.getAbsolutePath());
	                }
	            }
	            
	            /*
	             * Start project search
	             */
	            prj.search();
	        } 
	        catch (Throwable e) {            
	        	e.printStackTrace();
				System.exit(1);
	        }
    	}
    }

    
    private static void setProperties(String fileName){
    	File optFile = new File(fileName);
        if (!optFile.exists()) {
            System.err.println("Warning: option file \"" + optFile.getAbsolutePath() + "\" not found - defaults will be used");
            return;
        }
        
    	Properties prop = new Properties();
        try {
			prop.load(new FileInputStream(optFile));
		} catch (FileNotFoundException e) {
			System.err.println("Warning: option file \"" + optFile.getAbsolutePath() + "\" not found - defaults will be used");
            return;
		} catch (IOException e) {
			System.err.println("Warning: option file \"" + optFile.getAbsolutePath() + "\" not read - defaults will be used");
            return;
		}
		
        Options.setOptions(prop);
    }

    public static String mergeColumns(String a, String b, int w1, int w2) {
        String ret = "";
        StringTokenizer tk1 = new StringTokenizer(a, "\n");
        StringTokenizer tk2 = new StringTokenizer(b, "\n");
        while (tk1.hasMoreTokens() && tk2.hasMoreTokens()) {
            ret += String.format("%-" + w1 + "s | %-" + w2 + "s |\n", tk1.nextToken(), tk2.nextToken());
        }
        while (tk2.hasMoreTokens()) {
            ret += String.format("%-" + w1 + "s | %-" + w2 + "s |\n", "", tk2.nextToken());
        }
        while (tk1.hasMoreTokens()) {
            ret += String.format("%-" + w1 + "s | %-" + w2 + "s |\n", tk1.nextToken(), "");
        }
        return ret;
    }

    public static String mergeColumns(String a, String b, String c, int w1, int w2, int w3) {
        String ret = "";
        StringTokenizer tk1 = new StringTokenizer(a, "\n");
        StringTokenizer tk2 = new StringTokenizer(b, "\n");
        StringTokenizer tk3 = new StringTokenizer(c, "\n");
        String s1 = null, s2 = null, s3 = null;
        while (tk1.hasMoreTokens() || tk2.hasMoreTokens() || tk3.hasMoreTokens()) {
            s1 = tk1.hasMoreTokens() ? tk1.nextToken() : "";
            s2 = tk2.hasMoreTokens() ? tk2.nextToken() : "";
            s3 = tk3.hasMoreTokens() ? tk3.nextToken() : "";
            ret += String.format("%-" + w1 + "s | %-" + w2 + "s | %-" + w3 + "s |\n",
                    s1,// (""+s1).replace("\t", "...").replace(" ", "~"),
                    s2,// (""+s2).replace("\t", "...").replace(" ", "~"),
                    s3// (""+s3).replace("\t", "...").replace(" ", "~")
                    );
        }
        return ret;
    }
}
