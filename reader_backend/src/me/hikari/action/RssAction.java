package me.hikari.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.hikari.model.Channel;
import me.hikari.model.HibernateSessionFactory;
import me.hikari.model.Item;
import me.hikari.model.User;

import org.apache.struts2.interceptor.SessionAware;
import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

public class RssAction extends ActionSupport implements SessionAware {
	/************************ Request parameters **************************/
	private int channelId;
	private int itemStart;
	private int itemLimit;

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public int getItemStart() {
		return itemStart;
	}

	public void setItemStart(int itemStart) {
		this.itemStart = itemStart;
	}

	public int getItemLimit() {
		return itemLimit;
	}

	public void setItemLimit(int itemLimit) {
		this.itemLimit = itemLimit;
	}

	/**************************************************/
	private List items = new ArrayList();

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String retrieveItem() {
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = session.createCriteria(Item.class);
		criteria.add(Restrictions.eq("channelId", this.channelId));
		criteria.addOrder(Order.desc("pubDate"));
		criteria.setFirstResult(itemStart);
		criteria.setMaxResults(itemLimit);
		this.items = criteria.list();
		return SUCCESS;
	}

	/**************************************************/
	private Map<String, Object> applicationSession;

	@Override
	public void setSession(Map<String, Object> session) {
		this.applicationSession = session;
	}

}