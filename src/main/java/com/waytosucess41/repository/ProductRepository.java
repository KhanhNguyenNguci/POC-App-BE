package com.waytosucess41.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waytosucess41.entity.IProduct;

@Repository
public interface ProductRepository extends JpaRepository<IProduct, Integer>{

}
