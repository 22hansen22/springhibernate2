package com.jwt.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwt.model.Employee;
import com.jwt.model.XX;

@Repository
public class XXDAOImpl implements XXDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addXX(XX employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);

	}

	@SuppressWarnings("unchecked")
	public List<XX> getAllXXs() {

		return sessionFactory.getCurrentSession().createQuery("from XX")
				.list();
	}

	@Override
	public void deleteXX(Integer xxId) {
		XX xx = (XX) sessionFactory.getCurrentSession().load(
				XX.class, xxId);
		if (null != xx) {
			this.sessionFactory.getCurrentSession().delete(xx);
		}

	}

	public XX getXX(int xxid) {
		return (XX) sessionFactory.getCurrentSession().get(
				XX.class, xxid);
	}

	@Override
	public XX updateXX(XX xx) {
		sessionFactory.getCurrentSession().update(xx);
		return xx;
	}

}