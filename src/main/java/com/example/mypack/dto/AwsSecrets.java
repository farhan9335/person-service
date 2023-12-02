package com.example.mypack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AwsSecrets {

	private String username;
	private String password;
	private String host;
	private String engine;
	private String port;
	private String dbInstanceIdentifier;
	
}
