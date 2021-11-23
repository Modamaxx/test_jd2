package com.example.demo.dao.api;

import com.example.demo.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfileDao extends JpaRepository<Profile,Long > {
}
