    package com.example.Notes.repository;

    import com.example.Notes.models.User;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.Optional;

    public interface UserRepo extends JpaRepository<User,Long> {
        Optional<User> findByName(String Name);
    }
