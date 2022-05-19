package com.ssafy.vue.dto;

public class Notice {
	private int idx;
	private String title;
	private String content;
	private String id;
	private String date;
	
	public Notice() {
		super();
	}

	public Notice(int idx, String title, String content, String id, String date) {
		super();
		this.idx = idx;
		this.title = title;
		this.content = content;
		this.id = id;
		this.date = date;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "NoticeDTO [idx=" + idx + ", title=" + title + ", content=" + content + ", id=" + id + ", date=" + date
				+ "]";
	}
}
