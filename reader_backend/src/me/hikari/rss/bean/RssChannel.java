package me.hikari.rss.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class RssChannel {
	private int id = 0;
	private int categoryId = 0;
	// "text" is similar to title
	private String title = "testTitle";
	private String imgUrl = "testImgUrl";
	private String imgTitle = "testImgTitle";
	private String imgLink = "TestImgLink";
	private String copyRight = "testCopyRight";
	private String generator = "testGenerator";
	private String language = "testLanguage";
	// "pubDate" and "updated" are similar to lastBuildDate
	private Date lastBuildDate = new Date(0);
	// "subtitle" is similar to description
	private String description = "testDescription";
	// "atom:link" is similar to xmlUrl
	private String xmlUrl = "testXmlUrl";
	// "link" is similar to htmlUrl
	private String htmlUrl = "testHtmlUrl";
	private List<RssItem> items = new ArrayList<RssItem>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgTitle() {
		return imgTitle;
	}

	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public String getCopyRight() {
		return copyRight;
	}

	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}

	public String getGenerator() {
		return generator;
	}

	public void setGenerator(String generator) {
		this.generator = generator;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	// ------------------
	public Date getLastBuildDate() {
		return lastBuildDate;
	}

	public long getLastBuildDateTimeStamp() {
		return lastBuildDate.getTime();
	}

	public void setLastBuildDate(Date lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public void setLastBuildDate(String lastBuildDate) {
		// DateFormat dateFormat = new SimpleDateFormat(
		// "EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
		// try {
		// this.lastBuildDate = dateFormat.parse(lastBuildDate);
		// } catch (ParseException e) {
		// try {
		// dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
		// Locale.US);
		// this.lastBuildDate = dateFormat.parse(lastBuildDate);
		// } catch (ParseException e1) {
		// try {
		// dateFormat = new SimpleDateFormat(
		// "EEE, dd MMM yyyy HH:mm:ss", Locale.US);
		// this.lastBuildDate = dateFormat.parse(lastBuildDate);
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// this.lastBuildDate = new Date(0);
		// }
		// }
		// }
		final String[] formates = new String[] { "EEE, dd MMM yyyy HH:mm:ss Z",
				"EEE, dd MMM yyyy HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss",
				"yyyy-MM-dd HH:mm:ss" };
		for (String formate : formates) {
			DateFormat dateFormat = new SimpleDateFormat(formate, Locale.US);
			try {
				this.lastBuildDate = dateFormat.parse(lastBuildDate);
				return;
			} catch (Exception e) {
				//Log.w("trying formate", formate + "|for| " + lastBuildDate);
			}
		}
//		Log.e("try formate failed", lastBuildDate);
	}

	// ------------------
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getXmlUrl() {
		return xmlUrl;
	}

	public void setXmlUrl(String xmlUrl) {
		this.xmlUrl = xmlUrl;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public List<RssItem> getItems() {
		return items;
	}

	public void addItem(RssItem item) {
		this.items.add(item);
	}

	@Override
	public String toString() {
		return "RssChannel [id=" + id + ", categoryId=" + categoryId
				+ ", title=" + title + ", imgUrl=" + imgUrl + ", imgTitle="
				+ imgTitle + ", imgLink=" + imgLink + ", copyRight="
				+ copyRight + ", generator=" + generator + ", language="
				+ language + ", lastBuildDate=" + lastBuildDate
				+ ", description=" + description + ", xmlUrl=" + xmlUrl
				+ ", htmlUrl=" + htmlUrl + ", items=" + items.size() + "]\n";
	}

	public void setLastBuildDate(long t) {
		this.lastBuildDate = new Date(t);
	}

}
