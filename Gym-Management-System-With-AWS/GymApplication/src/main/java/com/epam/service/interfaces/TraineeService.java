package com.epam.service.interfaces;

import java.util.List;

import com.epam.dto.Credentials;
import com.epam.dto.TrainerDto;
import com.epam.dto.request.TraineeRegistrationRequest;
import com.epam.dto.request.UpdateTraineeRequest;
import com.epam.dto.request.UpdateTraineesTrainerListRequest;
import com.epam.dto.response.TraineeProfileResponse;
import com.epam.dto.response.UpdatedTraineeResponse;

public interface TraineeService {

	TraineeProfileResponse getTraineeProfile(String username);

	Credentials traineeRegistration(TraineeRegistrationRequest traineeRegistrationRequest);

	void deleteTraineeProfile(String traineeUsername);

	UpdatedTraineeResponse updateTraineeProfile(UpdateTraineeRequest updateTraineeRequest);

	List<TrainerDto> updateTraineesTrainers(UpdateTraineesTrainerListRequest updateTraineesTrainerListRequest);

	public List<TrainerDto> getNonActiveTrainersOnTrainee(String username);
}
