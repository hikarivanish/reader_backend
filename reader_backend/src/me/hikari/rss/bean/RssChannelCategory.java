package me.hikari.rss.bean;

import java.util.ArrayList;
import java.util.List;

public class RssChannelCategory {
	private int id = 0;
	private String title = "RssChannelCategory_default_title";
	private List<RssChannel> channels = new ArrayList<RssChannel>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<RssChannel> getChannels() {
		return channels;
	}

	public void addChannel(RssChannel channel) {
		this.channels.add(channel);
	}

	@Override
	public String toString() {
		return "RssChannelCategory [id=" + id + ", title=" + title
				+ ", channels=\n" + channels + "\n]";
	}

	public void setChannels(List<RssChannel> channels) {
		this.channels = channels;
	}

}
