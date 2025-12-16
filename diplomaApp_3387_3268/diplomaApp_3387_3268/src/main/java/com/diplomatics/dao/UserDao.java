package com.diplomatics.dao;

import com.diplomatics.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    Optional<User> findByUserName(String username);

    @Query(value = "SELECT * FROM users INNER JOIN applications ON users.id = applications.student_id where applications.diploma_id =?1 AND applications.selected='Σε Αναμονή'", nativeQuery = true)
    List<User> findAppStudents(int app_id);

    @Query(value = "SELECT * FROM users INNER JOIN applications ON users.id = applications.student_id where applications.diploma_id =?1", nativeQuery = true)
    List<User> getStudentsReady(int app_id);

    @Query(value = "SELECT * FROM users INNER JOIN applications ON users.id = applications.student_id where applications.diploma_id =?1 AND applications.selected='Σε Αναμονή' AND avg_grade > ?2 AND remain_courses< ?3", nativeQuery = true)
    List<User> getAppStudentsSets(int app_id, BigDecimal grade, int courses);

    @Query(value = "SELECT * FROM users WHERE user_name = ?1", nativeQuery = true)
    User findExistUser(String user_name);

    @Query(value = "SELECT * FROM users WHERE full_name = ?1", nativeQuery = true)
    User findExistUserFullname(String full_name);

}

