package com.bolster.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchemaConfig {

	@Autowired
	private DataSource dataSource;

	public void addSchema(String tenant) {
		try {
			Connection conn = this.dataSource.getConnection();
			if (conn != null)
				this.dataSource.getConnection().createStatement().execute("CREATE SCHEMA IF NOT EXISTS " + tenant + " AUTHORIZATION roster");
			
		} catch (SQLException e) {
			System.err.println("Unable to create schema " + tenant);
		}
	}
}
