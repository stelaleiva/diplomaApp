package com.diplomatics.services;

import com.diplomatics.dao.DiplomaDao;
import com.diplomatics.entity.Diploma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiplomaService {

    @Autowired
    private DiplomaDao repo=new DiplomaDao() {
        @Override
        public List<Diploma> findDiplomas(int user_id) {
            return null;
        }

        @Override
        public List<Diploma> findDiplomasAvailable() {
            return null;
        }

        @Override
        public Diploma findDiploma(String title) {
            return null;
        }

        @Override
        public <S extends Diploma> S save(S s) {
            return null;
        }

        @Override
        public <S extends Diploma> Iterable<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public Optional<Diploma> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public Iterable<Diploma> findAll() {
            return null;
        }

        @Override
        public Iterable<Diploma> findAllById(Iterable<Integer> iterable) {
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
        public void delete(Diploma diploma) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> iterable) {

        }

        @Override
        public void deleteAll(Iterable<? extends Diploma> iterable) {

        }

        @Override
        public void deleteAll() {

        }
    };

    public void save(Diploma diploma) {
        repo.save(diploma);
    }

    public List<Diploma> findDiplomasAvailable() { return repo.findDiplomasAvailable(); }

    public Diploma getDiploma(Integer id) {
        Optional<Diploma> result = repo.findById(id);
        return result.get();

    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
    public Diploma findDiploma(String title){ return repo.findDiploma(title);}

    public List<Diploma> findDiplomas(Integer user_id){ return repo.findDiplomas(user_id);}

}
