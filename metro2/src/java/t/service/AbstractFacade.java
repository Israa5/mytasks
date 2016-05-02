/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import t.Metrostation;
import static t.Metrostation_.id;
import static t.Metrostation_.xs;
import static t.Metrostation_.ys;

/**
 *
 * @author pro
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    //https://www.geodatasource.com/developers/java
    class resultArray {

        int idSation;
        double result;

        public void setIdSation(int idSation) {
            this.idSation = idSation;
        }

        public void setResult(double result) {
            this.result = result;
        }

        public int getIdSation() {
            return idSation;
        }

        public double getResult() {
            return result;
        }
    }

    class DistanceCalculator {

        public void result(/*float latuser,float longuser*/) {
            int ids = 0;
            double x = 0, y = 0;
            double xuser = 30.023002, yuser = 31.211302;
            //to min arraylist
            ArrayList<resultArray> r1 = new ArrayList<resultArray>();
            double result = 0;
            //for big arraylist to store
            Metrostation station = new Metrostation();
            ArrayList<Metrostation> m = new ArrayList<Metrostation>();
            int index = 0;

            //1-read data and get it in var
            Query q1 = getEntityManager().createNativeQuery("select id,xs,ys from metrostation;");
            m = (ArrayList<Metrostation>) q1.getResultList();

            m.add(new Metrostation(id, xs, ys));

            for (Metrostation item : m) {
                ids = item.getId();
                x = item.getXs();
                y = item.getYs();
            }
            //System.out.println(distance(xs, ys, xuser, yuser, "M") + " Miles\n");

            result = distance(x, y, xuser, yuser);
            //2-add result in array and get it to sort min
            resultArray e = new resultArray();
            e.setIdSation(ids);
            e.setResult(result);
            r1.add(0, e);

            /*for(int i=0,i<r1.size(),i++){
             r1.add(i,e);
             }*/
            //3-get data from 
            //System.out.println(distance(xs, ys, xuser, yuser, "N") + " Nautical Miles\n");
        }

        private double distance(double lat1, double lon1, double lat2, double lon2) {

            double theta = lon1 - lon2;
            double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
            dist = Math.acos(dist);
            dist = rad2deg(dist);
            dist = dist * 60 * 1.1515;
            //if (unit == "K") {
            dist = dist * 1.609344;
            /*} else if (unit == "N") {
             dist = dist * 0.8684;
             }*/

            return (dist);
        }

        /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
        /*::	This function converts decimal degrees to radians						 :*/
        /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
        private double deg2rad(double deg) {
            return (deg * Math.PI / 180.0);
        }

        /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
        /*::	This function converts radians to decimal degrees						 :*/
        /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
        private double rad2deg(double rad) {
            return (rad * 180 / Math.PI);
        }
    }

}
