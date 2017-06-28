package jp.co.comnic.javalesson.webapp.lastsubject.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import jp.co.comnic.javalesson.webapp.lastsubject.model.Account;


public class AccountDao extends BaseDao{
	
	public AccountDao() throws DaoException {}
	private CriteriaQuery<Account> query = builder.createQuery(Account.class);
	private Root<Account> root = query.from(Account.class);
	
	
	public List<Account> findAll() {
		return super.findAll(query, root);
	}
	
	public Account findById(Integer id) {
		return super.findById(Account.class, id);
	}
	
	public Account loginAuthenticate(String email , String password){
		Account account = null;
		
		try {
			// Criteria APIを使用して以下SQLを生成する
			// SELECT * FROM ACCOUNT WHERE email = [email] AND password = [password]
			query.select(root)
				 .where(builder.equal(root.get("email"), email), 
						builder.equal(root.get("password"), password));
			
			// SQLを実行して結果を単一のエンティティ・オブジェクトとして取得
			account = em.createQuery(query).getSingleResult();
			
		} catch (NoResultException e) {
			// getSingleResultメソッドは結果がなかった場合にNoResultExceptionをthrow
			// するため、この例外処理は不要
		}
		
		return account;
	}
	
}
