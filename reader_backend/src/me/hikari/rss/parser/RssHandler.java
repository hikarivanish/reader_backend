package me.hikari.rss.parser;

import me.hikari.rss.bean.RssChannel;
import me.hikari.rss.bean.RssItem;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 The characters method can be called more than once for the text within a single pair of open and close tags.
 if you initialize content in characters method ,it be true for small data, but not always.
 You need to initialize a buffer in the startElement method for that tag, 
 collect into the buffer in the characters method, and convert the buffer to a string in the endElement.
 */
public class RssHandler extends DefaultHandler {
	private static final String TAG = "RSSHandler";
	private RssChannel rssChannel = null;
	private RssItem rssItem = null;
	private StringBuffer content = null;
	private RSSMode rssMode = RSSMode.MODE_RSS;
	private ParsingState parsingState = ParsingState.CHANNEL;
	private static final String RSS_TAG_TITLE = "title", RSS_TAG_LINK = "link",
			RSS_TAG_AUTHOR = "author", RSS_TAG_DESCRIPTION = "description",
			RSS_TAG_LAST_BUILD_DATE = "lastBuildDate",
			RSS_TAG_PUB_DATE = "pubDate", RSS_TAG_UPDATED = "updated",
			RSS_TAG_CONTENT = "content", RSS_TAG_ID = "id",
			RSS_TAG_PUBLISHED = "published", RSS_TAG_NAME = "name",
			RSS_TAG_SUMMARY = "summary", RSS_TAG_LANGUAGE = "language",
			RSS_TAG_COPYRIGHT = "copyright", RSS_TAG_DC_CREATOR = "dc:creator",
			RSS_TAG_GENERATOR = "generator", RSS_TAG_URL = "url",
			RSS_TAG_SUBTITLE = "subtitle", RSS_TAG_URI = "uri",
			RSS_TAG_CATEGORY = "category", RSS_TAG_COMMENTS = "comments",
			RSS_TAG_CONTENT_ENCODED = "content:encoded";

	// rss��xml�ļ��и�����ʽ
	private enum RSSMode {
		MODE_RSS, MODE_FEED
	};

	// �����Ľ׶Σ����ڷ���title��channel��item���е�����
	private enum ParsingState {
		CHANNEL, ITEM, IMAGE, AUTHOR
	};

	public RssHandler(RssChannel channel) {
		this.rssChannel = channel;
	}

	public RssHandler() {
		this.rssChannel = new RssChannel();
	}

