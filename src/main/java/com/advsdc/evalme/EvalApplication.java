package com.advsdc.evalme;

import java.sql.SQLException;
import java.util.ArrayList;

import com.advsdc.evalme.dbconnection.SQLMethods;
import com.advsdc.evalme.dbconnection.SQLQueries;
import com.advsdc.evalme.util.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class EvalApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EvalApplication.class, args);
	}

}
