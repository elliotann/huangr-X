package com.easysoft.build.controller;

import com.easysoft.build.manager.*;
import com.easysoft.build.model.BtBuildPackDetail;
import com.easysoft.build.model.BtDeployPackRule;
import com.easysoft.build.model.RepositoryInfo;
import com.easysoft.build.utils.LogFileUtil;
import com.easysoft.build.vo.DownloadFileType;
import com.easysoft.core.common.controller.BaseController;
import com.easysoft.framework.spring.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * @author : andy.huang
 * @since :
 */
@Controller
@RequestMapping({"/core/admin/buildLogQuery"})
public class BuildLogQueryAction extends BaseController {
    @Autowired
    private BuildConfigInfoService buildConfigInfoService;
    @Autowired
    private BtBuildPackDetailService btBuildPackDetailService;
    @Autowired
    private BtDeployPackRuleService btDeployPackRuleService;
    @RequestMapping(params={"getFile"})
    public String getFile(String type,String filename,String fileNames,HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        RepositoryInfo repos = BuildReposManager.getByName("jeap1.0");
        DownloadFileType fileType = DownloadFileType.valueOf(type);
        HttpSession session = req.getSession();
        boolean bl = true;
        boolean bl2 = true;


        String userName = "360042212@qq.com";
        boolean istester = false;


        BtDeployPackRule dpr = null;

        File file = null;

        switch (fileType) {
            case build:
                file = new File(repos.getBuildDir(), filename);
                break;
            case dbuild:
                String[] ps = filename.split("/");
                try {
                    File root = PatchFileService.getRootOfBPByPatch("jeap1.0", ps[0]);
                    file = new File(root, ps[1]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case dispatchdd:
                file = PatchFileService.getDispatchDownLoadFile("jeap1.0", fileNames);
                bl2 = false;
                break;
            case epatch:
                dpr = btDeployPackRuleService.getBtDeployPackRuleByBranchAndDname("jeap1.0",filename.substring(0,filename.indexOf(".zip")));
                if(istester){
                    file = PatchFileService.encodePatch("jeap1.0", filename);
                }else{
                    if(dpr==null){
                        bl = false;
                        session.setAttribute("message", "补丁包未通过测试！\\n");
                        resp.sendRedirect(req.getContextPath() + "/manage/listpatch.jsp");
                    }else{
                        file = PatchFileService.encodePatch("jeap1.0", filename);
                    }
                }
                break;
            case log:
                file = new File(repos.getLogRoot(), filename + ".log");
                break;
            case patch:
                dpr = btDeployPackRuleService.getBtDeployPackRuleByBranchAndDname("jeap1.0",filename.substring(0,filename.indexOf(".zip")));
                if(istester){
                    file = new File(repos.getDeployDir(), filename);
                }else{
                    if(dpr==null){
                        bl = false;
                        session.setAttribute("message", "补丁包未通过测试！\\n");
                        resp.sendRedirect(req.getContextPath() + "/manage/listpatch.jsp");
                    }else{
                        file = new File(repos.getDeployDir(), filename);
                    }
                }
                break;
            case pbuild:
                file = BuildFileService.getPrivateFile("jeap1.0", filename);
                break;
            case bdlog:
                Map<String,List<BtBuildPackDetail>> bmap = btBuildPackDetailService.getBtBuildPackDetailListByBd("jeap1.0", filename);
                file = new File(repos.getLogRoot(), filename.substring(0,filename.indexOf(".zip")) + "_detail.log");
                if(file.exists()){
                    file.delete();
                }
                LogFileUtil lu = new LogFileUtil(file);
                lu.startWriter();
                List<Map.Entry<String, List<BtBuildPackDetail>>> mappingList = null;
                mappingList = new ArrayList<Map.Entry<String,List<BtBuildPackDetail>>>(bmap.entrySet());
                Collections.sort(mappingList, new Comparator<Map.Entry<String, List<BtBuildPackDetail>>>() {
                    public int compare(Map.Entry<String, List<BtBuildPackDetail>> mapping1, Map.Entry<String, List<BtBuildPackDetail>> mapping2) {
                        return mapping2.getKey().compareTo(mapping1.getKey());
                    }
                });
                for(Map.Entry<String, List<BtBuildPackDetail>> it:mappingList) {
                    lu.logMessageBd("补丁包：",it.getKey()) ;
                    for(BtBuildPackDetail bdetail:it.getValue()){
                        lu.logMessage(bdetail.getBuildPackDetail(), bdetail.getBuildPackName());
                    }
                }
                lu.closeWriter();
                break;
            default:
                break;
        }
        if(bl){
            if(bl2){
                downloadFile(req, resp, file);
            }else{
                downloadFileDelete(req, resp, file);
            }

            switch (fileType) {
                case pbuild:
                    buildConfigInfoService.saveBcConfigInPrivatePacking(userName,"jeap1.0",filename);
                    break;
                case patch:
                    buildConfigInfoService.saveBcConfigInDeployPacking(userName,"jeap1.0",filename);
                    break;
                case epatch:
                    buildConfigInfoService.saveBcConfigInEnDeployPacking(userName,"jeap1.0",filename);
                    break;
                default:
                    break;

            }
        }
        return "";
    }

    public void downloadFile(HttpServletRequest req, HttpServletResponse resp, File f) throws ServletException, IOException {
        String fileName = f.getName();
        if (fileName.indexOf('/') != -1 || fileName.indexOf('\\') != -1) {
            throw new ServletException("文件名称不合法" + fileName);
        }

        if (!f.exists() && !f.isFile())
            throw new ServletException("文件不存在" + fileName);
        logger.info("download file:" + f.getAbsolutePath() + f.length());
        FileInputStream fileInputStream = new FileInputStream(f);
        if (fileInputStream != null) {
            ServletOutputStream out = resp.getOutputStream();
            try {
                //resp.setContentType("application/x-msdownload");
                resp.setContentType("application/x-download");
                resp.setHeader("Content-Disposition", "attachment; filename=" +
                        new String(fileName.getBytes("UTF-8"),"iso8859-1") + "");

                final int buffSize = 1024;
                byte[] bs = new byte[buffSize];
                int len = fileInputStream.read(bs);
                while(len != -1) {
                    out.write(bs, 0, len);
                    out.flush();
                    len = fileInputStream.read(bs);
                }
            } catch (Throwable t) {
                logger.error("下载文件时出错", t);
            } finally {
                fileInputStream.close();
                out.flush();
                out.close();
            }
        }

    }

    public void downloadFileDelete(HttpServletRequest req, HttpServletResponse resp, File f) throws ServletException, IOException {
        String fileName = f.getName();
        if (fileName.indexOf('/') != -1 || fileName.indexOf('\\') != -1) {
            throw new ServletException("文件名称不合法" + fileName);
        }

        if (!f.exists() && !f.isFile())
            throw new ServletException("文件不存在" + fileName);
        logger.info("download file:" + f.getAbsolutePath() + f.length());
        FileInputStream fileInputStream = new FileInputStream(f);
        if (fileInputStream != null) {
            ServletOutputStream out = resp.getOutputStream();
            try {
                //resp.setContentType("application/x-msdownload");
                resp.setContentType("application/x-download");
                resp.setHeader("Content-Disposition", "attachment; filename=" +
                        new String(fileName.getBytes("utf-8"),"iso8859-1") + "");

                final int buffSize = 1024;
                byte[] bs = new byte[buffSize];
                int len = fileInputStream.read(bs);
                while(len != -1) {
                    out.write(bs, 0, len);
                    out.flush();
                    len = fileInputStream.read(bs);
                }
            } catch (Throwable t) {
                logger.error("下载文件时出错", t);
            } finally {
                fileInputStream.close();
                out.flush();
                out.close();
            }
        }
        if (f.exists() && f.isFile()){
            f.delete();
        }

    }
}
