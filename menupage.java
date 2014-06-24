package the.idea.bank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class menupage extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mp);
	}
	public void m1(View v)
	{
	Intent i=new Intent(this,enter.class);
	startActivity(i);
	}
	
	public void m2(View v)
	{
		Intent i=new Intent(this,randomread.class);
		startActivity(i);	
	}
}