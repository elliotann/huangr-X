 package com.easysoft.build.manager;



 import com.easysoft.build.model.BtDeployPackRule;
 import com.easysoft.build.model.BuildFile;
 import com.easysoft.build.model.RepositoryInfo;
 import com.easysoft.build.utils.AntTaskUtil;
 import com.easysoft.build.utils.Base64Util;
 import com.easysoft.build.utils.PatchUtil;
 import com.easysoft.build.vo.PatchFile;
 import com.easysoft.framework.spring.SpringContextHolder;

 import java.io.File;
 import java.io.FileFilter;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.List;
 import java.util.TreeSet;

 public class PatchFileService
 {
   public static File encodePatch(String branch, String patchName)
   {
     if (patchName.equalsIgnoreCase("latest.zip"))
       return null;
     RepositoryInfo repos = BuildReposManager.getByName(branch);
     File file = new File(repos.getDeployDir(), patchName);
     if (!file.exists())
       return null;
     File dest = new File(repos.getDeployDir(), patchName.substring(0, patchName.lastIndexOf('.')) + ".patch");
     if ((dest.exists()) && (dest.lastModified() > file.lastModified()))
       return dest;
     try
     {
       Base64Util.encodeFile(file.getAbsolutePath(), dest.getAbsolutePath());
     } catch (Exception e) {
       e.printStackTrace();
     }
     return dest;
   }
 
   public static PatchFile[] listPachInfo(String branch)
   {
	 BtDeployPackRuleService bserver = SpringContextHolder.getBean(BtDeployPackRuleService.class);
	 List<BtDeployPackRule> lb = bserver.getBtDeployPackRuleListByBranch(branch);
	 
     RepositoryInfo repos = BuildReposManager.getByName(branch);
     File file = repos.getDeployDir();
     File[] patchs = file.listFiles(new FileFilter() {
       public boolean accept(File pathname) {
         return pathname.getName().endsWith(".zip");
       }
     });
     TreeSet pInfos = new TreeSet();
     if (patchs != null) {       
       for (File f : patchs) {    	 
         if (f.isDirectory()) continue; if (f.getName().equals("latest.zip"))
           continue;
         boolean blean = false;
         PatchFile pf = PatchFile.getPatchFile(f, branch);
         for(BtDeployPackRule b:lb){
        	 if((b.getDeployPackName()+".zip").equals(pf.getName())){
        		 blean = true;
        		 break;
        	 }
         }
         if(blean){
        	 pf.setPassedTested("1"); 
         }else{
        	 pf.setPassedTested("0"); 
         }
         pInfos.add(pf);
       }
     }
     return (PatchFile[])(PatchFile[])pInfos.toArray(new PatchFile[pInfos.size()]);
   }
 
   public static File getRootOfBPByPatch(String branch, String patchName)
     throws ParseException
   {
     RepositoryInfo repos = BuildReposManager.getByName(branch);
     if (patchName.toLowerCase().endsWith(".zip"))
       patchName = patchName.substring(0, patchName.length() - ".zip".length());
     if(patchName.length()==17){
    	 patchName = patchName.substring(repos.getVersionNo().length());
         patchName = patchName.substring(0, patchName.length() - repos.getVersionSuffix().length());
     }else{
    	 String[] d = patchName.split("\\.");
    	 patchName = d[4].substring(0,8);    	 
     }     
 
     SimpleDateFormat df = new SimpleDateFormat(repos.getVersionPattern());
     String path = PatchUtil.getBackupDir(df.parse(patchName), repos.isWeekbug());
     File root = new File(repos.getDeployBackupDir(), path);
     return root;
   }
 
   public static BuildFile[] listBuildPackInfoOfPatch(String branch, String patchName)
     throws ParseException
   {
     File[] zips = getRootOfBPByPatch(branch, patchName).listFiles();
     if ((zips == null) || (zips.length == 0)) {
       return new BuildFile[0];
     }
 
     TreeSet bpInfos = new TreeSet();
     for (File zip : zips) {
       if (!zip.getName().endsWith(".zip")) continue;
       try {
         BuildFile info = BuildFileService.getBuildPackInfo(branch, zip);
         bpInfos.add(info);
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
 
     return (BuildFile[])(BuildFile[])bpInfos.toArray(new BuildFile[bpInfos.size()]);
   }
   
   public static File getDispatchDownLoadFile(String branch,String fileNames){
	   String[] fileArray = fileNames.split(",");
	   RepositoryInfo repos = BuildReposManager.getByName(branch);
	   File path = repos.getDisPatchDownLoadDir();
	   if(!path.exists()){
		   path.mkdir();
	   }
	   File root = new File(repos.getBranchRoot(), path.getName());
	   File tempfile = new File(root,"temp_"+System.currentTimeMillis());
	   if(!tempfile.exists()){
		   tempfile.mkdir();
	   }
	   File buildroot = repos.getBuildDir();
	   File fa ;
	   for(int i=0;i<fileArray.length;i++){
		   fa = new File(buildroot,fileArray[i]);
		   AntTaskUtil.unzip(fa, tempfile, null);
	   }
	   //批量下载构建包，当选很多构建包时，合成的构建包名过长会出错hush20131231
	   //String fn = fileNames.replaceAll(",", "-").replaceAll(".zip", "");
	   String fn = "batch_";
	   File ddfile = new File(root,fn + System.currentTimeMillis() + ".zip");
	   AntTaskUtil.zip(tempfile, ddfile, null);
	   AntTaskUtil.deleteDir(tempfile,null);	   
	   return ddfile;
   }
   
   
   
 }

