package com.example.viewpagercircle;

/*import com.viewpagerindicator.IconPagerAdapter;*/

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public final class TestFragment extends Fragment{
    private static final String KEY_CONTENT = "TestFragment:Content";
    int i = 0;
    
    
    public static TestFragment newInstance(String content) {
        TestFragment fragment = new TestFragment();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            builder.append(content).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        fragment.mContent = builder.toString();

        return fragment;
    }
    
    public static TestFragment newInstance(int content) {
        TestFragment fragment = new TestFragment();

        
        fragment.mInt = content;

        return fragment;
    }
    
    

    
    private String mContent = "???";
    private int mInt = 0;
    public static int count = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }
    }

    
   
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       /* TextView text = new TextView(getActivity());
        text.setGravity(Gravity.CENTER);
        text.setText(mContent);
        text.setTextSize(20 * getResources().getDisplayMetrics().density);
        text.setPadding(20, 20, 20, 20);*/
        	View thirdL;
            thirdL = inflater.inflate(mInt, container, false);
            
            Log.d("count", mInt+ "");
        
            
            /*if (count > 2130903042) {*/
            
            ImageView logo = (ImageView) thirdL.findViewById(R.id.logo);
            
            logo.setImageResource(R.drawable.logo2);
            
            String fontPath = "fonts/KaushanScript-Regular.otf";
            
            TextView appName = (TextView) thirdL.findViewById(R.id.appName);
            
            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

            appName.setTypeface(tf);
            
            	 Button getStarted = (Button) thirdL.findViewById(R.id.getStarted);
            	 if (getStarted!=null) {
            		 getStarted.setOnClickListener(new View.OnClickListener() {
         				
         				@Override
         				public void onClick(View v) {
         					// TODO Auto-generated method stub
         				//	Toast.makeText(getActivity(), "GetStarted", Toast.LENGTH_SHORT).show();
         					getActivity().startActivity(new Intent(getActivity(), DOB_Friends.class));
         				}
         			});	
				}
                
			/*}*/
           

            return thirdL;
       

        
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    }

	/*@Override
	public int getIconResId(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBackground(int b_i) {
		
		return 0;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), "GetStarted", Toast.LENGTH_SHORT).show();
		
	}*/
}
