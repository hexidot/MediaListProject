package com.vcolunga.SoloProjectProjectsAlgos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vcolunga.SoloProjectProjectsAlgos.models.Media;

@Repository
public interface MediaRepository extends CrudRepository<Media, Long> {

}
