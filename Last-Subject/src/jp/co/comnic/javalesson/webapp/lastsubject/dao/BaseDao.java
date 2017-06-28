package jp.co.comnic.javalesson.webapp.lastsubject.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;






public class BaseDao {

	
	protected EntityManager em = Persistence.createEntityManagerFactory("Last-Subject").createEntityManager();
	protected EntityTransaction tx = em.getTransaction();
	protected CriteriaBuilder builder = em.getCriteriaBuilder();
	
	
//	DBのテーブル内の全オブジェクトが格納されたリストを返す
	protected <T> List<T> findAll(CriteriaQuery<T> query, Root<T> root) {
		return em.createQuery(query.select(root)).getResultList();
	}
	
//	与えられた主キーに対応するオブジェクトを返す
	public <T> T findById(Class<T> entityClass, Serializable id) {
		return em.find(entityClass, id);
	}
	
//	新規データの挿入
	public void insert(Object entity) throws DaoException {

		try {
			tx.begin();
			em.persist(entity);
			tx.commit();
			
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	//既存データを1件削除
	public <T> void remove(Class<T> entityClass, Serializable id) throws DaoException {
		// エンティティ・オブジェクトを取得

	Object entity=em.find(entityClass, id);
	//取得したエンティティを削除
	try {
		tx.begin();
		em.remove(entity);
		tx.commit();
		
	} catch (Exception e) {
		throw new DaoException(e);
	}
	}
	
	//1件のデータのPK以外の情報の更新
	public void update(Object entity) throws DaoException {
		try {
			tx.begin();
			em.merge(entity);
			tx.commit();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}
