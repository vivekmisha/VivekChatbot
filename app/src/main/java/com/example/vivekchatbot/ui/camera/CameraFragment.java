package com.example.vivekchatbot.ui.camera;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.camera2.CameraManager;
import android.media.MediaRecorder;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.camera.core.ImageCapture;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import com.example.vivekchatbot.LogOutActivity;
import com.example.vivekchatbot.R;
import com.example.vivekchatbot.databinding.FragmentCameraBinding;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CameraFragment extends Fragment {
    FragmentCameraBinding binding;
    ProcessCameraProvider cameraProvider;
    int cam_face = CameraSelector.LENS_FACING_FRONT;
    boolean flipX = true;
    private boolean isRecording = false;
    private MediaRecorder mediaRecorder;
    private String currentVideoPath;

    CameraManager cameraManager;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private PreviewView previewView;
    ExecutorService cameraExecutor;
    ImageCapture imageCapture;
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCameraBinding.inflate(inflater, container, false);
        cameraManager = (CameraManager) requireContext().getSystemService(Context.CAMERA_SERVICE);
        imageCapture = new ImageCapture.Builder().setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY).build();
        cameraExecutor = Executors.newSingleThreadExecutor();


        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                .format(System.currentTimeMillis());
        String fileName = "IMG" + timeStamp + ".jpg";

        binding.camerabtn.setOnClickListener(v -> {
            if (cameraProvider != null) {
                cameraProvider.unbindAll();
                saveBitmapToStorage(previewView.getBitmap(), fileName);
                new Handler().postDelayed(this::startCamera, 3000);
            }
        });
        binding.recordButton.setOnClickListener(v -> {
            if (isRecording) {
                stopRecording();
            } else {
                startRecording();
            }
        });

        binding.flipCameraButton.setOnClickListener(v -> flipCamera());

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        previewView = view.findViewById(R.id.previewView);
        startCamera();

        binding.mprofile.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), LogOutActivity.class);
            startActivity(intent);
        });

    }

    private void startRecording() {
        try {
            File videoFile = createVideoFile();
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            mediaRecorder.setOutputFile(videoFile.getAbsolutePath());
            mediaRecorder.setVideoEncodingBitRate(10000000);
            mediaRecorder.setVideoFrameRate(30);
            int rotation = requireActivity().getWindowManager().getDefaultDisplay().getRotation();
            mediaRecorder.setOrientationHint(getOrientationHint(rotation));
            mediaRecorder.prepare();
            mediaRecorder.start();
            isRecording = true;
            Toast.makeText(requireContext(), "Recording started", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("MainActivity", "Failed to start recording", e);
        }
    }

    private void stopRecording() {
        if (isRecording) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;

            Toast.makeText(requireContext(), "Recording stopped", Toast.LENGTH_SHORT).show();
            saveVideoToGallery();
        }
    }

    private File createVideoFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String videoFileName = "VIDEO_" + timeStamp + ".mp4";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/Camera");
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        File videoFile = new File(storageDir, videoFileName);
        currentVideoPath = videoFile.getAbsolutePath();
        return videoFile;
    }

    private void saveVideoToGallery() {
        File videoFile = new File(currentVideoPath);
        Uri videoUri = Uri.fromFile(videoFile);

        MediaScannerConnection.scanFile(
                requireContext(),
                new String[]{videoUri.getPath()},
                new String[]{"video/mp4"},
                (path, uri) -> Toast.makeText(requireContext(), "Video saved to gallery: " + uri.toString(), Toast.LENGTH_SHORT).show()
        );
    }

    private int getOrientationHint(int rotation) {
        // Determine the orientation hint based on the rotation
        int orientation;
        switch (rotation) {
            case Surface.ROTATION_0:
                orientation = 90;
                break;
            case Surface.ROTATION_90:
                orientation = 0;
                break;
            case Surface.ROTATION_180:
                orientation = 270;
                break;
            case Surface.ROTATION_270:
                orientation = 180;
                break;
            default:
                orientation = 0;
        }
        return orientation;
    }

    private void startCamera() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext());
        cameraProviderFuture.addListener(() -> {
            try {
                cameraProvider = cameraProviderFuture.get();
                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(previewView.getSurfaceProvider());
                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(cam_face)
                        .build();
                Camera camera = cameraProvider.bindToLifecycle(
                        getViewLifecycleOwner(), cameraSelector, preview);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(requireContext()));
    }

    private void saveBitmapToStorage(Bitmap bitmap, String fileName) {
        File directory = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "SnapchatLite");
        if (!directory.exists()) {directory.mkdirs();}
        File file = new File(directory, fileName);
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();
            Toast.makeText(requireContext(), "Bitmap saved to storage", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private void flipCamera() {
        if (cam_face == CameraSelector.LENS_FACING_BACK) {
            cam_face = CameraSelector.LENS_FACING_FRONT;
            flipX = false;
        } else if (cam_face == CameraSelector.LENS_FACING_FRONT) {
            cam_face = CameraSelector.LENS_FACING_BACK;
            flipX = true;
        }
        cameraProvider.unbindAll();
        startCamera();
    }
}
