package com.epam.service.impl;

import com.epam.dto.EmailDto;
import com.epam.dto.TrainingSummaryDto;
import com.epam.service.interfaces.CommunicationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunicationServiceImpl implements CommunicationService {

    @Autowired
    private SqsTemplate sqsTemplate;
    public void sendEmail(EmailDto emailDto){
        sqsTemplate.send(to -> {
            try {
                to.queue("email-queue").payload(new ObjectMapper().writeValueAsString(emailDto));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void sendTraining(TrainingSummaryDto trainingSummaryDto) {
        sqsTemplate.send(to -> {
            try {
                to.queue("reports-queue").payload(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(trainingSummaryDto));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
