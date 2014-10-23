package platu.main;

import java.io.File;
import platu.project.Project;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import platu.lpn.LPN;
import platu.stategraph.StateGraph;

public class Interpretor {

    public static boolean OLD_LPN = false;
    private boolean readFlag = false;
    final int MAXHISTORY = 10;
    boolean FLATMODEL = false;
    String[] commandHistory = new String[MAXHISTORY];
    static final LinkedHashMap<enCMD, String> mapCMD = (new LinkedHashMap<enCMD, String>(20));
    static final LinkedHashMap<enCMD, String> mapDesc = (new LinkedHashMap<enCMD, String>(20));

    enum enCMD {
    	skip, set, last, liststg, sglist, readcsp,
        setinterface, savesg, savelpn, join, removedummypn,
        rdpn, findsg, simulate, sim, getsg, addenv,
        flattenpns, mergebgpnall, getsgmaximal, getsgmaxenv, getsgflat,
        mergesg, sgabst, abstractsg, sgaf, autofailure,
        sgreduce, reduce, deletesg, help, quit, q, interactive,
        print, chkpie, sghide, abstStateTran, hidevar, readrsg,
        readlpn, chkpi, del, draw
    };
    
    static final String[] CMD = {
    	"skip", "set", "last", "liststg", "sglist", "readcsp",
        "setinterface", "savesg", "savelpn", "join", "removedummypn",
        "rdpn", "findsg", "simulate", "sim", "getsg", "addenv",
        "flattenpns", "mergebgpnall", "getsgmaximal", "getsgmaxenv", "getsgflat",
        "mergesg", "sgabst", "abstractsg", "sgaf", "autofailure",
        "sgreduce", "reduce", "deletesg", "help", "quit", "q", "interactive",
        "print", "chkpie", "sghide", "abstStateTran", "hidevar", "readrsg",
        "readlpn", "chkpi", "del", "draw"
    };
    
    static final String[] CMDDesc = {
        "  [skip]", "  [set]", "re-enter last command", "  [liststg]", "  [sglist]", "  [readcsp]",
        "  [setinterface]", "+ [savesg]", "+ [savelpn]", "+ [join]", "  [removedummypn]",
        "  [rdpn]", "+ [findsg]", "+ [simulate]", "+ [sim]", "+ [getsg]", "  [addenv]",
        "  [flattenpns]", "+ [mergebgpnall]", "  [getsgmaximal]", "  [getsgmaxenv]", "  [getsgflat]",
        "  [mergesg]", "  [sgabst]", "  [abstractsg]", "  [sgaf]", "  [autofailure]",
        "  [sgreduce]", "  [reduce]", "  [deletesg]", "+  [help]", "+  [quit]", "+ [q]", "+ [interactive]",
        "+ [print]", "  [chkpie]", "  [sghide]", "  [abstStateTran]", "  [hidevar]", "  [readrsg]",
        "+ [readlpn]", "  [chkpi]", "  [del]", "  [draw]"
    };

    static {
        int x = 0;
        for (enCMD en : enCMD.values()) {
            mapCMD.put(en, CMD[x]);
            mapDesc.put(en, CMDDesc[x]);
            x++;
        }
    }

    public int interpretcommand(Project prj, final String commandLine) {
        if (prj == null) {
            new Exception("Main: interpretcommand: prj is NULL").printStackTrace();
        }
        
        // list of command arguments
        LinkedList<String> arguments = new LinkedList<String>();
        
        // first command argument
        String argument1 = "";

        // parse commandLine and place command and arguments into list
        String buffer = commandLine;
        StringTokenizer tk = new StringTokenizer(buffer, " ");
        while (tk.hasMoreTokens()) {
            arguments.add(tk.nextToken());
        }

        // make sure there is a command to execute
        if (arguments.size() == 0) {
            return 0;
        }

        // remove command from argument list
        String command = arguments.removeFirst();

        int argumentCount = arguments.size();
        if (arguments.size() > 0) {
            argument1 = arguments.peekFirst();
        }

        // mode changes
        if (command.charAt(0) == '/' && command.charAt(1) == '/') {
            return 0;
        } else if (command.compareToIgnoreCase(mapCMD.get(enCMD.skip)) == 0) {
            return 0;
        } else if (command.compareToIgnoreCase(mapCMD.get(enCMD.del)) == 0) {
            for (StateGraph sg : prj.getDesignUnitSet()) {
            	LPN lpn = sg.getLpn();
            	
                if (lpn.getLabel().compareTo(argument1) == 0) {
                    prj.getDesignUnitSet().remove(lpn);
                }
            }
        } else if (command.compareToIgnoreCase(mapCMD.get(enCMD.last)) == 0) {
            int x = 1;
            for (; x < MAXHISTORY; x++) {
                System.out.printf("\t%d\t%s\n", x, commandHistory[x]);
            }

            System.out.printf("enter choice: ");
            try {
                x = System.in.read() - '0';
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

            int ret = interpretcommand(prj, commandHistory[x]);//!!
            return (ret);
        } else if (command.compareToIgnoreCase(mapCMD.get(enCMD.rdpn)) == 0
                || command.compareToIgnoreCase(mapCMD.get(enCMD.removedummypn)) == 0) {
            //if( argument1 != "" )
            //prj.removeDummyPN( argument1 );
        } else if (command.compareToIgnoreCase(mapCMD.get(enCMD.findsg)) == 0) {
            prj.search();
        } else if (command.compareToIgnoreCase(mapCMD.get(enCMD.draw)) == 0){
        	if(argumentCount > 0){
        		for(StateGraph sg : prj.getDesignUnitSet()){
        			LPN lpn = sg.getLpn();
        			
        			if(argument1.equals(lpn.getLabel())){
        				System.out.print("drawing " + lpn.getLabel() + "...");
        				boolean rc = sg.draw();
        				
        				if(rc)
        					System.out.println("Done");
        				else
        					System.out.println("Failed");
        			}
        		}
        	}
        	else{
        		for(StateGraph sg : prj.getDesignUnitSet()){
        			LPN lpn = sg.getLpn();
        			System.out.print("drawing " + lpn.getLabel() + "...");
        			boolean rc = sg.draw();
        			
        			if(rc)
        				System.out.println("Done");
        			else
        				System.out.println("Failed");
        		}
        	}
        } else if (command.compareToIgnoreCase(mapCMD.get(enCMD.help)) == 0) {
            System.out.printf("%15s   |   %s\n"
                    + "----------------------------------------------------\n", "Command", "Description");
            for (enCMD cmd : enCMD.values()) {//!!
                System.out.printf("%15s   |   %s\n", mapCMD.get(cmd), mapDesc.get(cmd));
                //prln( CMD[cmd] + "  |  " + CMDDESC[cmd] );
            }
        } else if (command.compareToIgnoreCase(mapCMD.get(enCMD.q)) == 0
                || command.compareToIgnoreCase(mapCMD.get(enCMD.quit)) == 0) {
            return 1;
        } else if (command.compareToIgnoreCase(mapCMD.get(enCMD.readlpn)) == 0) {
        	if(!this.readFlag){
	        	this.readFlag = true;
	        	
	        	LinkedList<String> fileList = new LinkedList<String>();
	        	for(String arg : arguments){
	        		File f = new File(arg);
	        		fileList.add(f.getAbsolutePath());
	        	}
	        	
	        	prj.readLpn(fileList);
	        }
	        else{
	        	System.err.println("error: only one call to the readLpn command is allowed");
	        	System.exit(1);
	        }
        }
        else{
        	System.out.println("Unrecognized command: " + command);
        }

        return 0;
    }
}
