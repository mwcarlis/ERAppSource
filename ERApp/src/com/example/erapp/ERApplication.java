package com.example.erapp;

import java.util.ArrayList;

import android.app.Application;
import android.content.SharedPreferences;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;


// Androids "Application" class allows us to create singleton objects to exist in their own lifecycle
//			 	behind Activities.  Use Application to access/organize/control application global data/info.
public class ERApplication extends Application {
	
	private static SharedPreferences preferences;
	private static String Application_ID="aYOHfCM2NACDPzlyze29zkwDPEyZiymsSbSFpFLg";
	private static String Client_Key="Ljp9v7vaLOM7viK9f9o7Kw2EA6IsoJOwBlww9MbA";
	

	
	@Override
	public void onCreate(){
		super.onCreate();
		
		ParseObject.registerSubclass(Expense.class);
		Parse.initialize(this, Application_ID, Client_Key);
		
		//ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
		//optionally enable public read access.
		//defaultACL.setPublicReadAccess(false);
		ParseACL.setDefaultACL(defaultACL, true);
	} // end onCreate()
	
}/// END ERApplication

