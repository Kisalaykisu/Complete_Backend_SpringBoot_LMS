package com.example.complete_backend_springboot_lms.repository;

import com.example.complete_backend_springboot_lms.entity.HiredCandidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HiredRepository extends MongoRepository<HiredCandidate,Integer> {

}
