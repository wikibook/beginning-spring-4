package sample.spring.chapter07.bankapp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import sample.spring.chapter07.bankapp.domain.FixedDepositDetails;

@Repository(value = "fixedDepositDao")
public class FixedDepositDaoImpl implements FixedDepositDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public int createFixedDeposit(final FixedDepositDetails fdd) {
		sessionFactory.getCurrentSession().save(fdd);
		return fdd.getFixedDepositId();
	}

	public FixedDepositDetails getFixedDeposit(final int fixedDepositId) {
		String hql = "from FixedDepositDetails as fixedDepositDetails where fixedDepositDetails.fixedDepositId ="
				+ fixedDepositId;
		return (FixedDepositDetails) sessionFactory.getCurrentSession()
				.createQuery(hql).uniqueResult();
	}
}
