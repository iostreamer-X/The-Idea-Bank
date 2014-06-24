package the.idea.bank;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
        checkninit();
		new Thread()
		{
			public void run()
			{
				ProgressBar p=(ProgressBar)findViewById(R.id.progressBar1);
				for(int i=300;i>=0;i--)
				{
					p.setProgress(i);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Intent i=new Intent(getBaseContext(),menupage.class);
				startActivity(i);
				Calendar cal = Calendar.getInstance();
	            cal.add(Calendar.SECOND, 10);
	           
	            Intent intent = new Intent(getBaseContext(),notifservice.class);
	    
	            PendingIntent pintent = PendingIntent.getService(getBaseContext(), 0, intent, 0);
	           
	            AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
	            //for 30 mint 60*60*1000
	            alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
	                         6*60*60*1000, pintent);
				finish();
			}
		}.start();
		}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void checkninit()
	{
		FileInputStream fIn= null;
        InputStreamReader isr = null;
        try
            {
        	fIn = openFileInput("total.dat");
        	Log.d("err","regular");

            }
            catch(IOException e){
            e.printStackTrace(System.err);
            FileOutputStream fOut = null;

            OutputStreamWriter osw = null;

            try{

            fOut = openFileOutput("total.dat", Context.MODE_PRIVATE);

            osw = new OutputStreamWriter(fOut);

            osw.write("0\n");

            osw.close();

            fOut.close();
            Log.d("err","written in the stars");

            }catch(Exception e1){

            e1.printStackTrace(System.err);

            }
            Log.d("err","first_time");
            
        	}
	}

}
