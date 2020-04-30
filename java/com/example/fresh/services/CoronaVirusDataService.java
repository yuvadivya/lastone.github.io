package com.example.fresh.services;

import com.example.fresh.models.LocationStats;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Service
public class CoronaVirusDataService {
    private List<LocationStats> allStats = new ArrayList<>();
    public List<LocationStats> getAllStats() {
        return allStats;
    }
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, ParseException {
        List <LocationStats> newStats = new ArrayList<>();
        URL url = new URL("https://api.covid19india.org/data.json");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //conn.addRequestProperty("Authorization","VYMAT9N1QQ2YGN4G");
        conn.setRequestMethod("GET");
        conn.connect();
        int responsecode = conn.getResponseCode();
        String inline="";
        if(responsecode != 200)
            throw new RuntimeException("HttpResponseCode" +responsecode);
        else{
            Scanner sc = new Scanner(url.openStream());
            while(sc.hasNext())
            {
                inline+=sc.nextLine();
            }
            sc.close();
        }
        System.out.println(inline);
        JSONParser parse = new JSONParser();
        JSONObject jobj = (JSONObject)parse.parse(inline);
        System.out.println(jobj);
        JSONArray jsonarr_1 = (JSONArray) jobj.get("statewise");
        System.out.println(jsonarr_1);
        for(int i=0;i<jsonarr_1.size();i++)
        {
            JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
            LocationStats locationStat = new LocationStats();
            locationStat.setState((String) jsonobj_1.get("state"));
            locationStat.setConfirmed(Integer.parseInt((String) jsonobj_1.get("confirmed")));
            locationStat.setRecovered(Integer.parseInt((String) jsonobj_1.get("recovered")));
            locationStat.setDeceased(Integer.parseInt((String)jsonobj_1.get("deaths")));
            locationStat.setActive(Integer.parseInt((String)jsonobj_1.get("active")));
            System.out.println(locationStat);
            newStats.add(locationStat);
        }
        this.allStats=newStats;
    }

}
