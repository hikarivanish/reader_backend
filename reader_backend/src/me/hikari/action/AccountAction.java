package me.hikari.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.LikeExpression;
import org.hibernate.criterion.Restrictions;

import me.hikari.model.HibernateSessionFactory;
import me.hikari.model.User;
import me.hikari.util.HashUtil;

import com.opensymphony.xwork2.ActionSupport;

public class AccountAction extends ActionSupport implements SessionAware {

	private static final String LOGIN_RESULT = "login_result";
	/*******************************************************/
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		if (username != null && password != null && !"".equals(username)
				&& !"".equals(password)) {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("userName", username));
			for (Object _user : criteria.list()) {
				User user = (User) _user;
				if (HashUtil.Md5Hex(password).equals(user.getPassword())) {
					// put user info in the session
					this.applicationSession.put("user", user);
					this.loginResult = new LoginResult(true, "login_success");
					return LOGIN_RESULT;
				}
			}
		}
		return LOGIN_RESULT;
	}

	private LoginResult loginResult = new LoginResult(false, "login_fail");

	public LoginResult getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(LoginResult loginResult) {
		this.loginResult = loginResult;
	}

	public static class LoginResult {
		public LoginResult(boolean login, String message) {
			this.login = login;
			this.message = message;
		}

		public LoginResult() {

		}

		private boolean login = false;
		private String message = "";

		public boolean isLogin() {
			return login;
		}

		public void setLogin(boolean login) {
			this.login = login;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

	/*******************************************************/
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String userInfo() {
		this.user = (User) this.applicationSession.get("user");
		return SUCCESS;
	}

	private Map<String, Object> applicationSession;

	@Override
	public void setSession(Map<String, Object> _map) {
		this.applicationSession = _map;
	}
}
