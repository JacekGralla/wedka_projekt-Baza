package com.example.luptowski.wedka;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;


public class HandleXML extends AsyncTask <String, Void, String> {
    private String id = "id";
    private String date = "date";
    private String dayName = "dayName";
    private String sunrise = "sunrise";
    private String sunset = "sunset";
    private String moonState = "moonState";
    private String condition = "condition";
    private String urlString = null;

    public volatile boolean parsingComplete = true;
    URL url;
    int event;
    String text = null;

    public HandleXML(String url) {
        this.urlString = url;
    }

    public String getID() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDayName() {
        return dayName;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getMoonState() {
        return moonState;
    }

    public String getCondition() {
        return condition;
    }
    @Override
    protected String doInBackground(String ...h) {
        try {
            url = new URL("http://wedkarze.cba.pl/data.xml/xml");
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser myParser = factory.newPullParser();
            event = myParser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();

                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (name.equals("id")) {
                            id = text;
                        } else if (name.equals("date")) {
                            date = myParser.getAttributeValue(null, "value");
                        } else if (name.equals("dayName")) {
                            dayName = myParser.getAttributeValue(null, "value");
                        } else if (name.equals("sunrise")) {
                            sunrise = myParser.getAttributeValue(null, "value");
                        } else if (name.equals("sunset")) {
                            sunset = myParser.getAttributeValue(null, "value");
                        } else if (name.equals("moonState")) {
                            moonState = myParser.getAttributeValue(null, "value");
                        } else if (name.equals("condition")) {
                            condition = myParser.getAttributeValue(null, "value");
                        } else {
                        }
                        break;
                }
                event = myParser.next();
            }
            parsingComplete = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlString;
    }
}



