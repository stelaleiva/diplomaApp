package com.diplomatics.dao;

import com.diplomatics.entity.Supervised;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupervisedDao extends CrudRepository<Supervised, Integer> {

    @Query(value = "SELECT * FROM supervised WHERE student_id = ?1", nativeQuery = true)
    List<Supervised> findSupervised(int student_id);

    @Query(value = "SELECT * FROM supervised WHERE prof_id = ?1", nativeQuery = true)
    List<Supervised> findSupervisedStu(int prof_id);

    @Query(value = "SELECT * FROM supervised WHERE super_id = ?1", nativeQuery = true)
    Supervised findSupervisedById(int super_id);
}
