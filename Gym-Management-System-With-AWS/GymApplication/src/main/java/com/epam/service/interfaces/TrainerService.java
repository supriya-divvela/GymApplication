package com.epam.service.interfaces;

import com.epam.dto.Credentials;
import com.epam.dto.request.TrainerRegistrationRequest;
import com.epam.dto.request.UpdateTrainerRequest;
import com.epam.dto.response.TrainerProfileResponse;
import com.epam.dto.response.UpdatedTrainerResponse;

public interface TrainerService {

	TrainerProfileResponse getTrainerProfile(String username);

	Credentials trainerRegistration(TrainerRegistrationRequest trainerRegistrationRequest);

	UpdatedTrainerResponse updateTrainerProfile(UpdateTrainerRequest updateTrainerRequest);
}
