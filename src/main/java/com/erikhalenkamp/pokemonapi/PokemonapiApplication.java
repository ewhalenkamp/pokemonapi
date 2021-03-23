package com.erikhalenkamp.pokemonapi;

import java.util.*;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@RestController
public class PokemonapiApplication {
	
	private DataClass data;
	
	public static void main(String[] args) {
		SpringApplication.run(PokemonapiApplication.class, args);
	}
	
	
	@GetMapping("/api/pokemon")
	public String getPokemonContext(@RequestParam(value = "id", defaultValue = "0") String id) 
		throws IOException
	{
		if (this.data == null) {
			this.data = new DataClass();
	        this.data.initBasePokemon();
	        this.data.initBaseAbility();
	        this.data.initBaseMove();
	        this.data.initDexDescriptions();
	        this.data.merge();
		}
        //for (BasePokemon pokemon : data.getCollection())
        	//System.out.println(pokemon.createJSON(data));
        
		String context = "";
		int idint = Integer.parseInt(id);
		//if default id is passed, return a list of all basepokemon objects
		if (idint == 0) {
			for (BasePokemon pokemon : this.data.getCollection()) {
				context += pokemon.createJSON(data) + ", ";
			}
			context = context.substring(0, context.length()-2);
			return context;
		}
		//otherwise, get the specific object
		else {
			return this.data.getCollection().get(idint-1).createJSON(data);
		}
	}

}
