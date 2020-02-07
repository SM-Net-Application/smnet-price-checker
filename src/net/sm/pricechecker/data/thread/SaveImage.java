package net.sm.pricechecker.data.thread;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

// TODO : Ottimizzare e spostare questo processo nel core

public class SaveImage extends Thread {

	private String link;
	private File photo;

	public SaveImage(String link, File photosFolder, String productCode, String seller, String country) {
		super();
		this.link = link;
		this.photo = new File(photosFolder, seller);
		this.photo = new File(this.photo, country);
		this.photo.mkdirs();
		this.photo = new File(this.photo, productCode + ".png");
	}

	@Override
	public void run() {
		super.run();

		if (!this.link.isEmpty())
			if (!this.photo.exists())
				try {
					BufferedImage bi = ImageIO.read(new URL(this.link));
					ImageIO.write(bi, "png", this.photo);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

}
