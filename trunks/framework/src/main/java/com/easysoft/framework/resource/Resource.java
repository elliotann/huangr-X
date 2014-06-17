package com.easysoft.framework.resource;

/**
 * User: andy
 * Date: 13-8-7
 * Time: 下午5:34
 *
 * @since:
 */
public class Resource {
    private String src;
    private int compress;
    private int merge;
    private String type;
    private boolean iscommon;

    public String getSrc()
    {
        return this.src;
    }
    public void setSrc(String src) {
        this.src = src;
    }
    public int getCompress() {
        return this.compress;
    }
    public void setCompress(int compress) {
        this.compress = compress;
    }
    public int getMerge() {
        return this.merge;
    }
    public void setMerge(int merge) {
        this.merge = merge;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public boolean isIscommon() {
        return this.iscommon;
    }
    public void setIscommon(boolean iscommon) {
        this.iscommon = iscommon;
    }

}
