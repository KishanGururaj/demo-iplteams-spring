package com.wolken.wolkenapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import com.wolken.wolkenapp.dto.TeamDTO;

@Component
public class TeamDAOImpl implements TeamDAO {

	@Autowired
	LocalSessionFactoryBean bean;
	Logger logger = Logger.getLogger("TeamDAOImpl");

	public String save(TeamDTO teamDTO) {

		/*
		 * Configuration configuration=bean.getConfiguration();
		 * configuration.configure(); SessionFactory
		 * factory=configuration.buildSessionFactory();
		 */
		try {
			SessionFactory factory = bean.getObject();
			logger.info("inisde save method");
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(teamDTO);
			transaction.commit();
			session.close();
			return "data added";
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return "data not added";
	}

	public String delete(String teamName) {
		// TODO Auto-generated method stub
		try {
			SessionFactory factory = bean.getObject();
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("delete from TeamDTO tr where tr.teamName =:teamName");
			query.setParameter("teamName", teamName);
			query.executeUpdate();
			transaction.commit();
			session.close();
			return "data delete ";
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return "data is not delete ";
	}

	public String updateCaptainByTeamName(String teamName, String teamCaptain) {
		// TODO Auto-generated method stub
		try {
			SessionFactory factory = bean.getObject();
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.getNamedQuery("updateByTeamName");
			query.setParameter("teamCaptain", teamCaptain);
			query.setParameter("teamName", teamName);
			query.executeUpdate();
			// transaction.commit();
			session.close();
			return "data updated successfully";
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return "data is not updated successfully";
	}

	public List<TeamDTO> getAll() {
		// TODO Auto-generated method stub
		try {
			SessionFactory factory = bean.getObject();
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.getNamedQuery("selectAll");
			List<TeamDTO> dtos = query.list();
			session.close();

			return dtos;
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}

}
