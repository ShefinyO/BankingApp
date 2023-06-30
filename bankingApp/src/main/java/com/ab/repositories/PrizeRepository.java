package com.ab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ab.entities.Prize;


@Repository
public interface PrizeRepository extends JpaRepository<Prize, Integer>{

}
