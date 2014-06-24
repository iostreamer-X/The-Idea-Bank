package the.idea.bank;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class randomread extends Activity
{   String fname;
    String data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rr);
		
			int tot=gettot();
		if(tot>0)
		{
			Toast.makeText(getApplicationContext(), "Tap on your idea to edit it.",Toast.LENGTH_SHORT).show();
			
			Random r=new Random();
			Integer rt=r.nextInt(tot);
			rt=rt+1;
			fname=rt.toString();
			FileInputStream fis = null;
			TextView t=(TextView)findViewById(R.id.textView2);
			t.setClickable(true);
			try{

	        	fis = openFileInput(fname);
	            DataInputStream dataIO = new DataInputStream(fis);
	            data=dataIO.readLine();
	            dataIO.close();
				t.setHint(data);

			}catch(IOException e){

			e.printStackTrace(System.err);
	        
			}
		}
		else
			Toast.makeText(getApplicationContext(), "Don't fret, we ain't eating your idea.",Toast.LENGTH_LONG).show();
		
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
public void test(View v)
{
Intent i=new Intent(this,enteredit.class);
i.putExtra("fname",fname);
i.putExtra("data", data);
startActivity(i);
finish();
}
}
