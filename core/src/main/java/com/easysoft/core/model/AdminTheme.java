package com.easysoft.core.model;

/**
 * @author andy
 * @version 1.0
 */
public class AdminTheme extends Resource {
	private String themename;
	private String path;
	private String author;
	private String version;
	private String thumb = "preview.png";

	/**
	 * 0否 1是
	 */
	private int framemode;


	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getThemename() {
		return themename;
	}

	public void setThemename(String themename) {
		this.themename = themename;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getFramemode() {
		return framemode;
	}

	public void setFramemode(int framemode) {
		this.framemode = framemode;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
