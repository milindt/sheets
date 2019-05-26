package com.milindt.sheets.repository;

import com.milindt.sheets.model.Sharing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharingRepository extends JpaRepository<Sharing, Long> {

}
