package com.epam.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaProducerConfig {
	
	@Bean
	public NewTopic createEmailTopic() {
		return TopicBuilder.name("email-messages").build();
	}
	
	@Bean
	public NewTopic createReportTopic() {
		return TopicBuilder.name("report-messages").build();
	}

}
