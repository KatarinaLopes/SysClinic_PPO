/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.hibernateutil;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.BootstrapServiceRegistryImpl;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Katarina
 */
public class HibernateUtil {

    private Configuration cfg;
    private SessionFactory sessionFactory;
    private Session s;

    private static HibernateUtil myself = null;

    private HibernateUtil() {
        cfg = new Configuration();

        try {
            sessionFactory = cfg.configure("hibernate.cfg.xml").
                    buildSessionFactory(new StandardServiceRegistryBuilder()
                            .applySettings(cfg.getProperties()).build());
            s = sessionFactory.openSession();
        } catch (HibernateException th) {
            System.err.println("Failed to create SessionFactory" + th);
        }
    }

    private Session getSession() {
        String a = new String();

        //s.clear();
        
        try {
            s.close();
        } catch (HibernateException he) {

        } finally {
            s = sessionFactory.openSession();
        }
        return s;
    }

    public static HibernateUtil getInstance() {
        if (myself == null) {
            myself = new HibernateUtil();
        }
        return myself;
    }

    public void persist(Object o) throws ConstraintViolationException {

        Transaction t = getSession().getTransaction();

        t.begin();

        s.save(o);

        t.commit();

        //s.close();
        //}catch(HibernateException he){
        //System.err.println("Could not operate on session: " + he);
        //}
    }

    //"Prepared statement"
    /*public List recover(String table, String column, Object value) { //testar
        Session s = null;

        try {
            s = sessionFactory.openSession();

        } catch (HibernateException he) {
            System.err.println("Could not open session to recover operation" + he);
            return null;
        }

        Query query;
        
        if(column == null){
            query = s.createQuery("from " + table);
        }else{
            query = s.createQuery("from " + table + " where " + column + " =:value");
            query.setParameter("value", value);
        }

        try {
            return query.list();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }*/
    public List recover(String hql) throws IndexOutOfBoundsException {
        Query query = null;

        query = getSession().createQuery(hql);

        return query.list();

    }

    public void update(Object o) {
        //Se a sessão é aberta, tem que ser fechada

        String a = new String();
        //s = sessionFactory.openSession();

        Transaction t = getSession().getTransaction();

        t.begin();

       // s.
        
        //s.update(o);

        s.saveOrUpdate(o);
        t.commit();
        //getSession().close();
    }

    /*public void update(Object o) {
        Transaction tr = null;
        Session s = null;
        
        try{
        s.close();
        }catch(org.hibernate.SessionException sess){
            
        }
        s = sessionFactory.openSession();
        tr = s.beginTransaction();  
        
        
        s.update(o);
        
        //s.close();
        
        tr.commit();
        
        
    }*/
    public void delete(Object o) {
        Transaction t = getSession().getTransaction();

        t.begin();

        s.delete(o);

        t.commit();

    }

    /*public void exportSchema() {
        SchemaExport se = new SchemaExport(new Configuration().configure("hibernate.cfg.xml"));
        se.create(true, true);
    }*/
    public static void main(String[] args) {
        SchemaExport se = new SchemaExport(new Configuration().configure());
        se.create(true, true);

        //Paciente p = (Paciente) HibernateUtil.getInstance().recover("Paciente", "nome", "amor" + "or 1 = 1'").get(0);
        //System.out.println(p);
    }
}
