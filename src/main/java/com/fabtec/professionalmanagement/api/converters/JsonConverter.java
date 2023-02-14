package com.fabtec.professionalmanagement.api.converters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.fabtec.professionalmanagement.api.model.Professional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class JsonConverter implements Serializable{

	private static final long serialVersionUID = 5592326048368020429L;
	
	public static List<Professional> loadJson(FileReader pathReader){
		
		List<Professional> professionals = new ArrayList<Professional>();
		Type type = new TypeToken<List<Professional>>() {}.getType();
		
		BufferedReader reader = new BufferedReader(pathReader);
		Gson gson = new GsonBuilder().create();
		professionals = gson.fromJson(reader, type);
		
		return professionals;
	}

}
