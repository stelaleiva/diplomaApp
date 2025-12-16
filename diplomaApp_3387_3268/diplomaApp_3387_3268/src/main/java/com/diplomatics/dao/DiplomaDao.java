package com.diplomatics.dao;

import com.diplomatics.entity.Diploma;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiplomaDao extends CrudRepository<Diploma, Integer> {

    @Query(value = "SELECT * FROM diploma WHERE user_id = ?1 ", nativeQuery = true)
    List<Diploma> findDiplomas(int user_id);

    @Query(value = "SELECT * FROM diploma WHERE available = 'Ναι' ", nativeQuery = true)
    List<Diploma> findDiplomasAvailable();

    @Query(value = "SELECT * FROM diploma WHERE title = ?1 ", nativeQuery = true)
    Diploma findDiploma(String title);


}
