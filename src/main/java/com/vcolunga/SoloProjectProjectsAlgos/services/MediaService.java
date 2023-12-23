package com.vcolunga.SoloProjectProjectsAlgos.services;

import java.util.Optional;

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
	
	public Media findMedia(Long id) {
		Optional<Media> optional = mediaRepository.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}
	
	public void deleteMedia(Long id) {
		mediaRepository.deleteById(id);
	}
	
	public Media updateMedia(Media editedMedia) {
		return mediaRepository.save(editedMedia);
	}
}
