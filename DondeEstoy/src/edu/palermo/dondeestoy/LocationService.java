package edu.palermo.dondeestoy;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import edu.palermo.dondeestoy.bo.BaseResponse;
import edu.palermo.dondeestoy.rest.ApiService;
import edu.palermo.dondeestoy.rest.ApiServiceException;

public class LocationService extends Service {
	// public static final String BROADCAST_ACTION = "Hello World";
	private static final int ONE_MINUTE = 1000 * 60 * 1;

	public static LocationService getInstance() {
		// TODO Auto-generated constructor stub
		return locationService;
	}

	public static LocationService locationService;
	public static Boolean bregistred = false;
	public LocationManager locationManager;
	public MyLocationListener listener;
	public Location previousBestLocation = null;

	public Location getPreviousBestLocation() {
		if (previousBestLocation == null) {
			if (locationManager != null) {
				previousBestLocation = locationManager
						.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			}
		} else {
			return previousBestLocation;
		}
		return previousBestLocation;
	}

	// Intent intent;
	int counter = 0;

	@Override
	public void onCreate() {
		super.onCreate();
		// intent = new Intent(BROADCAST_ACTION);
		locationService = this;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		if (locationManager == null) {
			locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			listener = new MyLocationListener();
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 55000, 0, listener);
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 55000, 0, listener);
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	protected boolean isBetterLocation(Location location,
			Location currentBestLocation) {
		if (currentBestLocation == null) {
			// A new location is always better than no location
			return true;
		}

		// Check whether the new location fix is newer or older
		long timeDelta = location.getTime() - currentBestLocation.getTime();
		boolean isSignificantlyNewer = timeDelta > ONE_MINUTE;
		boolean isSignificantlyOlder = timeDelta < -ONE_MINUTE;
		boolean isNewer = timeDelta > 0;

		// If it's been more than two minutes since the current location, use
		// the new location
		// because the user has likely moved
		if (isSignificantlyNewer) {
			return true;
			// If the new location is more than two minutes older, it must be
			// worse
		} else if (isSignificantlyOlder) {
			return false;
		}

		// Check whether the new location fix is more or less accurate
		int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation
				.getAccuracy());
		boolean isLessAccurate = accuracyDelta > 0;
		boolean isMoreAccurate = accuracyDelta < 0;
		boolean isSignificantlyLessAccurate = accuracyDelta > 200;

		// Check if the old and new location are from the same provider
		boolean isFromSameProvider = isSameProvider(location.getProvider(),
				currentBestLocation.getProvider());

		// Determine location quality using a combination of timeliness and
		// accuracy
		if (isMoreAccurate) {
			return true;
		} else if (isNewer && !isLessAccurate) {
			return true;
		} else if (isNewer && !isSignificantlyLessAccurate
				&& isFromSameProvider) {
			return true;
		}
		return false;
	}

	/** Checks whether two providers are the same */
	private boolean isSameProvider(String provider1, String provider2) {
		if (provider1 == null) {
			return provider2 == null;
		}
		return provider1.equals(provider2);
	}

	@Override
	public void onDestroy() {
		// handler.removeCallbacks(sendUpdatesToUI);
		super.onDestroy();
		Log.v("STOP_SERVICE", "DONE");
		locationManager.removeUpdates(listener);
	}

	public static Thread performOnBackgroundThread(final Runnable runnable) {
		final Thread t = new Thread() {
			@Override
			public void run() {
				try {
					runnable.run();
				} finally {

				}
			}
		};
		t.start();
		return t;
	}

	public class MyLocationListener implements LocationListener {
		private String TAG = "MyLocationListener";
		private String imeiActual;
		private String serverAddress;

		public void onLocationChanged(final Location loc) {
			Utils utils = new Utils(getApplicationContext());
			imeiActual = utils.getIMEI();
			serverAddress = utils.getServerAddress();
			Log.i("**************************************", "Location changed");
			if (isBetterLocation(loc, previousBestLocation)) {
				loc.getLatitude();
				loc.getLongitude();
				Log.d("Latitude", Double.toString(loc.getLatitude()));
				Log.d("Longitude", Double.toString(loc.getLongitude()));
				Log.d("Provider", loc.getProvider());
				Log.d("HashCode", "Start  Id:" + this.hashCode());
				Log.d("**************************************",
						"***************");
				try {
					new Thread(new Runnable() {
						public void run() {

							ApiService apiService = new ApiService();
							ApiService.setServerAddress(serverAddress);
							BaseResponse baseResponse;
							try {
								if (!LocationService.bregistred) {
									baseResponse = apiService
											.postCreateDevice(
													imeiActual,
													"Usuario Android",
													Utils.PERSONAL_LOCATION_CATEGORY_ID,
													Utils.MOVIL_LOCATION_TYPE_ID);
									if (baseResponse.getCode().equals("000")) {
										Log.d(TAG, "Device registrado");
									} else {
										Log.d(TAG,
												"El Device existe en la base de datos");
									}
									bregistred=true;
								}
								baseResponse = apiService.postUpdateLocation(
										loc.getLatitude(), loc.getLongitude(),
										imeiActual);
								Log.d(TAG, "Registrada posicion");
							} catch (ApiServiceException e) {
								Log.e(TAG, "Error al registrar el DEVICE", e);
								bregistred = false;
							}
						}
					}).start();
				} catch (Exception ex) {
					Log.e("mylocationService", ex.getMessage());
				}

			}
		}

		public void onProviderDisabled(String provider) {
			Toast.makeText(getApplicationContext(), "Gps Disabled",
					Toast.LENGTH_SHORT).show();
		}

		public void onProviderEnabled(String provider) {
			Toast.makeText(getApplicationContext(), "Gps Enabled",
					Toast.LENGTH_SHORT).show();
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {

		}
	}
}
