package com.wolken.wolkenapp.service;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wolken.wolkenapp.dao.TeamDAO;
import com.wolken.wolkenapp.dto.TeamDTO;

@Component
public class TeamServiceImpl implements TeamService {
	@Autowired
	TeamDAO dao;
	Logger logger = Logger.getLogger("TeamServiceImpl");

	public String validateAndSave(TeamDTO teamDTO) {
		// TODO Auto-generated method stub
		try {
			logger.info("inside validate and save");
			if (teamDTO.getTeamId() > 0 ) {
				if (teamDTO.getTeamName() != null && teamDTO.getTeamName()=="") {
					if (teamDTO.getTeamGround() != null && teamDTO.getTeamGround()=="") {
						if (teamDTO.getTeamCaptain() != null && teamDTO.getTeamCaptain()=="") {
							logger.info("inside null check inside validate and save ");
							return dao.save(teamDTO);

						} else {
							logger.error("team captain is null value inside validate and save");
						}
					} else {
						logger.error("team ground is null value  inside validate and save");
					}
				} else {
					logger.error("team name is null value inside validate and save ");
				}
			} else {
				logger.error("team id is null inside validate and save");
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String validateAndDelete(String teamName) {
		// TODO Auto-generated method stub
		try {
			logger.info("inside validate and delete ");
			if (teamName != null&& teamName=="") {
				logger.info("inside null check validate and delete ");
				return dao.delete(teamName);

			}
		} catch (NullPointerException e) {
			logger.info("inside null exception inside validate and delete");
			e.printStackTrace();
		}
		return null;
	}

	public String validateAndUpdate(String teamName, String teamCaptain) {
		// TODO Auto-generated method stub
		logger.info("inside validate and update ");
		if (teamName != null && teamName=="") {
			if (teamCaptain != null && teamCaptain=="") {
				logger.info("inside null check validate and update ");
				return dao.updateCaptainByTeamName(teamName, teamCaptain);

			} else {
				logger.error("team captain value inside update is null");
			}

		}
		return null;
	}

	public List<TeamDTO> getAll() {
		// TODO Auto-generated method stub
		logger.info("inside getall method in service ");
		return dao.getAll();
	}

}
