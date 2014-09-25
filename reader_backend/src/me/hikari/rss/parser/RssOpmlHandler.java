package me.hikari.rss.parser;

import java.util.ArrayList;
import java.util.List;

import me.hikari.rss.bean.RssChannel;
import me.hikari.rss.bean.RssChannelCategory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RssOpmlHandler extends DefaultHandler {
	private static final String TAG_OUTLINE = "outline", TAG_TEXT = "text",
			TAG_TITLE = "title", TAG_HTML_URL = "htmlUrl", TAG_TYPT = "type",
			TAG_XML_URL = "xmlUrl";

	private enum ParsingState {
		INIT, CATOGERY, CHANNEL
	};

	private ParsingState parsingState;
	private StringBuffer buffer;
	private List<RssChannelCategory> rssCatogeries;
	private RssChannelCategory rssCatogery;
	private RssChannel rssChannel;

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		parsingState = ParsingState.INIT;
		rssCatogeries = new ArrayList<RssChannelCategory>();
	}

	public List<RssChannelCategory> getCategories() {
		return rssCatogeries;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if (qName == null || qName.equals(""))
			return;
		switch (parsingState) {
		case INIT:
			if (qName.equals(TAG_OUTLINE)) {
				parsingState = ParsingState.CATOGERY;
				rssCatogery = new RssChannelCategory();
				//rssCatogery.setText(attributes.getValue(TAG_TEXT));
				rssCatogery.setTitle(attributes.getValue(TAG_TITLE));
			}
			break;
		case CATOGERY:
			if (qName.equals(TAG_OUTLINE)) {
				parsingState = ParsingState.CHANNEL;
				rssChannel = new RssChannel();
				rssChannel.setHtmlUrl(attributes.getValue(TAG_HTML_URL));
				//rssChannel.setText(attributes.getValue(TAG_TEXT));
				rssChannel.setTitle(attributes.getValue(TAG_TITLE));
				//rssChannel.setType(attributes.getValue(TAG_TYPT));
				rssChannel.setXmlUrl(attributes.getValue(TAG_XML_URL));
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		if (qName == null || qName.equals(""))
			return;
		switch (parsingState) {
		case CHANNEL:
			if (qName.equals(TAG_OUTLINE)) {
				parsingState = ParsingState.CATOGERY;
				rssCatogery.addChannel(rssChannel);
			}
			break;
		case CATOGERY:
			if (qName.equals(TAG_OUTLINE)) {
				parsingState = ParsingState.INIT;
				rssCatogeries.add(rssCatogery);
			}
			break;
		default:
			break;
		}
	}
}
