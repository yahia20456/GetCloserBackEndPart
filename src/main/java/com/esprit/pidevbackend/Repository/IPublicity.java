package com.esprit.pidevbackend.Repository;

import com.esprit.pidevbackend.Domain.Publicity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPublicity extends JpaRepository<Publicity, Long> {


}
