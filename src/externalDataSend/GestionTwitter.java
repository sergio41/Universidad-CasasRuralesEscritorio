package externalDataSend;

import java.util.List;
import java.util.Vector;

import twitter4j.*;
import twitter4j.conf.*;

public class GestionTwitter {
	private static Twitter twitter;
		
	private static void inicializarTwitter(){
		/*ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey("xEIAPcAcPKSnUj3cQVmkqQ");
		cb.setOAuthConsumerSecret("XwLA9bC2RwW5HMMVF1Qx7icMiu9MsoXNAmEEXDM3ng");
		cb.setOAuthAccessToken("1318353908-WXP45IIedFVt8iGtBajcEdXYw013vxxQpyk2gBh");
		cb.setOAuthAccessTokenSecret("MAtTy95rsDhbAChmALQglVtwIayNjDdFMBgD156w");
		twitter = new TwitterFactory(cb.build()).getInstance();*/
	}
	
	public static void enviarTweet(String s){
		/*if (twitter == null) inicializarTwitter();
		Status status;
		try {
			status = twitter.updateStatus(s);
			System.out.println("Successfully updated the status to [" + status.getText() + "].");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public static Vector<String> getTodosTweets(){
		//if (twitter == null) inicializarTwitter();
		Vector<String> vectorTweets = new Vector<String>();
		/*try {
			List<Status> statuses = twitter.getHomeTimeline();
			for (Status status : statuses) vectorTweets.add(status.getText());
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //System.out.println("Showing home timeline.");
	    //for (Status status1 : statuses) {System.out.println(status1.getUser().getName() + ":" + status1.getText());
		*/return vectorTweets;
	}
	
	public static String getUltimoTweet(){
		/*if (twitter == null) inicializarTwitter();
		try {
			List<Status> statuses = twitter.getHomeTimeline();
			return statuses.get(0).getText();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return "Error";
	}
}
