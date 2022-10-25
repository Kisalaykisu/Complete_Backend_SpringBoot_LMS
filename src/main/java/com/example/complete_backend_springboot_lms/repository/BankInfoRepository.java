package com.example.complete_backend_springboot_lms.repository;

import com.example.complete_backend_springboot_lms.entity.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankInfoRepository extends MongoRepository<BankInfo, Integer> {
}
