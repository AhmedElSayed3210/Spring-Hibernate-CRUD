package com.springdemo.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {

		Session currentSession=sessionFactory.getCurrentSession();
		
		Query<Customer> q=currentSession.createQuery("from Customer order by first_name",Customer.class);
		
		List<Customer> customerList=q.getResultList();
		
		return customerList;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

Session currentSession=sessionFactory.getCurrentSession();

currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int customerId) {

		Session currentSession=sessionFactory.getCurrentSession();
		
		Customer customer=currentSession.get(Customer.class, customerId);
		return customer;
	}

	@Override
	public void deleteCustomer(int customerId) {
		Session currentSession=sessionFactory.getCurrentSession();
		
		Customer customer=currentSession.get(Customer.class, customerId);
		
		currentSession.delete(customer);
		
		
	}

}
