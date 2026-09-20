package com.dialogshowcase;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
//import yourpackage.dialogs.*;
import com.dialogshowcase.dialogs.*;


public class BestfiveActivity extends AppCompatActivity {
	
	private ArrayList<HashMap<String, Object>> dialogItem = new ArrayList<>();
	
	private LinearLayout linear6;
	private LinearLayout layout_collapsible_header;
	private LinearLayout linear7;
	private RecyclerView recyclerview1;
	private LinearLayout linear2;
	private TextView textview2;
	private TextView textview1;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.bestfive);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear6 = findViewById(R.id.linear6);
		layout_collapsible_header = findViewById(R.id.layout_collapsible_header);
		linear7 = findViewById(R.id.linear7);
		recyclerview1 = findViewById(R.id.recyclerview1);
		linear2 = findViewById(R.id.linear2);
		textview2 = findViewById(R.id.textview2);
		textview1 = findViewById(R.id.textview1);
	}
	
	private void initializeLogic() {
		//NOTE 
		//Create Colours in colors.xml
		//Resources Editor -> colors.xml
		_setList();
	}
	
	public void _setList() {
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("", "");
			dialogItem.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("", "");
			dialogItem.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("", "");
			dialogItem.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("", "");
			dialogItem.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("", "");
			dialogItem.add(_item);
		}
		recyclerview1.setLayoutManager(new LinearLayoutManager(this));
		recyclerview1.setAdapter(new Recyclerview1Adapter(dialogItem));
	}
	
	
	public void _dialog1() {
		StepWizardDialog.show(BestfiveActivity.this, new StepWizardDialog.OnWizardCompletedListener() {
			
			@Override
			public void onCompleted() {
				SketchwareUtil.showMessage(getApplicationContext(), "Completed ");
			}
		});
	}
	
	
	public void _dialog2() {
		OtpVerificationDialog.show(BestfiveActivity.this, new OtpVerificationDialog.OnOtpVerifiedListener() {
			@Override
			public void onVerified(String otpCode) {
				
				
				
				SketchwareUtil.showMessage(getApplicationContext(), "Verified OTP" + otpCode);
			}
		});
	}
	
	
	public void _dialog3() {
		NumberStepperDialog.show(BestfiveActivity.this, 1, 1, 10, new NumberStepperDialog.OnQuantitySelectedListener() {
			@Override
			public void onQuantitySelected(int quantity) {
				SketchwareUtil.showMessage(getApplicationContext(), "Quantity : " + quantity);
			}
		});
	}
	
	
	public void _dialog4() {
		GlassmorphicDialog.show(BestfiveActivity.this, new GlassmorphicDialog.OnExploreListener() {
			@Override
			public void onExplore() {
				SketchwareUtil.showMessage(getApplicationContext(), "Completed");
			}
		});
	}
	
	
	public void _dialog5() {
		ColorPickerDialog.show(BestfiveActivity.this, new ColorPickerDialog.OnColorSelectedListener() {
			@Override
			public void onColorSelected(String hexColor, int colorInt) {
				SketchwareUtil.showMessage(getApplicationContext(), "Colour :" + colorInt +"  "+ "HEX:" + hexColor);
				
			}
		});
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.item_dialog_card, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout base = _view.findViewById(R.id.base);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final TextView card_description = _view.findViewById(R.id.card_description);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final FrameLayout card_icon_container = _view.findViewById(R.id.card_icon_container);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final ImageView card_icon = _view.findViewById(R.id.card_icon);
			final TextView card_category = _view.findViewById(R.id.card_category);
			final TextView card_title = _view.findViewById(R.id.card_title);
			final com.google.android.material.button.MaterialButton card_btn_code = _view.findViewById(R.id.card_btn_code);
			final com.google.android.material.button.MaterialButton card_btn_launch = _view.findViewById(R.id.card_btn_launch);
			
			if (_position == 0) {
				card_description.setText("Interactive 3-step setup flow with dot progress indicators, back/next navigation, and completion callback.");
				card_category.setText("Wizards & OTP");
				card_title.setText("Multi-Step Onboarding Wizard");
				card_icon.setImageResource(R.drawable.ic_arrow_forward);
			}
			else
			if (_position == 1) {
				card_description.setText("Advanced 4-digit square input boxes with automatic focus jumping, backspace handling, and resend countdown.");
				card_category.setText("Wizards & OTP");
				card_title.setText("OTP Code Verification");
				card_icon.setImageResource(R.drawable.ic_lock);
			}
			else
			if (_position == 2) {
				card_description.setText("Interactive [-] and [+] stepper with min/max bounds and instant count display.");
				card_category.setText("Input & Forms");
				card_title.setText("Number Stepper Counter");
				card_icon.setImageResource(R.drawable.ic_add);
			}
			else
			if (_position == 3) {
				card_description.setText("Translucent card styling with subtle borders, glowing icon, and modern aesthetics.");
				card_category.setText("Pickers & Custom");
				card_title.setText("Glassmorphic Card Dialog");
				card_icon.setImageResource(R.drawable.ic_sparkle);
			}
			else
			if (_position == 4) {
				card_description.setText("Grid of 8 vibrant color circles with hex preview and selection callback.");
				card_category.setText("Pickers & Custom");
				card_title.setText("Color Palette Picker");
				card_icon.setImageResource(R.drawable.ic_palette);
			} else {
				
			}
			View.OnClickListener commonClick = new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					
					if (_position == 0) {
						_dialog1();
					}
					else
					if (_position == 1) {
						_dialog2();
					}
					else
					if (_position == 2) {
						_dialog3();
					}
					else
					if (_position == 3) {
						_dialog4();
					}
					else
					if (_position == 4) {
						_dialog5();
					} else {
						
					}
				}
			};
			
			card_btn_launch.setOnClickListener(commonClick);
			base.setOnClickListener(commonClick);
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
	}
}