package the.idea.bank;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class notifservice extends Service {   

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


	@Override
    public void onCreate() {
    	
        
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Y u did dis? ;_;", Toast.LENGTH_LONG).show();
    }

    @SuppressLint("NewApi")
	@Override
    public void onStart(Intent intent, int startid) {
    	Integer tot=gettot();
        String num=tot.toString();
        String dis = null;
        if(tot==0)
        {
        	dis="Cogito Ergo Sum";
        }
        else if(tot==1)
        	dis="1 idea in your account and this one might change your life as well as the world.";
    	else if(tot>1)
        	dis=num+" ideas in your account and this one might change your life as well as the world.";
    	// TODO Auto-generated method stub
    	Intent i=new Intent(this,randomread.class);
    	PendingIntent pIntent = PendingIntent.getActivity(this.getBaseContext(),0,i,0);
        Notification n  = new NotificationCompat.Builder(this)
        .setContentTitle("Go Change the world.")
        .setContentText(dis)
        .setSmallIcon(R.drawable.ic_launcher)
        .setContentIntent(pIntent)
        .setAutoCancel(true)
        .build();
        NotificationManager notif = (NotificationManager) 
    		    getSystemService(NOTIFICATION_SERVICE); 
        
        notif.notify(0, n); 	   
    }
    
    private int gettot()
	{
		FileInputStream fis = null;
        String data = null;
        int tou=0;
        try{

        	fis = openFileInput("total.dat");
            DataInputStream dataIO = new DataInputStream(fis);
            data=dataIO.readLine();
            dataIO.close();
		tou=Integer.parseInt(data);	

		}catch(IOException e){

		e.printStackTrace(System.err);
        
		}
	return tou;
	}
}
