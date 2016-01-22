package br.com.guarani.rta.utils;

import java.io.File;
import java.io.IOException;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
			
			@Autowired
			private HttpServletRequest request;

			public String write(){
				return null;
				
			}
}
