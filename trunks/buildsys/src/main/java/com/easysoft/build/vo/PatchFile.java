 package com.easysoft.build.vo;


 import com.easysoft.build.manager.BuildReposManager;
 import com.easysoft.build.model.RepositoryInfo;
 import com.easysoft.build.utils.PatchUtil;

 import java.io.File;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.Hashtable;
 import java.util.Map;

 public class PatchFile
   implements Comparable<PatchFile>
 {
   private static volatile Map<File, PatchFile> patchFiles = new Hashtable();
   private final File file;
   private final String branch;
   private final File backupRoot;
   private static SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   private String passedTested;
 

public static PatchFile getPatchFile(File file, String branch)
   {
     PatchFile patch = (PatchFile)patchFiles.get(file);
     if (patch == null) {
       synchronized (patchFiles) {
         patch = (PatchFile)patchFiles.get(file);
         if (patch == null) {
           patch = new PatchFile(file, branch);
           patchFiles.put(file, patch);
         }
       }
     }
     return patch;
   }
 
   private PatchFile(File file, String branch)
   {
     this.file = file;
     this.branch = branch;
     RepositoryInfo repos = BuildReposManager.getByName(branch);
     String patchName = file.getName();
     SimpleDateFormat df = null;
     if(patchName.length()==21){
    	 if (patchName.toLowerCase().endsWith(".zip"))
    	       patchName = patchName.substring(0, patchName.length() - ".zip".length());
    	     patchName = patchName.substring(7);
    	     patchName = patchName.substring(0, patchName.length() - 4);
    	     df = new SimpleDateFormat("yyMMdd");
     }else{
//    	 if (patchName.toLowerCase().endsWith(".zip"))
//  	       patchName = patchName.substring(0, patchName.length() - ".zip".length());
//  	     patchName = patchName.substring(repos.getBand().length()+1+repos.getSpNo().length()+1+repos.getVersionNo().length());
//  	     patchName = patchName.substring(0, patchName.length()-3 - repos.getVersionSuffix().length());
//  	     df = new SimpleDateFormat(repos.getVersionPattern());
    	 String[] d = patchName.split("\\.");
    	 patchName = d[4].substring(0, 8);
    	 df = new SimpleDateFormat(repos.getVersionPattern());
     }    
 
     
     String path = "";
     try {
       path = PatchUtil.getBackupDir(df.parse(patchName), repos.isWeekbug());
     }
     catch (ParseException e)
     {
      e.printStackTrace();
     }
     this.backupRoot = new File(repos.getDeployBackupDir(), path);
   }
 
   public File getFile() {
     return this.file;
   }
 
   public String getBranch() {
     return this.branch;
   }
 
   public String getName() {
     return this.file.getName();
   }
 
//   public int compareTo(PatchFile o) {
//     return (int)(o.file.length() - this.file.length());
//   }
   
   public int compareTo(PatchFile o) {
	   String name = this.file.getName();
	   String name2 = o.file.getName();
      return name2.compareTo(name);
   }
 
   public String getLastModify()
   {
     Date date = new Date(this.file.lastModified());
     return formater.format(date);
   }
 
   public long getSize()
   {
     return this.file.length();
   }
 
   public int getIncludePBSize()
   {
     File[] fs = this.backupRoot.listFiles();
     if (fs == null)
       return 0;
     return fs.length;
   }
   
   public String getPassedTested() {
	   return passedTested;
   }

   public void setPassedTested(String passedTested) {
	   this.passedTested = passedTested;
   }

 }

