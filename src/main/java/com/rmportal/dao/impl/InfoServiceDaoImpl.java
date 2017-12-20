package com.rmportal.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmportal.dao.InfoServiceDao;
import com.rmportal.model.PortalInfo;
import com.rmportal.model.PortalMappingInfo;

@Service
public class InfoServiceDaoImpl implements InfoServiceDao {

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<PortalMappingInfo> getDetails(Long[] localities, Integer price, Long acId, Long gender, Long[] rooms) {
		final StringBuilder hql= new StringBuilder();
		final Map<String, Object> paramaters= new HashMap<String, Object>();
		hql.append("SELECT I FROM PortalMappingInfo I ");
		hql.append(" LEFT JOIN I.roomType RT ");
		if(null!=localities && localities.length>0){
			hql.append(" LEFT JOIN I.address A WHERE A.location.id IN (:location)");
			paramaters.put("location", Arrays.asList(localities));
		}
		if(null!=price && price > 0){
			if(paramaters.isEmpty()){
				hql.append(" WHERE ");
			}else{
				hql.append(" AND ");
			}
			hql.append("I.rent<=:rent");
			paramaters.put("rent", price);
		}
		if(null!=acId && acId>0){
			if(paramaters.isEmpty()){
				hql.append(" WHERE ");
			}else{
				hql.append(" AND ");
			}
			hql.append("I.condition.id=:condition");
			paramaters.put("condition", acId);
		}
		if(null!=gender && gender>0){
			if(paramaters.isEmpty()){
				hql.append(" WHERE ");
			}else{
				hql.append(" AND ");
			}
			hql.append("I.gender.id=:gender");
			paramaters.put("gender", gender);
		}
		
		
		if(null!=rooms && rooms.length>0){
			if(paramaters.isEmpty()){
				hql.append(" WHERE ");
			}else{
				hql.append(" AND ");
			}
			hql.append("I.roomType.id in (:roomType)");
			paramaters.put("roomType", Arrays.asList(rooms));
		}
//		if(paramaters.isEmpty()){
//			hql.append(" WHERE ");
//			hql.append("RT.value<>I.occupied");
//		}else{
//			hql.append(" AND ");
//			hql.append("RT.value<>I.occupied");
//		}
		final Query query= entityManager.createQuery(hql.toString());
		paramaters.forEach((key,value)->{
			System.out.println(key);
			System.out.println(value);
			query.setParameter(key, value);
			
		});
		if(paramaters.isEmpty()){
			query.setMaxResults(20);
		}
		return query.getResultList();
	}
	
	public int create(String sql) {
		final Query query= entityManager.createNativeQuery(sql);
		return query.executeUpdate();
	}

	@Override
	public List<PortalInfo> getDetails(String type) {
		return null;
	}
}
