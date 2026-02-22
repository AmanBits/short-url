package com.neurix.short_url.repository;

import com.neurix.short_url.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlRepository extends JpaRepository<UrlMapping,String> {
}
