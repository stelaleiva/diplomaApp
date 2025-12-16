package com.diplomatics.services;

import com.diplomatics.entity.Applications;
import com.diplomatics.dao.ApplicationsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationsService {

    @Autowired
    private ApplicationsDao repo = new ApplicationsDao() {
        @Override
        public Applications findApplicationAgain(int diploma_id, int student_id) {
            return null;
        }

        @Override
        public List<Applications> findApplicationStud(String user) {
            return null;
        }

        @Override
        public List<Applications> findApplication(int student_id) {
            return null;
        }

        @Override
        public List<Applications> applyUsers(int student_id, int diploma_id) {
            return null;
        }

        @Override
        public Applications applyFinalUser(int student_id, int diploma_id) {
            return null;
        }

        @Override
        public List<Applications> dontApplyUser(int student_id) {
            return null;
        }

        @Override
        public Applications findUsersApp(int student_id) {
            return null;
        }

        @Override
        public List<Applications> findByDiploma(int diploma_id) {
            return null;
        }

        @Override
        public <S extends Applications> S save(S s) {
            return null;
        }

        @Override
        public <S extends Applications> Iterable<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public Optional<Applications> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public Iterable<Applications> findAll() {
            return null;
        }

        @Override
        public Iterable<Applications> findAllById(Iterable<Integer> iterable) {
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
        public void delete(Applications applications) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> iterable) {

        }

        @Override
        public void deleteAll(Iterable<? extends Applications> iterable) {

        }

        @Override
        public void deleteAll() {

        }
    };

    public void save(Applications application) {
        repo.save(application);
    }

    public void delete(Applications application) { repo.delete(application); }

    public Applications getApplicationAgain(Integer diploma_id, Integer student_id) {
        return repo.findApplicationAgain(diploma_id, student_id);
    }

    public List<Applications> getApplicationStud(String user){
        return repo.findApplicationStud(user);
    }

    public List<Applications> getApplication(Integer student_id) {
        return repo.findApplication(student_id);
    }

    public List<Applications> applyUsers(Integer student_id, Integer diploma_id) { return repo.applyUsers(student_id, diploma_id); }

    public Applications applyFinalUser(Integer student_id, Integer diploma_id) { return repo.applyFinalUser(student_id, diploma_id); }

    public List<Applications> dontApplyUser(Integer student_id) { return repo.dontApplyUser(student_id); }

    public Applications findUsersApp(Integer student_id) { return repo.findUsersApp(student_id); }

    public List<Applications> findByDiploma(Integer diploma_id) { return repo.findByDiploma(diploma_id); }
}
