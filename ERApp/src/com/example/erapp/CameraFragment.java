package com.example.erapp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.SaveCallback;


@SuppressLint("ShowToast")
public class CameraFragment extends Fragment{
	public static final String TAG="CameraFragment";
	
	private Camera camera;
	private SurfaceView surfaceView;
	private ParseFile photoFile;
	private Button photoButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.fragment_camera, parent, false);
		
		photoButton = (Button)v.findViewById(R.id.camera_photo_button);
		
		if(camera == null){
			try{
				camera = Camera.open();
				photoButton.setEnabled(true);
			} catch (Exception e){
				Log.e(TAG, "No camera with exception: " + e.getMessage());
				photoButton.setEnabled(false);
				Toast.makeText(getActivity(), "No camera detected", Toast.LENGTH_LONG).show();
			}
			
			
		} // end if(camera==null)
		
		photoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (camera == null){
					return;
				}
				camera.takePicture(new Camera.ShutterCallback() {

					@Override
					public void onShutter() {
						// nothing to do
					}

				}, null, new Camera.PictureCallback() {

					@Override
					public void onPictureTaken(byte[] data, Camera camera) {
						saveScaledPhoto(data);
					}

				}); // end takePicture

			}
		}); //end photoButton.setOnClickListener
		
		
		surfaceView = (SurfaceView) v.findViewById(R.id.camera_surface_view);
		SurfaceHolder holder = surfaceView.getHolder();
		holder.addCallback(new Callback() {

			public void surfaceCreated(SurfaceHolder holder) {
				try {
					if (camera != null) {
						camera.setDisplayOrientation(90);
						camera.setPreviewDisplay(holder);
						camera.startPreview();
					}
				} catch (IOException e) {
					Log.e(TAG, "Error setting up preview", e);
				}
			}

			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// nothing to do here
			}

			public void surfaceDestroyed(SurfaceHolder holder) {
				// nothing here
			}

		});  // end holder.addCallback
		
		return v;
	} //END onCreateView
	
	private void saveScaledPhoto(byte[] data) {

		// Resize photo from camera byte array
		Bitmap expenseImage = BitmapFactory.decodeByteArray(data, 0, data.length);
		Bitmap expenseImageScaled = Bitmap.createScaledBitmap(expenseImage, 200, 200
				* expenseImage.getHeight() / expenseImage.getWidth(), false);

		// Override Android default landscape orientation and save portrait
		Matrix matrix = new Matrix();
		matrix.postRotate(90);
		Bitmap rotatedScaledMealImage = Bitmap.createBitmap(expenseImageScaled, 0,
				0, expenseImageScaled.getWidth(), expenseImageScaled.getHeight(),
				matrix, true);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		rotatedScaledMealImage.compress(Bitmap.CompressFormat.JPEG, 100, bos);

		byte[] scaledData = bos.toByteArray();

		// Save the scaled image to Parse
		photoFile = new ParseFile("receipt_photo.jpg", scaledData);
		photoFile.saveInBackground(new SaveCallback() {

			public void done(ParseException e) {
				if (e != null) {
					Toast.makeText(getActivity(),"Error saving: " + e.getMessage(),
							Toast.LENGTH_LONG).show();
				} else {
					addPhotoToExpenseAndReturn(photoFile);
				}
			}
		});
	} // end saveScaledPhoto
	
	private void addPhotoToExpenseAndReturn(ParseFile photoFile) {
		((NewExpenseActivity) getActivity()).getCurrentExpense().setPhotoFile(photoFile);
		FragmentManager fm = getActivity().getFragmentManager();
		fm.popBackStack("NewExpenseFragment",FragmentManager.POP_BACK_STACK_INCLUSIVE);
	} // end addPhotoToExpenseAndReturn
	
	@Override
	public void onResume() {
		super.onResume();
		if (camera == null) {
			try {
				camera = Camera.open();
				photoButton.setEnabled(true);
			} catch (Exception e) {
				Log.i(TAG, "No camera: " + e.getMessage());
				photoButton.setEnabled(false);
				Toast.makeText(getActivity(), "No camera detected",
						Toast.LENGTH_LONG).show();
			}
		}
	} // end onResume

	@Override
	public void onPause() {
		if (camera != null) {
			camera.stopPreview();
			camera.release();
		}
		super.onPause();
	}// end onPause
	
	
} //End CameraFragment
