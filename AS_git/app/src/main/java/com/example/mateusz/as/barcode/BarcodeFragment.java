package com.example.mateusz.as.barcode;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mateusz.as.MainActivity;
import com.example.mateusz.as.R;
import com.example.mateusz.as.adapters.AdapterCattle;
import com.example.mateusz.as.models.Cattle;
import com.example.mateusz.as.saveModelFragments.CattleFragment;
import com.example.mateusz.as.show.CattleInfoFragment;
import com.example.mateusz.as.show.ListSearchFragment;
import com.example.mateusz.as.viewHolder.TeamViewHolder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.*;

public class BarcodeFragment extends Fragment {

    public static final String ID_CATTLES = "LIST_CATTLE_WITH_ID";
    static final int REQUEST_TAKE_PHOTO = 1;

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private SurfaceView camera;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private Button show;
    private ArrayList<Integer> idCattle = new ArrayList<>();
    private String number = "";
    private String earring;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_barcode, container, false);

        initFirst(view);

        return view;
    }

    public void initFirst(final View view) {
        show = view.findViewById(R.id.show_search_cattle);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCattle();
            }
        });
        show.setEnabled(false);
        camera = view.findViewById(R.id.camera);

        barcodeDetector = new BarcodeDetector.Builder(view.getContext())
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource
                .Builder(view.getContext(), barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();

        camera.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (checkSelfPermission(view.getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                        requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_TAKE_PHOTO);

                        return;
                    }
                    cameraSource.start(camera.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                SparseArray<Barcode> qrcode = detections.getDetectedItems();
                if (qrcode.size() != 0) {
                    if (!number.equals(qrcode.valueAt(0).rawValue)) {
                        number = qrcode.valueAt(0).rawValue;
                        idCattle.clear();
                        db.collection("Cattle")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            List<Cattle> cattles = new ArrayList<>();
                                            cattles.addAll(task.getResult().toObjects(Cattle.class));

                                            for (Cattle cattle : cattles) {
                                                if (cattle.getEarring().contains(number)) {
                                                    idCattle.add((int) cattle.getIdCattle());
                                                    earring = cattle.getEarring();
                                                }
                                            }

                                            show.setEnabled(true);

                                        } else {
                                            Log.w("QueryCattle.read: ", "Error getting documents.", task.getException());
                                        }
                                    }
                                });
                    }
                }
            }
        });

    }

    public void showCattle(){
        if (idCattle.size() == 1) {
            CattleInfoFragment cattleInfoFragment = new CattleInfoFragment();
            Bundle bundle = new Bundle();
            bundle.putString(CattleInfoFragment.CATTLE_EARRNIG, earring);
            cattleInfoFragment.setArguments(bundle);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.container_fragment, cattleInfoFragment);
            ft.commit();
        } else if (idCattle.size() > 1) {
            ListSearchFragment listSearchFragment = new ListSearchFragment();
            Bundle bundle = new Bundle();
            bundle.putIntegerArrayList(ID_CATTLES, idCattle);
            listSearchFragment.setArguments(bundle);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.container_fragment, listSearchFragment);
            ft.commit();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_TAKE_PHOTO:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        cameraSource.start(camera.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }


    }

}
