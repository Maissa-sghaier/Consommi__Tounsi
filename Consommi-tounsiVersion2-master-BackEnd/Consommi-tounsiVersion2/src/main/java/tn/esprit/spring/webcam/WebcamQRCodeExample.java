package tn.esprit.spring.webcam;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.springframework.stereotype.Service;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
@Service
public class WebcamQRCodeExample extends JFrame implements IWebcamQRCodeExample, Callable, ThreadFactory {

	private static final long serialVersionUID = 6441489157408381878L;
	String code;
	private ExecutorService executorService = Executors.newFixedThreadPool(500);
	private Webcam webcam = null;
	private WebcamPanel panel = null;
	private JTextArea textarea = null;

	public WebcamQRCodeExample() {
		super();
		
		//setVisible(true);
		}

	@Override
	public String call() throws InterruptedException {
		 
		setLayout(new FlowLayout());
		setTitle("Read Bar Code With Webcam");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Dimension size = WebcamResolution.QVGA.getSize();

		webcam = Webcam.getWebcams().get(0);
		webcam.setViewSize(size);

		panel = new WebcamPanel(webcam);
		panel.setPreferredSize(size);
		panel.setFPSDisplayed(true);

		textarea = new JTextArea();
		textarea.setEditable(false);
		textarea.setPreferredSize(size);

		add(panel);
		add(textarea);

		pack();
		setVisible(true);
		executorService.submit(this);
		
		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Result result = null;
			BufferedImage image = null;

			if (webcam.isOpen()) {

				if ((image = webcam.getImage()) == null) {
					continue;
				}

				LuminanceSource source = new BufferedImageLuminanceSource(image);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

				try {
					result = new MultiFormatReader().decode(bitmap);
				} catch (NotFoundException e) {
					// fall thru, it means there is no QR code in image
				}
			}

			if (result != null) {
				
				if (result.getText().substring(0,3).equals("619"))
				{
					textarea.setText(result.getText()+" : produit 100% tunisien.");
					executorService.awaitTermination(3, TimeUnit.SECONDS);
					webcam.close();
					
					//webcam=null;

					return code= result.getText();
					
					
				}
				else 
				{
					textarea.setText(result.getText()+" : non tunisian product.\n"
							+ "this product will not be added to data base.");
					executorService.awaitTermination(3, TimeUnit.SECONDS);
					webcam.close();
					
					//webcam=null;

					return code=null;
				}
			}

		} while (true);
		
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, "example-runner");
		t.setDaemon(true);
		return t;
	}

//	public static void main(String[] args) {
//		new WebcamQRCodeExample();
//	}
}