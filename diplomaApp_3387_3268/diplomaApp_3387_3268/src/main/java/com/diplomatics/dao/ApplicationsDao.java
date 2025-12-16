package com.diplomatics.dao;

import com.diplomatics.entity.Applications;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ApplicationsDao extends CrudRepository<Applications, Integer> {

    @Query(value = "SELECT * FROM applications WHERE diploma_id = ?1 and student_id = ?2", nativeQuery = true)
    Applications findApplicationAgain(int diploma_id, int student_id);

    @Query(value = "SELECT * FROM diploma INNER JOIN applications ON diploma.diploma_id = applications.diploma_id AND diploma.user =?1 GROUP BY diploma", nativeQuery = true)
    List<Applications> findApplicationStud(String user);

    @Query(value = "SELECT * FROM applications WHERE student_id = ?1", nativeQuery = true)
    List<Applications> findApplication(int student_id);

    @Query(value = "SELECT * FROM applications WHERE student_id != ?1 AND diploma_id=?2", nativeQuery = true)
    List<Applications> applyUsers(int student_id, int diploma_id);

    @Query(value = "SELECT * FROM applications WHERE student_id = ?1 AND diploma_id= ?2", nativeQuery = true)
    Applications applyFinalUser(int student_id, int diploma_id);

    @Query(value = "SELECT * FROM applications WHERE student_id = ?1 ", nativeQuery = true)
    List<Applications> dontApplyUser(int student_id);

    @Query(value = "SELECT * FROM applications WHERE student_id = ?1 AND selected='Επιλεχθήκατε' limit 1", nativeQuery = true)
    Applications findUsersApp(int student_id);

    @Query(value = "SELECT * FROM applications WHERE diploma_id = ?1 ", nativeQuery = true)
    List<Applications> findByDiploma(int diploma_id);
}
