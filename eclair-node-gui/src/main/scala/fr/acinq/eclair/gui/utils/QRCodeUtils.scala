package fr.acinq.eclair.gui.utils

import javafx.scene.image.WritableImage
import javafx.scene.paint.Color

import com.google.zxing.{BarcodeFormat, EncodeHintType}
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel

/**
  * Created by DPA on 22/12/2017.
  */
case object QRCodeUtils {

  def createQRCode(data: String, width: Int = 250, height: Int = 250, margin: Int = 5): WritableImage = {
    import scala.collection.JavaConversions._
    val hintMap = collection.mutable.Map[EncodeHintType, Object]()
    hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8")
    hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L)
    hintMap.put(EncodeHintType.MARGIN, margin.toString)
    val qrWriter = new QRCodeWriter
    val byteMatrix = qrWriter.encode(data, BarcodeFormat.QR_CODE, width, height, hintMap)
    val writableImage = new WritableImage(width, height)
    val pixelWriter = writableImage.getPixelWriter
    for (i <- 0 until byteMatrix.getWidth) {
      for (j <- 0 until byteMatrix.getWidth) {
        if (byteMatrix.get(i, j)) {
          pixelWriter.setColor(i, j, Color.BLACK)
        } else {
          pixelWriter.setColor(i, j, Color.WHITE)
        }
      }
    }
    writableImage
  }
}
