package com.diplomatics.services;

import com.diplomatics.dao.UserDao;
import com.diplomatics.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired private UserDao repo=new UserDao() {
        @Override
        public Optional<User> findByUserName(String username) {
            return Optional.empty();
        }

        @Query(value = "SELECT * FROM users WHERE user_name = ?1", nativeQuery = true)

        @Override
        public List<User> findAppStudents(int app_id) {
            return null;
        }

        @Override
        public List<User> getStudentsReady(int app_id) {
            return null;
        }

        @Override
        public List<User> getAppStudentsSets(int app_id, BigDecimal grade, int courses) {
            return null;
        }

        @Override
        public User findExistUser(String user_name) {
            return null;
        }

        @Override
        public User findExistUserFullname(String full_name) {
            return null;
        }

        @Override
        public <S extends User> S save(S s) {
            return null;
        }

        @Override
        public <S extends User> Iterable<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public Optional<User> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public Iterable<User> findAll() {
            return null;
        }

        @Override
        public Iterable<User> findAllById(Iterable<Integer> iterable) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(User user) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> iterable) {

        }

        @Override
        public void deleteAll(Iterable<? extends User> iterable) {

        }

        @Override
        public void deleteAll() {

        }
    };

    public void save(User user) { repo.save(user);  }

    public User get(Integer id) { Optional<User> result = repo.findById(id);  return result.get();  }

    public  List<User> getAppStudents(Integer id) { return repo.findAppStudents(id); }

    public  List<User> getAppStudentsSets(Integer id, BigDecimal grade, Integer courses) { return repo.getAppStudentsSets(id, grade, courses); }

    public  List<User> getStudentsReady(Integer id) { return repo.getStudentsReady(id); }

    public User findExistUser(String username) { return repo.findExistUser(username);}

    public User findExistUserFullname(String full_name) { return repo.findExistUserFullname(full_name);}
}
