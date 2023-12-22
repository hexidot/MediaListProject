package com.vcolunga.SoloProjectProjectsAlgos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcolunga.SoloProjectProjectsAlgos.models.MediaList;
import com.vcolunga.SoloProjectProjectsAlgos.models.User;
import com.vcolunga.SoloProjectProjectsAlgos.repositories.MediaListRepository;

@Service
public class MediaListService {

	@Autowired
	private MediaListRepository mediaListRepository;
	
	public MediaList createMediaList(MediaList newList) {
		return mediaListRepository.save(newList);
	}
	
	public MediaList findList(Long id) {
		Optional<MediaList> optionalList = mediaListRepository.findById(id);
		return optionalList.isPresent() ? optionalList.get() : null;
	}
	
	public User findOppositeUser(MediaList list, User currentUser) {
		if(list.getUsers().get(0) == currentUser) {
			return list.getUsers().get(1);
		}
		return list.getUsers().get(0);
	}
}
