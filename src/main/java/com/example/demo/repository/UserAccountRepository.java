    package com.example.demo.repository;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

//import com.example.demo.model.StudentEntity;
import com.example.demo.model.UserAccount;

    @Repository
    public interface UserAccountRepository extends JpaRepository<UserAccount,Long>{
        public UserAccount findByUsername(String uas);
        public UserAccount findByPassword(String pass);
        public java.util.Optional<UserAccount> findByEmail(String email);
    }
