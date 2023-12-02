package com.example.mypack.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.example.mypack.dto.AwsSecrets;
import com.google.gson.Gson;

@Configuration
public class ApplicationConfig {

	@Value("${learning.aws.access_key}")
	private String accessKey;
	
	@Value("${learning.aws.secret_key}")
	private String secretKey;
	
	private Gson gson = new Gson();

	@Bean
	public DataSource dataSource() {
		AwsSecrets secrets = getSecret();
		return DataSourceBuilder.create()
				// .driverClassName("")
				.url("jdbc:" + secrets.getEngine() + "://" + secrets.getHost() + ":" + secrets.getPort() + "/demoDB")
				.username(secrets.getUsername()).password(secrets.getPassword()).build();
	}

	private AwsSecrets getSecret() {
		String secretName = "mylearningsecret";
		String region = "us-east-1";
		
		AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
		        .withRegion(region)
		        .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
		        .build();
		
		GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
		GetSecretValueResult getSecretValueResponse;
		try {
			getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
		} catch (Exception e) {
			throw e;
		}

		String secret = getSecretValueResponse.getSecretString();
		return gson.fromJson(secret, AwsSecrets.class);

	}
}
