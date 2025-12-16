package com.diplomatics.services;

import com.diplomatics.entity.Supervised;
import com.diplomatics.dao.SupervisedDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupervisedService {

    @Autowired
    private SupervisedDao supervisedRepository=new SupervisedDao() {
        @Override
        public List<Supervised> findSupervised(int student_id) {
            return null;
        }

        @Override
        public List<Supervised> findSupervisedStu(int prof_id) {
            return null;
        }

        @Override
        public Supervised findSupervisedById(int super_id) {
            return null;
        }

        @Override
        public <S extends Supervised> S save(S s) {
            return null;
        }

        @Override
        public <S extends Supervised> Iterable<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public Optional<Supervised> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public Iterable<Supervised> findAll() {
            return null;
        }

        @Override
        public Iterable<Supervised> findAllById(Iterable<Integer> iterable) {
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
        public void delete(Supervised supervised) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> iterable) {

        }

        @Override
        public void deleteAll(Iterable<? extends Supervised> iterable) {

        }

        @Override
        public void deleteAll() {

        }
    };

    public List<Supervised> findSupervised(Integer student_id){
        return supervisedRepository.findSupervised(student_id);
    }
    public List<Supervised> findSupervisedStu(Integer prof_id){
        return supervisedRepository.findSupervisedStu(prof_id);
    }

    public void save(Supervised supervised) {
        supervisedRepository.save(supervised);
    }

   public Supervised findSupervisedById(Integer super_id){ return supervisedRepository.findSupervisedById(super_id);}
}
