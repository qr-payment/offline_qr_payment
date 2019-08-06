package com.example.user_client_kotlin.ui


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.user_client_kotlin.R
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.fragment_main_transaction.*
import java.io.IOException


class MainTransactionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val barcodeDetector = BarcodeDetector.Builder(requireContext())
            .setBarcodeFormats(Barcode.QR_CODE)
            .build()

        val cameraSource = CameraSource
            .Builder(requireContext(), barcodeDetector)
            .setFacing(CameraSource.CAMERA_FACING_BACK)
            .setRequestedFps(29.8f)
            .setRequestedPreviewSize(600,800)
            .setAutoFocusEnabled(true)
            .build()

        cameraSurface.holder.addCallback(object: SurfaceHolder.Callback{
            override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
            }
            override fun surfaceDestroyed(holder: SurfaceHolder?) {
                if(cameraSource != null){
                    // 카메라 미리보기를 종료한다.
                    cameraSource.stop()
                    cameraSource.release()
                }
            }
            override fun surfaceCreated(holder: SurfaceHolder?) {
                try {
                    if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED){
                        cameraSource.start(cameraSurface.holder)
                        return
                    }
                }catch (e: IOException){
                    e.printStackTrace()
                }
            }
        })

        barcodeDetector.setProcessor(object: Detector.Processor<Barcode>{
            override fun release() {
                Log.e("Detection","release")
            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>?) {
                val barcodes = detections?.detectedItems
                if (barcodes?.size() != 0) {
                    val barcodeContents = barcodes?.valueAt(0)?.displayValue // 바코드 인식 결과물
                    Log.e("Detection", ""+barcodeContents)
                }
            }

        })


    }




}
