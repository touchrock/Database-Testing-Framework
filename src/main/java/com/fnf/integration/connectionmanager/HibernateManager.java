package com.fnf.integration.connectionmanager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateManager {
	
	private boolean sessionInitialized = false;
	private SessionFactory sessionFactory;
	private Session session;
	private static final HibernateManager INSTANCE = new HibernateManager();
	
	public void initializeAbility(Configuration config) throws HibernateException {
		
		try {
			initializeSessionFactory(config);
		}
		catch (HibernateException e) {
			throw e;
		}
	}
	
	private HibernateManager() { }
	
	public static HibernateManager getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Initialize the session factory from a configuration settings. The 
	 * settings must conform to the hibernate-configuration standard.
	 * 
	 */
	private synchronized void intializeSessionFactory(Configuration config) {
		
		if (isSessionInitialized()) {
			return;
		}
		else {
			try {
				sessionFactory = config.buildSessionFactory();
				setSessionInitialized();
			}
			catch (HibernateException e) {
				throw e;
			}
		}
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public synchronized Session getCurrentSession() throws HibernateException {
		
		try {
			if (session == null || !session.isOpen()) {
				session = getSessionFactory.openSession();
			}
			
			return session;
		}
		catch (HibernateException e) {
			throw e;
		}
	}
}
