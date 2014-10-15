/*
 *
 *  * Copyright 1999-2011 jeap Group Holding Ltd.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.easysoft.build.manager;

import com.easysoft.build.model.RepositoryInfo;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : andy.huang
 * @since :
 */
public abstract class BuildReposManager
{
    public static final String SVN_LIST_FILE_NAME = "build_repositories.xml";
    private static Map<String, RepositoryInfo> infos = new Hashtable();
    private static File configFile = null;
    private static long lastModify = -1L;

    public static void init()
    {
        synchronized (infos) {
            if (configFile == null) {
                URL res = BuildReposManager.class.getClassLoader().getResource("build_repositories.xml");
                configFile = new File(res.getFile());
                if ((!configFile.isFile()) && (!configFile.exists())) {
                    configFile = null;
                    return;
                }
                lastModify = configFile.lastModified();
                try {
                    readConfig(res.openStream(), false);
                } catch (Exception e) {
                    configFile = null;
                    e.printStackTrace();
                }
            } else if (lastModify != configFile.lastModified()) {
                try {
                    readConfig(new FileInputStream(configFile), false);
                    lastModify = configFile.lastModified();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void readConfig(InputStream in, boolean append)
            throws JDOMException, IOException
    {
        SAXBuilder builder = new SAXBuilder(false);
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        Namespace ns = root.getNamespace();
        if (!append)
            infos.clear();
        List list = root.getChildren("repository", ns);
        for (int i = 0; i < list.size(); ++i) {
            Element reposEl = (Element)list.get(i);
            RepositoryInfo reposInfo = new RepositoryInfo();
            reposInfo.setName(reposEl.getChildTextTrim("name", ns).toLowerCase());
            reposInfo.setSvnUrl(reposEl.getChildTextTrim("svn_url", ns));
            reposInfo.setTagUrl(reposEl.getChildTextTrim("tag_url", ns));
            reposInfo.setSvnUser(reposEl.getChildTextTrim("svn_user", ns));
            reposInfo.setSvnPassword(reposEl.getChildTextTrim("svn_password", ns));
            reposInfo.setTestUsers(reposEl.getChildTextTrim("test-users", ns));
            reposInfo.setDeployUsers(reposEl.getChildTextTrim("deploy-users", ns));
            reposInfo.setSss(reposEl.getChildTextTrim("ss-users", ns));

            reposInfo.setSpNo(reposEl.getChildTextTrim("sp_no", ns));
            reposInfo.setCreateDate(reposEl.getChildTextTrim("create_date", ns));
            reposInfo.setBand(reposEl.getChildTextTrim("band", ns));
            reposInfo.setPermitBand(reposEl.getChildTextTrim("permit_band", ns));
            reposInfo.setPermitPatch(reposEl.getChildTextTrim("permit_patch", ns));
            reposInfo.setMainVersion(reposEl.getChildTextTrim("main_version", ns));
            reposInfo.setSubVersion(reposEl.getChildTextTrim("sub_version", ns));
            reposInfo.setJdkVersion(reposEl.getChildTextTrim("jdk_version", ns));
            reposInfo.setSrcEncoding(reposEl.getChildTextTrim("src_encoding", ns));
            reposInfo.setIsWeekbug(reposEl.getChildTextTrim("is_weekbug", ns));

            String value = reposEl.getChildTextTrim("version_no", ns);
            if (value != null)
                reposInfo.setVersionNo(value);
            value = reposEl.getChildTextTrim("version_pattern", ns);
            if (value != null)
                reposInfo.setVersionPattern(value);
            value = reposEl.getChildTextTrim("version_suffix", ns);
            if (value != null)
                reposInfo.setVersionSuffix(value);
            value = reposEl.getChildTextTrim("sql_encoding", ns);
            if (value != null)
                reposInfo.setSqlEncoding(value);
            value = reposEl.getChildTextTrim("svn_root", ns);
            if (value != null) {
                reposInfo.setSvnRoot(value);
            }
            reposInfo.setBuildDir(toFile(reposEl, ns, "build_dir"));
            reposInfo.setDeployDir(toFile(reposEl, ns, "deploy_dir"));
            reposInfo.setCompileLibDir(toFile(reposEl, ns, "compile_lib_dir"));
            reposInfo.setCompileClassDir(toFile(reposEl, ns, "compile_class_dir"));
            reposInfo.setDeployBackupDir(toFile(reposEl, ns, "deploy_backup_dir"));
            reposInfo.setWorkspace(toFile(reposEl, ns, "workspace"));
            reposInfo.setVersionInfo(toFile(reposEl, ns, "version_info"));
            reposInfo.setBranchRoot(toFile(reposEl, ns, "branchRoot"));

            String ps = reposEl.getChildTextTrim("projects", ns);
            if (ps != null) {
                String[] projects = reposEl.getChildTextTrim("projects", ns).split(";");
                for (String p : projects) {
                    reposInfo.addProject(p.trim());
                }
            }
            infos.put(reposInfo.getName(), reposInfo);
        }
        in.close();
    }

    private static File toFile(Element reposEl, Namespace ns, String elementName)
    {
        String file = reposEl.getChildTextTrim(elementName, ns);
        if (file != null)
            return new File(file);
        return null;
    }

    public static String[] listNames()
    {
        init();
        Set set = infos.keySet();
        return (String[])(String[])set.toArray(new String[set.size()]);
    }

    public static RepositoryInfo getByName(String name)
    {
        init();
        return (RepositoryInfo)infos.get(name.toLowerCase());
    }
}

