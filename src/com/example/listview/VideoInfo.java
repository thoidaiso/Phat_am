package com.example.listview;

public class VideoInfo {
	int id;
	String uniq_id;
	String artist;
	String video_title;
	String description;
	String yt_id;
	String yt_thumb;
	int site_views;
	String mp3;

	public int getId() {
		return this.id;
	}

	public String getVideoId() {
		return this.uniq_id;
	}

	public String getArtist() {
		return this.artist;
	}

	public String getVideoTitle() {
		return this.video_title;
	}

	public String getDescription() {
		return this.description;
	}

	public String getYoutubeId() {
		return this.yt_id;
	}

	public String getYoutubeImage() {
		return this.yt_thumb;
	}

	public int getSiteView() {
		return this.site_views;
	}

	public String getMp3() {
		return this.mp3;
	}
}
