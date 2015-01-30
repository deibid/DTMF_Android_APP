package com.davidazar.dtmf;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment()).commit();
		}

		getActionBar().setTitle("Pablo es puto");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if(item.getItemId() == R.id.opcion_info){

			AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
			builder1.setMessage("David Azar\n\n\nFiltros 2014");
			builder1.setCancelable(false);
			builder1.setPositiveButton("Cerrar",
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});

			AlertDialog alert11 = builder1.create();
			alert11.show();

		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		ToneGenerator toneGenerator;
		Button []mButtons;
		int[] mIds;
		int[] mBits;
		int mSound;

		String[] mFrecuencias;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);


			mIds = new int[]{

					ToneGenerator.TONE_DTMF_S,
					ToneGenerator.TONE_DTMF_1,
					ToneGenerator.TONE_DTMF_2,
					ToneGenerator.TONE_DTMF_3,
					ToneGenerator.TONE_DTMF_4,
					ToneGenerator.TONE_DTMF_5,
					ToneGenerator.TONE_DTMF_6,
					ToneGenerator.TONE_DTMF_7,
					ToneGenerator.TONE_DTMF_8,
					ToneGenerator.TONE_DTMF_9,
					ToneGenerator.TONE_DTMF_A,
					ToneGenerator.TONE_DTMF_B,
					ToneGenerator.TONE_DTMF_C,
					ToneGenerator.TONE_DTMF_D,
					ToneGenerator.TONE_DTMF_0,
					ToneGenerator.TONE_DTMF_P
			};


			mBits = new int[]{

					R.id.btCero,
					R.id.btUno,
					R.id.btDos,
					R.id.btTres,
					R.id.btCuatro,
					R.id.btCinco,
					R.id.btSeis,
					R.id.btSiete,
					R.id.btOcho,
					R.id.btNueve,
					R.id.btA,
					R.id.btB,
					R.id.btC,
					R.id.btD,
					R.id.btAsterix,
					R.id.btHash
			};


			mFrecuencias = new String[]{

					
					"941 Hz + 1209 Hz",
					"697 Hz + 1209 Hz",
					"697 Hz + 1336 Hz",
					"697 Hz + 1477 Hz",
					"770 Hz + 1209 Hz",
					"770 Hz + 1336 Hz",
					"770 Hz + 1477 Hz",
					"852 Hz + 1209 Hz",
					"852 Hz + 1336 Hz",
					"852 Hz + 1477 Hz",
					"697 Hz + 1633 Hz",
					"770 Hz + 1633 Hz",
					"852 Hz + 1633 Hz",
					"941 Hz + 1633 Hz",		
					"941 Hz + 1336 Hz",
					"941 Hz + 1477 Hz"

			};


			final TextView tvFrecuencias = (TextView) getActivity().findViewById(R.id.tvFrecuencias);

			mButtons = new Button[16];

			for(int i =0;i<16;i++){
				mButtons[i] = (Button) getActivity().findViewById(mBits[i]);
				mButtons[i].setOnTouchListener(new View.OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						switch(event.getAction()){
						case(MotionEvent.ACTION_DOWN):
							int j = v.getId();
						j = returnIndex(mBits,j);
						mSound = mIds[j];
						toneGenerator.startTone(mSound);
						tvFrecuencias.setText(mFrecuencias[j]);
						break;
						case(MotionEvent.ACTION_UP):
							toneGenerator.stopTone();
						tvFrecuencias.setText("");
						break;
						}
						return false;
					}
				});
			}

			toneGenerator = new ToneGenerator(1, 100);
		}



		private static int returnIndex(int[] array,int index){

			for(int i =0;i<array.length;i++){
				if(array[i] == index)
					return i;
			}

			return 0;
		}

	}


}


