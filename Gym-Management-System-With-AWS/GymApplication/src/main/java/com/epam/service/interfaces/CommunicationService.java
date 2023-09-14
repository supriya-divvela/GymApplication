package com.epam.service.interfaces;

import com.epam.dto.EmailDto;
import com.epam.dto.TrainingSummaryDto;

public interface CommunicationService{
    public void sendEmail(EmailDto emailDto);
    public void sendTraining(TrainingSummaryDto trainingSummaryDto);
}
