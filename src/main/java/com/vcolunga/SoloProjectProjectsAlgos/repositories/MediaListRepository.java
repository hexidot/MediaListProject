package com.vcolunga.SoloProjectProjectsAlgos.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vcolunga.SoloProjectProjectsAlgos.models.MediaList;

@Repository
public interface MediaListRepository extends CrudRepository<MediaList, Long> {
}