	public RssChannel getRssChannel() {
		return rssChannel;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		if (rssChannel == null)
			rssChannel = new RssChannel();
		rssChannel.getItems().clear();
		rssMode = RSSMode.MODE_RSS;
		parsingState = ParsingState.CHANNEL;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		content = new StringBuffer();
		if (qName == null || qName.equals(""))
			return;
		// Log.v(TAG, "start " + qName);
		if (qName.equals("rss")) { // ���xml��һ����ǩ��rss
			rssMode = RSSMode.MODE_RSS;
		} else if (qName.equals("feed")) { // ���xml��һ����ǩ��feed
			rssMode = RSSMode.MODE_FEED;
			// Log.i(TAG, "feed mode");
		} else {
			switch (rssMode) {
			case MODE_RSS:
				if (qName.equals("item")) {
					rssItem = new RssItem(rssChannel.getId(),
							rssChannel.getCategoryId());
					parsingState = ParsingState.ITEM;
				} else if (qName.equals("image")
						&& parsingState == ParsingState.CHANNEL) {
					parsingState = ParsingState.IMAGE;
				}
				break;
			case MODE_FEED:
				if (qName.equals("entry")) {
					rssItem = new RssItem(rssChannel.getId(),
							rssChannel.getCategoryId());
					parsingState = ParsingState.ITEM;
				} else if (qName.equals("author")) {
					parsingState = ParsingState.AUTHOR;
				}
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		if (qName == null || qName.equals(""))
			return;
		// Log.v(TAG, "end " + qName);
		switch (rssMode) {
		case MODE_RSS:
			switch (parsingState) {
			case CHANNEL:
				if (qName.equals(RSS_TAG_TITLE)) {
					rssChannel.setTitle(content.toString());
				} else if (qName.equals(RSS_TAG_LAST_BUILD_DATE)) {
					rssChannel.setLastBuildDate(content.toString());
				} else if (qName.equals(RSS_TAG_LINK)) {
					rssChannel.setHtmlUrl(content.toString());
				} else if (qName.equals(RSS_TAG_DESCRIPTION)) {
					// Log.i(TAG, content.toString());
					rssChannel.setDescription(content.toString());
				} else if (qName.equals(RSS_TAG_COPYRIGHT)) {
					rssChannel.setCopyRight(content.toString());
				} else if (qName.equals(RSS_TAG_LANGUAGE)) {
					rssChannel.setLanguage(content.toString());
				} else if (qName.equals(RSS_TAG_GENERATOR)) {
					rssChannel.setGenerator(content.toString());
				}
				break;
			case ITEM:
				if (qName.equals(RSS_TAG_TITLE)) {
					rssItem.setTitle(content.toString());
				} else if (qName.equals(RSS_TAG_LINK)) {
					rssItem.setLink(content.toString());
				} else if (qName.equals(RSS_TAG_PUB_DATE)) {
					rssItem.setPubDate(content.toString());
				} else if (qName.equals(RSS_TAG_AUTHOR)) {
					rssItem.setAuthor(content.toString());
				} else if (qName.equals(RSS_TAG_DC_CREATOR)) {
					rssItem.setAuthor(content.toString());
				} else if (qName.equals(RSS_TAG_DESCRIPTION)) {
					rssItem.setDescription(content.toString());
				} else if (qName.equals(RSS_TAG_CONTENT_ENCODED)) {
					rssItem.setContent(content.toString());
				} else if (qName.equals(RSS_TAG_CATEGORY)) {
					rssItem.addTag(content.toString());
				} else if (qName.equals(RSS_TAG_COMMENTS)) {
					rssItem.setComments(content.toString());
				} else if (qName.equals("item")) {
					rssChannel.addItem(rssItem);
					parsingState = ParsingState.CHANNEL;
				}
				break;
			case IMAGE:
				if (qName.equals(RSS_TAG_LINK)) {
					rssChannel.setImgLink(content.toString());
				} else if (qName.equals(RSS_TAG_TITLE)) {
					rssChannel.setImgTitle(content.toString());
				} else if (qName.equals(RSS_TAG_URL)) {
					rssChannel.setImgUrl(content.toString());
				} else if (qName.equals("image")) {
					parsingState = ParsingState.CHANNEL;
				}
				break;
			default:
				break;
			}
			break;
		case MODE_FEED:
			// Log.i(TAG, "endElement:" + qName + " state:" + parsingState);
			switch (parsingState) {
			case CHANNEL:
				if (qName.equals(RSS_TAG_TITLE)) {
					rssChannel.setTitle(content.toString());
				} else if (qName.equals(RSS_TAG_UPDATED)) {
					rssChannel.setLastBuildDate(content.toString());
				} else if (qName.equals(RSS_TAG_SUBTITLE)) {
					rssChannel.setDescription(content.toString());
				} else if (qName.equals(RSS_TAG_GENERATOR)) {
					rssChannel.setGenerator(content.toString());
				}
				break;
			case ITEM:
				if (qName.equals(RSS_TAG_ID)) {
					rssItem.setLink(content.toString());
				} else if (qName.equals(RSS_TAG_TITLE)) {
					rssItem.setTitle(content.toString());
				} else if (qName.equals(RSS_TAG_CONTENT)) {
					rssItem.setDescription(content.toString());
				} else if (qName.equals(RSS_TAG_NAME)) {
					rssItem.setAuthor(content.toString());
				} else if (qName.equals(RSS_TAG_PUBLISHED)) {
					rssItem.setPubDate(content.toString());
				} else if (qName.equals(RSS_TAG_UPDATED)) {
					rssItem.setPubDate(content.toString());
				} else if (qName.equals(RSS_TAG_SUMMARY)) {
					rssItem.setDescription(content.toString());
				} else if (qName.equals("entry")) {
					rssChannel.addItem(rssItem);
					// Log.i(TAG, "addItem " + rssItem);
					parsingState = ParsingState.CHANNEL;
				}
				break;
			case AUTHOR:
				if (qName.equals(RSS_TAG_NAME)) {
					rssItem.setAuthor(content.toString());
				} else if (qName.equals(RSS_TAG_URI)) {
					rssItem.setAuthorUri(content.toString());
				} else if (qName.equals("author")) {
					parsingState = ParsingState.ITEM;
				}
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		content.append(ch, start, length);
	}
}
