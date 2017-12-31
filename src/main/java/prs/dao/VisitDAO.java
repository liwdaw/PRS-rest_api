/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prs.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import prs.entity.Visit;

/**
 *
 * @author Janusz
 */
@Repository
public class VisitDAO {
    @PersistenceContext	
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Visit> getAllVisits(){
        String hql = "FROM Visit as visits ORDER BY visits.visitID";
		return (List<Visit>) entityManager.createQuery(hql).getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Visit> getThisDoctorVisits(String name){
        String hql = "FROM Visit as visits WHERE visits.doctor.account.username = ? ORDER BY visits.date";
        List<Visit> visits = entityManager.createQuery(hql)
				.setParameter(1, name).getResultList();
		return visits;
    }
    
    @SuppressWarnings("unchecked")
	public List<Visit> getThisPatientVisits(String name){
        String hql = "FROM Visit as visits WHERE visits.patient.account.username = ? ORDER BY visits.date";
        List<Visit> visits = entityManager.createQuery(hql)
				.setParameter(1, name).getResultList();
		return visits;
    }
    
    @SuppressWarnings("unchecked")
	public List<Visit> getThisDoctorFutureVisits(String name){
        String hql = "FROM Visit as visits WHERE visits.doctor.account.username = ? AND visits.date > ? ORDER BY visits.date";
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        List<Visit> visits = entityManager.createQuery(hql)
				.setParameter(1, name)
				.setParameter(2, date)
				.getResultList();
		return visits;
    }
    
    @SuppressWarnings("unchecked")
	public List<Visit> getThisPatientFutureVisits(String name){
        String hql = "FROM Visit as visits WHERE visits.patient.account.username = ? AND visits.date > ? ORDER BY visits.date";
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        List<Visit> visits = entityManager.createQuery(hql)
				.setParameter(1, name)
				.setParameter(2, date)
				.getResultList();
		return visits;
    }
    
    @SuppressWarnings("unchecked")
	public List<Visit> getThisDoctorPastVisits(String name){
        String hql = "FROM Visit as visits WHERE visits.doctor.account.username = ? AND visits.date < ? ORDER BY visits.date DESC";
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        List<Visit> visits = entityManager.createQuery(hql)
				.setParameter(1, name)
				.setParameter(2, date)
				.getResultList();
		return visits;
    }
    
    @SuppressWarnings("unchecked")
	public List<Visit> getThisPatientPastVisits(String name){
        String hql = "FROM Visit as visits WHERE visits.patient.account.username = ? AND visits.date < ? ORDER BY visits.date DESC";
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        List<Visit> visits = entityManager.createQuery(hql)
				.setParameter(1, name)
				.setParameter(2, date)
				.getResultList();
		return visits;
    }
    
    @SuppressWarnings("unchecked")
	public List<Visit> getAllFutureVisits(){
        String hql = "FROM Visit as visits WHERE visits.date > ? ORDER BY visits.date";
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        List<Visit> visits = entityManager.createQuery(hql)
				.setParameter(1, date)
				.getResultList();
		return visits;
    }
    
    @SuppressWarnings("unchecked")
	public List<Visit> getThisDoctorTodayVisits(String name){
        String hql = "FROM Visit as visits WHERE visits.doctor.account.username = ? AND visits.date > ? AND visits.date < ? ORDER BY visits.date";
        java.sql.Date dateStart = new java.sql.Date(new java.util.Date().getTime() - 24*60*60*1000);
        java.sql.Date dateEnd = new java.sql.Date(new java.util.Date().getTime() + 24*60*60*1000);
        List<Visit> visits = entityManager.createQuery(hql)
				.setParameter(1, name)
				.setParameter(2, dateStart)
				.setParameter(3, dateEnd)
				.getResultList();
        System.out.println(dateStart);
        System.out.println(dateEnd);
		return visits;
    }

    @SuppressWarnings("unchecked")
    public List<Visit> getThisDoctorDateVisits(String name, Date date) {
        String hql = "FROM Visit as visits WHERE visits.doctor.account.username = ? AND visits.date > ? AND visits.date < ? ORDER BY visits.date";
        java.sql.Date dateStart = new java.sql.Date(date.getTime() - 24*60*60*1000);
        java.sql.Date dateEnd = new java.sql.Date(date.getTime() + 24*60*60*1000);
        List<Visit> visits = entityManager.createQuery(hql)
                .setParameter(1, name)
                .setParameter(2, dateStart)
                .setParameter(3, dateEnd)
                .getResultList();
        return visits;
    }

    @SuppressWarnings("unchecked")
    public List<Visit> getThisDoctorDateVisitsBetween(String name, Date dateStart, Date dateEnd) {
        String hql = "FROM Visit as visits WHERE visits.doctor.account.username = ? AND visits.date > ? AND visits.date < ? ORDER BY visits.date";
        java.sql.Date sqlDateStart = new java.sql.Date(dateStart.getTime() - 24*60*60*1000);
        java.sql.Date sqlDateEnd = new java.sql.Date(dateEnd.getTime() + 24*60*60*1000);
        List<Visit> visits = entityManager.createQuery(hql)
                .setParameter(1, name)
                .setParameter(2, sqlDateStart)
                .setParameter(3, sqlDateEnd)
                .getResultList();
        return visits;
    }
}
