package the.idea.bank;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class enter extends Activity
{   Integer tot=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ente);
		tot=gettot();
		if (tot==1)
			Toast.makeText(getApplicationContext(),"You currently have 1 idea in your Account.",Toast.LENGTH_LONG).show();
		else
			Toast.makeText(getApplicationContext(),"You currently have "+tot.toString()+" ideas in your Account.",Toast.LENGTH_LONG).show();
		tot++;
		Log.d("ert",tot.toString());
		
		
		
	}
	public void add(View v)
	{
		EditText e=(EditText)findViewById(R.id.multiAutoCompleteTextView1);
		String data=e.getText().toString();
		if(data.equals("")==false)
		{
			String fname=tot.toString();
			FileOutputStream fOut = null;

			OutputStreamWriter osw = null;

			try{

			fOut = openFileOutput(fname, Context.MODE_PRIVATE);

			osw = new OutputStreamWriter(fOut);

			osw.write(data);

			osw.close();

			fOut.close();
			fOut = openFileOutput("total.dat", Context.MODE_PRIVATE);
			osw = new OutputStreamWriter(fOut);
			osw.write(tot.toString());
			osw.close();

			fOut.close();
			Toast.makeText(getApplicationContext(), "Your idea has been added to your account safely.",Toast.LENGTH_LONG).show();
			finish();
			}catch(Exception e1){

			e1.printStackTrace(System.err);
			Toast.makeText(getApplicationContext(), "Something went wrong with your internal storage device.",Toast.LENGTH_LONG).show();
			finish();
			}
		}
		else
			Toast.makeText(getApplicationContext(), "Empty ideas are not going to help you.",Toast.LENGTH_SHORT).show();
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
