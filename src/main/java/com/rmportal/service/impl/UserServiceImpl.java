package com.rmportal.service.impl;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmportal.model.User;
import com.rmportal.repositories.UserRepository;
import com.rmportal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public User validate(User user) {
		SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
		System.out.println("sessionFactory : " + sessionFactory.getStatistics());
		System.out.println("Entity fetch count :" + sessionFactory.getStatistics().getEntityFetchCount());
        System.out.println("Second level cache hit count : "+ sessionFactory.getStatistics().getSecondLevelCacheHitCount());
        System.out.println("Second level cache put count : " + sessionFactory.getStatistics().getSecondLevelCachePutCount());
        System.out.println("Second level cache miss count : " + sessionFactory.getStatistics().getSecondLevelCacheMissCount());
        userRepository.validate(user.getUsername(), user.getPassword());
        System.out.println("sessionFactory : " + sessionFactory.getStatistics());
		System.out.println("Entity fetch count :" + sessionFactory.getStatistics().getEntityFetchCount());
        System.out.println("Second level cache hit count : "+ sessionFactory.getStatistics().getSecondLevelCacheHitCount());
        System.out.println("Second level cache put count : " + sessionFactory.getStatistics().getSecondLevelCachePutCount());
        System.out.println("Second level cache miss count : " + sessionFactory.getStatistics().getSecondLevelCacheMissCount());
        userRepository.validate(user.getUsername(), user.getPassword());
        System.out.println("sessionFactory : " + sessionFactory.getStatistics());
		System.out.println("Entity fetch count :" + sessionFactory.getStatistics().getEntityFetchCount());
        System.out.println("Second level cache hit count : "+ sessionFactory.getStatistics().getSecondLevelCacheHitCount());
        System.out.println("Second level cache put count : " + sessionFactory.getStatistics().getSecondLevelCachePutCount());
        System.out.println("Second level cache miss count : " + sessionFactory.getStatistics().getSecondLevelCacheMissCount());
        userRepository.validate(user.getUsername(), user.getPassword());
        System.out.println("sessionFactory : " + sessionFactory.getStatistics());
		System.out.println("Entity fetch count :" + sessionFactory.getStatistics().getEntityFetchCount());
        System.out.println("Second level cache hit count : "+ sessionFactory.getStatistics().getSecondLevelCacheHitCount());
        System.out.println("Second level cache put count : " + sessionFactory.getStatistics().getSecondLevelCachePutCount());
        System.out.println("Second level cache miss count : " + sessionFactory.getStatistics().getSecondLevelCacheMissCount());
        
		return userRepository.validate(user.getUsername(), user.getPassword());

	}

	@Override
	public boolean register(User user) {
		try {
			userRepository.save(user);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

}
