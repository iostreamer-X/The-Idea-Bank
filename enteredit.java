package the.idea.bank;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class enteredit extends Activity
{   
    String fname,data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ente);
		Bundle b=getIntent().getExtras();
		fname=b.getString("fname");
		data=b.getString("data");
		EditText e=(EditText)findViewById(R.id.multiAutoCompleteTextView1);
		e.setText(data);
	}
	public void add(View v)
	{
		EditText e=(EditText)findViewById(R.id.multiAutoCompleteTextView1);
		String data=e.getText().toString();
		if(data.equals("")==false)
		{
			FileOutputStream fOut = null;

			OutputStreamWriter osw = null;

			try{

			fOut = openFileOutput(fname, Context.MODE_PRIVATE);

			osw = new OutputStreamWriter(fOut);

			osw.write(data);

			osw.close();

			fOut.close();
			Toast.makeText(getApplicationContext(), "Your idea has been edited sucessfully.",Toast.LENGTH_LONG).show();
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

}
