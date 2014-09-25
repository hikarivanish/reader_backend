package me.hikari.rss.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class RssItem {
	private int id = 0;
	private int channelId = 0;
	// for quick access,
	//or select * from rss_item where 
	//channel_id in (select channel_id from channel where category_id = '$id');
	private int categoryId = 0;
	private String title = "";
	// "summary" is similar to description
	private String description = "";
	// "content:encoded" is similar to content
	private String content = "";
	// xml tag "id" is similar to link
	private String link = "";
	// "dc:creator" and "author>name" is similar to author
	private String author = "";
	private String authorUri = "";
	// "published" and "updated" are similar to pubDate
	private Date pubDate = new Date(0);
	private String comments = "";// link to comments
	// in xml the tag is "category"
	private List<String> tags = new ArrayList<String>();
	private boolean marked = false;

	public RssItem() {

	}

	public RssItem(int channelId, int categoryId) {
		this.channelId = channelId;
		this.categoryId = categoryId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorUri() {
		return authorUri;
	}

	public void setAuthorUri(String authorUri) {
		this.authorUri = authorUri;
	}

	// ------------------
	public Date getPubDate() {
		return pubDate;
	}

	public long getPubDateTimeStamp() {
		return pubDate.getTime();
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public void setPubDate(String pubDate) {
		// DateFormat dateFormat = new SimpleDateFormat(
		// "EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
		// try {
		// this.pubDate = dateFormat.parse(pubDate);
		// } catch (ParseException e) {
		// // e.printStackTrace();
		// try {
		// dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
		// Locale.US);
		// this.pubDate = dateFormat.parse(pubDate);
		// } catch (ParseException e1) {
		// try {
		// dateFormat = new SimpleDateFormat(
		// "EEE, dd MMM yyyy HH:mm:ss", Locale.US);
		// this.pubDate = dateFormat.parse(pubDate);
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// this.pubDate = new Date(0);
		// }
		// }
		// }
		final String[] formates = new String[] { "EEE, dd MMM yyyy HH:mm:ss Z",
				"EEE, dd MMM yyyy HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss",
				"yyyy-MM-dd HH:mm:ss" };
		for (String formate : formates) {
			DateFormat dateFormat = new SimpleDateFormat(formate, Locale.US);
			try {
				this.pubDate = dateFormat.parse(pubDate);
				return;
			} catch (Exception e) {
				//Log.w("trying formate", formate + "|for| " + pubDate);
			}
		}
//		Log.e("try formate failed", pubDate);
	}

	// ------------------
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<String> getTags() {
		return tags;
	}

	// public void setTags(List<String> tags) {
	// this.tags = tags;
	// }
	public void addTag(String tag) {
		this.tags.add(tag);
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	@Override
	// public String toString() {
	// return "RssItem [id=" + id + ", channelId=" + channelId
	// + ", categoryId=" + categoryId + ", title=" + title
	// + ", description=" + description + ", content=" + content
	// + ", link=" + link + ", author=" + author + ", authorUri="
	// + authorUri + ", pubDate=" + pubDate + ", comments=" + comments
	// + ", tags=" + tags + "]";
	// }
	public String toString() {
		return "id=" + id + ", channelId=" + channelId + ", categoryId="
				+ categoryId + ", title=" + title + ", description="
				+ ", link=" + link + ", author=" + author + ", authorUri="
				+ authorUri + ", pubDate=" + pubDate + ", comments=" + comments
				+ ", tags=" + tags;
	}

	public void setPubDate(long t) {
		this.pubDate = new Date(t);
	}

}
