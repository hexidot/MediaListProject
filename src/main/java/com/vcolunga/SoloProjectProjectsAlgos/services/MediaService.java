package com.vcolunga.SoloProjectProjectsAlgos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcolunga.SoloProjectProjectsAlgos.models.Media;
import com.vcolunga.SoloProjectProjectsAlgos.repositories.MediaRepository;

@Service
public class MediaService {

	@Autowired
	private MediaRepository mediaRepository;
	public Media createMedia(Media newMedia) {
		return mediaRepository.save(newMedia);
	}
}
